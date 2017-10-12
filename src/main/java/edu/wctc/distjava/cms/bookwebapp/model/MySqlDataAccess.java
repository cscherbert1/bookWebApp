package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

/**
 *
 * @author cscherbert1
 */
public class MySqlDataAccess implements DataAccess {

    private final int ALL_RECORDS = 0;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private final boolean DEBUG = true;

    //get db connection
    //encapsulate complexity of creating url, entering login info, etc
    public final void openConnection(String driverClass, String url,
            String userName, String password) throws ClassNotFoundException, SQLException {

        //must validate these values being passed in 
        if (driverClass == null || driverClass.length() < 5) {
            throw new IllegalArgumentException("Invalid driverClass provided. Cannot connect to the Database");
        }
        if (url == null || url.length() < 5) {
            throw new IllegalArgumentException("Invalid URL provided. Cannot connext to the Database");
        }
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Invalid user name provided.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid password provided.");
        }

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    public final void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * Returns records from a table. Requires an open connection
     *
     * @param tableName
     * @param maxRecords
     * @return rawData
     * @throws SQLException
     */
    public final List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException {

        //don't use an arrayList, it's not thread safe. use Vector instead, even tho it's an old collection
        List<Map<String, Object>> rawData = new Vector<>();

        String sql = "";

        //be aware that this code block in vulnerable to sql injection
        //will learn solution in another lesson after 9/26/2017
        if (maxRecords > ALL_RECORDS) {
            sql = "select * from " + tableName + " limit " + maxRecords;
        } else {
            sql = "select * from " + tableName;
        }

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount(); //be careful, this is 1 based numbering, not 0 based

        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                //record key put in map,  record value
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }

        return rawData; 
    }

    public final List<Map<String, Object>> findRecordById(String tableName,
            String pkColName, Object pkValue)
            throws SQLException {
        //validation

        //logic
        String sql = "SELECT * FROM " + tableName + " WHERE " + pkColName + " = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, pkValue);

        if (DEBUG) { //turn debug off  (set to false) if going into production
            System.out.println(sql);
        }

        rs = pstmt.executeQuery();

        Map<String, Object> record = new LinkedHashMap<>();

                ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount(); //be careful, this is 1 based numbering, not 0 based

        List<Map<String, Object>> rawData = new Vector<>();

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                //record key put in map,  record value
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }

        return rawData;
    }

    public final int deleteRecordById(String tableName, String pkColName, Object pkValue)
            throws ClassNotFoundException, SQLException {

        //validate parameters
        String sql = "DELETE FROM " + tableName
                + " WHERE " + pkColName + " = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, pkValue);

        return pstmt.executeUpdate();

    }

    public final int createRecord(String tableName, List<String> colNames,
            List<Object> colValues)
            throws SQLException {

        //validation goes here:
        //Logic        
        String sql = "INSERT INTO " + tableName + " ";

        StringJoiner sj = new StringJoiner(", ", "(", ")");
        for (String col : colNames) {
            sj.add(col);
        }

        sql += sj.toString() + " VALUES ";

        //reset the String Joiner by recalling the constructor
        sj = new StringJoiner(", ", "(", ")");
        for (Object value : colValues) {
            sj.add("?");
        }

        sql += sj.toString();

        if (DEBUG) { //turn debug off  (set to false) if going into production
            System.out.println(sql);
        }

        pstmt = conn.prepareStatement(sql);

        //set values for each ? as objects. conversion happens automatically
        for (int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i - 1));
        }

        return pstmt.executeUpdate();
    }

    public final int updateRecordById(String tableName, List<String> colNames,
            List<Object> colValues, String pkColName, Object pkValue) throws SQLException {

        //validate here:
        //null/ empty table name, min size of 1 for lists, make sure the size of colNames and colValues should match
        //pkcolName not null/ empty, pkVal not null
        //logic
        String sql = "UPDATE " + tableName + " SET ";

        StringJoiner sj = new StringJoiner(" = ?, ", "", " =? ");

        //adds the ? to the final item in the colNames list
        for (String col : colNames) {
            sj.add(col);

        }

        sql += sj.toString()
                + "WHERE " + pkColName + " = ?";

        if (DEBUG) { //turn debug off  (set to false) if going into production
            System.out.println(sql);
        }

        pstmt = conn.prepareStatement(sql);

        //set values for each ? as objects. conversion happens automatically
        for (int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i - 1));

            //place this final setObject outside forloop
            if (i == colValues.size()) {
                pstmt.setObject((i + 1), pkValue);
            }
        }

        return pstmt.executeUpdate();

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public PreparedStatement getPstmt() {
        return pstmt;
    }

    public void setPstmt(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }


    /*
    MAIN METHOD FOR TESTING
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataAccess db = new MySqlDataAccess();

        db.openConnection(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin"
        );

        System.out.println("Test findRecordById:");

        List<Map<String, Object>> returnedRecord = db.findRecordById("author", "author_id", 7);
        System.out.println(returnedRecord);

        db.closeConnection();

//        int recsAdded = db.createRecord("author", Arrays.asList("author_name, date_added"), 
//                Arrays.asList("Sally Smith", "2010-02-14"));
//        int recsUpdated = db.updateRecordById("author", Arrays.asList("author_name", "date_added"), 
//                Arrays.asList("Sally Smith", "2010-02-14"), "author_id", 10);
//
//        System.out.println("Recs Updated: " + recsUpdated);
//        
//        List<Map<String, Object>> list = db.getAllRecords("author", 0);
//
//        for (Map<String, Object> rec : list) {
//            System.out.println(rec);
//        }
//        System.out.println("Recs created: " + recsAdded);
//        db.openConnection(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root",
//                "admin"
//        );
//
//        //Test for getAllRecords()
//        System.out.println("Test getAllRecords:");
//        List<Map<String, Object>> list = db.getAllRecords("author", 0);
//
//        for (Map<String, Object> rec : list) {
//            System.out.println(rec);
//        }
//        
//        db.closeConnection();
//        int recsDelete = db.deleteRecordById("author", "author_id", new Integer(7));
        //Test for deleteSelectRecords()
//        System.out.println("Test deleteSelectRecords:");
//        List<Integer> deletePKs = new ArrayList<Integer>();
//        deletePKs.add(4);
//        db.deleteSelectRecords("author", "author_id", deletePKs);
//        
//        List<Map<String, Object>> delCheck = db.getAllRecords("author", 0);
//
//        for (Map<String, Object> rec : delCheck) {
//            System.out.println(rec);
//        }
    }
}
