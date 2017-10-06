package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cscherbert1
 */
public interface iAuthorDAO {

    public abstract List<Author> getListOfAuthors() 
            throws SQLException, ClassNotFoundException;
    
    public abstract int removeAuthorById(Integer id)
        throws SQLException, ClassNotFoundException;

}
