package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author cscherbert1
 */
public class AuthorService {

    private iAuthorDAO authorDao;

    public AuthorService(iAuthorDAO authorDao) {
        setAuthorDao(authorDao);
    }

    public final List<Author> getAuthorList()
            throws SQLException, ClassNotFoundException {

        return authorDao.getListOfAuthors();

        //this is "faked"/ hard coded data. In future, this method will get author values from DB
//        return Arrays.asList(
//                new Author(1,"Mark Twain", new Date()),
//                new Author(2,"Stephen King", new Date()),
//                new Author(3,"JK Rowling", new Date())
//        );
    }
    
    public final void removeAuthors(List<Author> authors) 
            throws SQLException, ClassNotFoundException{
        
        authorDao.removeAuthors(authors);
    }

    public iAuthorDAO getAuthorDao() {
        return authorDao;
    }

    //do validation /final
    public void setAuthorDao(iAuthorDAO authorDao) {
        this.authorDao = authorDao;
    }
    
    
    /*
    Main method to test. Comment out before production
    */

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        iAuthorDAO dao = new AuthorDAO("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
                        "root", "admin")
        );

        AuthorService authorService = new AuthorService(dao);

        //test getAuthorList();
        System.out.println("Test getAuthorList:");
        List<Author> list = authorService.getAuthorList();

        for (Author a : list) {
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
                    + ", " + a.getDateAdded() + "\n");
        }
        
        //test removeAuthors();
        System.out.println("Test removeAuthors:");
        List<Author> delAuths = new Vector();
        delAuths.add(new Author(6));
        
        authorService.removeAuthors(list);
        
        List<Author> delAuthsTest = authorService.getAuthorList();

        for (Author a : delAuthsTest) {
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
                    + ", " + a.getDateAdded() + "\n");
        }
    }

}
