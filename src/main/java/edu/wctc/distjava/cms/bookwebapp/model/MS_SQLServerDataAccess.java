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
public class MS_SQLServerDataAccess {  //implements DataAccess
    
//    private final int ALL_RECORDS = 0;
//    
//    private Connection conn;
//    private Statement stmt;
//    private ResultSet rs;
//    private String driverClass;
//    private String url;
//    private String userName;
//    private String password;
//
//    public MS_SQLServerDataAccess(String driverClass, String url,
//            String userName, String password){
//        //validate all of these input parameters for null
//        
////        if(driverClass == null || driverClass.length() < 5){
////            
////        }
//
//        setDriverClass(driverClass);
//        setUrl(url);
//        setUserName(userName);
//        setPassword(password);
//    }
//    //get db connection
//    //encapsulate complexity of creating url, entering login info, etc
//    @Override
//    public void openConnection() throws ClassNotFoundException, SQLException{
//        
//        //must validate these values being passed in 
//        
//        Class.forName(driverClass);
//        conn = DriverManager.getConnection(url, userName, password);
//    }
//    
//    @Override
//    public void closeConnection() throws SQLException{
//        if(conn != null)
//            conn.close();
//    }
//    
//    /**
//     * Returns records from a table. Requires an open connection
//     * 
//     * @param tableName
//     * @param maxRecords
//     * @return rawData
//     * @throws SQLException 
//     */
//    @Override
//    public final List<Map<String,Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException{
//        
//        //don't use an arrayList, it's not thread safe. use Vector instead, even tho it's an old collection
//        List<Map<String,Object>> rawData = new Vector<>();
//        
//        String sql = "";   
//        
//        //be aware that this code block in vulnerable to sql injection
//        //will learn solution in another lesson after 9/26/017
//        if(maxRecords > ALL_RECORDS){
//            sql= "select TOP " + maxRecords + " * from " + tableName;
//        } else {
//            sql = "select * from " + tableName;
//        }
//        
//        openConnection();
//        stmt = conn.createStatement();
//        rs = stmt.executeQuery(sql);
//        
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int colCount = rsmd.getColumnCount(); //be careful, this is 1 based numbering, not 0 based
//        
//        Map<String, Object> record = null; 
//        
//        while(rs.next()){
//            record = new LinkedHashMap<>();            
//            for(int colNum = 1; colNum <= colCount; colNum++){
//                              //record key put in map,  record value
//                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
//            }
//            rawData.add(record);
//        }
//        closeConnection();
//        
//        return rawData;
//    }
//    
//    @Override
//    public void deleteSelectRecords(String author, String author_id, List<Integer> deletePKs) throws IllegalArgumentException, ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    @Override
//    public final String getDriverClass() {
//        return driverClass;
//    }
//
//    @Override
//    public final void setDriverClass(String driverClass) {
//        //validate this method b/c of
//        this.driverClass = driverClass;
//    }
//
//    @Override
//    public final String getUrl() {
//        return url;
//    }
//
//    @Override
//    public final void setUrl(String url) {
//        this.url = url;
//    }
//
//    @Override
//    public final String getUserName() {
//        return userName;
//    }
//
//    @Override
//    public final void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    @Override
//    public final String getPassword() {
//        return password;
//    }
//
//    @Override
//    public final void setPassword(String password) {
//        this.password = password;
//    }
//    
//    
////    public static void main(String[] args) throws SQLException, ClassNotFoundException {
////        DataAccess db = new MySqlDataAccess(
////                "org.apache.derby.jdbc.ClientDriver",
////                "jdbc:derby://localhost:1527/sample",
////                "app",
////                "app"
////        );
////        
////        List<Map<String,Object>> list = db.getAllRecords("CUSTOMER", 0);
////        
////        for(Map<String,Object> rec : list){
////            System.out.println(rec);
////        }
////    }
//
//    @Override
//    public final int deleteRecordById(String tableName, String pkColName, Object pkValue)
//            throws ClassNotFoundException, SQLException{
//        
//        //validate parameters
//        String sql = "DELETE FROM " + tableName +
//                    " WHERE " + pkColName + " = ";
//        
//        if(pkValue instanceof String){
//            sql += "'" + pkValue.toString() + "'";
//            
//        } else { //if NOT string, must be a number, so use Long Integer to capture either int or long (the only 2 options)
//            sql += Long.parseLong(pkValue.toString());
//        }
//        
//        openConnection();
//        stmt = conn.createStatement();
//        int recsDeleted = stmt.executeUpdate(sql);
//        closeConnection();
//        
//        return recsDeleted;
//        
//    }
//


    
    
}
