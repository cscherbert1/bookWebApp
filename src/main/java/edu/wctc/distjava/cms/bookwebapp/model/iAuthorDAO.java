package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cscherbert1
 */
public interface iAuthorDAO {

    public List<Author> getListOfAuthors() 
            throws SQLException, ClassNotFoundException;

    public void removeAuthors(List<Author> authors)
            throws SQLException, ClassNotFoundException;

}
