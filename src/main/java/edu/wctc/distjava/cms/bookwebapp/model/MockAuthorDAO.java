package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author cscherbert1
 */
public class MockAuthorDAO implements iAuthorDAO {

    public MockAuthorDAO() {

    }

    @Override
    public List<Author> getListOfAuthors()
            throws SQLException, ClassNotFoundException {

        List<Author> list = Arrays.asList(
            new Author (1,"John Doe", new Date()),
            new Author (2, "Bob Smith", new Date())
        );
        
        return list;
    }

    @Override
    public int removeAuthorById(Integer id) throws SQLException, ClassNotFoundException {
        return 1;
    }

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {
        
//        iAuthorDAO dao = new AuthorDAO("com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root", "admin",
//                new MySqlDataAccess("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
//                        "root", "admin")
//        );

//      ****** OR ************

        iAuthorDAO dao =new MockAuthorDAO();

        List<Author> list = dao.getListOfAuthors();
        
        for(Author a: list) {
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() + 
                    ", " + a.getDateAdded() + "\n");
        }

    }


}
