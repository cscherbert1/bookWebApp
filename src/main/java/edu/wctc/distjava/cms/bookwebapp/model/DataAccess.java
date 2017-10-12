/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cscherbert1
 */
public interface DataAccess {

    void closeConnection() throws SQLException;

    /**
     * Returns records from a table. Requires an open connection
     *
     * @param tableName
     * @param maxRecords
     * @return rawData
     * @throws SQLException
     */
    public abstract List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException, ClassNotFoundException;
    
    public abstract List<Map<String, Object>> findRecordById(String tableName, 
            String pkColName, Object pkValue)
            throws SQLException;

    //get db connection
    //encapsulate complexity of creating url, entering login info, etc
    public abstract void openConnection(String driverClass, String url, String userName,
            String password)
            throws ClassNotFoundException, SQLException;

    public abstract int deleteRecordById(String tableName, String pkColName, Object pkValue)
            throws ClassNotFoundException, SQLException;

    public abstract int createRecord(String tableName, List<String> colNames,
            List<Object> colValues)
            throws SQLException;
    
    public abstract int updateRecordById(String tableName, List<String> colNames,
            List<Object> colValues, String pkColName, Object pkValue)
            throws SQLException;

}
