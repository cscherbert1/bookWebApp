package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public MySqlDataAccess(String driverClass, String url,
            String userName, String password) {
        //validate all of these input parameters for null

//        if(driverClass == null || driverClass.length() < 5){
//            
//        }
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }

    //get db connection
    //encapsulate complexity of creating url, entering login info, etc
    public void openConnection() throws ClassNotFoundException, SQLException {

        //must validate these values being passed in 
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    public void closeConnection() throws SQLException {
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
        //will learn solution in another lesson after 9/26/017
        if (maxRecords > ALL_RECORDS) {
            sql = "select * from " + tableName + " limit " + maxRecords;
        } else {
            sql = "select * from " + tableName;
        }

        openConnection();
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
        closeConnection();

        return rawData;
    }

    public final void deleteSelectRecords(String tableName, String colName, List<Integer> primaryKeys)
            throws IllegalArgumentException, ClassNotFoundException, SQLException {

        if (tableName == null || tableName.isEmpty()) {
            throw new IllegalArgumentException("You must define a table name from which to delete records.");
        }
        if (colName == null || colName.isEmpty()) {
            throw new IllegalArgumentException("You must define a the column from which you would like to delete records.");
        }
        if (primaryKeys.size() <= 0) {
            throw new IllegalArgumentException("You must provide one or more valid primary keys in order to delete any records.");
        }

        openConnection();
        stmt = conn.createStatement();
        for (int pk : primaryKeys) {

            String sqlDelete = "delete from " + tableName + " where " + colName + " = " + pk;
            stmt.executeUpdate(sqlDelete);
        }
        closeConnection();       

    }

    public final String getDriverClass() {
        return driverClass;
    }

    public final void setDriverClass(String driverClass) {
        //validate this method
        this.driverClass = driverClass;
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        //validate this method
        this.url = url;
    }

    public final String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        //validate this method
        this.userName = userName;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        //validate this method
        this.password = password;
    }

    /*
    MAIN METHOD FOR TESTING
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataAccess db = new MySqlDataAccess(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin"
        );

        //Test for getAllRecords()
        System.out.println("Test getAllRecords:");
        List<Map<String, Object>> list = db.getAllRecords("author", 0);

        for (Map<String, Object> rec : list) {
            System.out.println(rec);
        }

        //Test for deleteSelectRecords()
        System.out.println("Test deleteSelectRecords:");
        List<Integer> deletePKs = new ArrayList<Integer>();
        deletePKs.add(4);
        db.deleteSelectRecords("author", "author_id", deletePKs);
        
        List<Map<String, Object>> delCheck = db.getAllRecords("author", 0);

        for (Map<String, Object> rec : delCheck) {
            System.out.println(rec);
        }
    }
}
