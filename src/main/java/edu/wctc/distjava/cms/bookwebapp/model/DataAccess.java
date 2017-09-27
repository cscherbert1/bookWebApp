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
    List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException;

    String getDriverClass();

    String getPassword();

    String getUrl();

    String getUserName();

    //get db connection
    //encapsulate complexity of creating url, entering login info, etc
    void openConnection() throws ClassNotFoundException, SQLException;

    void setDriverClass(String driverClass);

    void setPassword(String password);

    void setUrl(String url);

    void setUserName(String userName);
    
}
