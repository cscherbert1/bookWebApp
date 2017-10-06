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
    
    //could wrap all 3 exceptions into 1 custom exception as seen in adv. Java
    public final int removeAuthorById(String id) //the value passed in will come from a web form, which always returns strings, so this should be your input 
            throws SQLException, ClassNotFoundException, NumberFormatException{
        
        if(id == null){
            throw new IllegalArgumentException("Id must be an integer greater than Zero.");
        }
        
        Integer value = Integer.parseInt(id); //throws number format exception
        
        return authorDao.removeAuthorById(value);
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
                new MySqlDataAccess()
        );

        AuthorService authorService = new AuthorService(dao);

        //test getAuthorList();
        System.out.println("Test getAuthorList:");
        List<Author> list = authorService.getAuthorList();

        for (Author a : list) {
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
                    + ", " + a.getDateAdded() + "\n");
        }
        
        System.out.println("test removeAuthorById:");
        int recsDeleted = authorService.removeAuthorById("11");
        List<Author> singleDelTestList = authorService.getAuthorList();        
        for (Author a : singleDelTestList) {
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
                    + ", " + a.getDateAdded() + "\n");
        }
        
        //test removeAuthors();
//        System.out.println("Test removeAuthors:");
//        List<Author> delAuths = new Vector();
//        Author author1 = new Author(1,"Author1", new Date());
//        Author author2 = new Author(2, "Author2", new Date());
//        
//        delAuths.add(author1);
//        delAuths.add(author2);
//        
//        authorService.removeAuthors(delAuths);
//        
//        List<Author> delAuthsTest = authorService.getAuthorList();
//
//        for (Author a : delAuthsTest) {
//            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
//                    + ", " + a.getDateAdded() + "\n");
//        }
    }

}
