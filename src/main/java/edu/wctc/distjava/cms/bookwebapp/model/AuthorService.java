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
    }
    
    public final Author findOneAuthorById(Object authorId) 
            throws SQLException, ClassNotFoundException{
        //validation
        if (authorId == null){
            throw new IllegalArgumentException("You must provide a valid author Id");
        }
        
        //logic
        return authorDao.findOneAuthorById(authorId);
        
    }
    
    //could wrap all 3 exceptions into 1 custom exception as seen in adv. Java
    public final int removeAuthorById(String id) //the value passed in will come from a web form, which always returns strings, so this should be your input 
            throws SQLException, ClassNotFoundException, NumberFormatException{
        
        if(id == null || Integer.parseInt(id) <= 0){
            throw new IllegalArgumentException("Id must be an integer greater than Zero.");
        }
        
        Integer value = Integer.parseInt(id); //throws number format exception
        
        return authorDao.removeAuthorById(value);
    }
    
    public final int addAuthor(List<String> colNames, List<Object>colValues)
            throws ClassNotFoundException, SQLException{
        
        //validation --> Dont need to do validation. The things that goe wrong will all be caught by exceptions, so by the time my code catches things, it will already be thrown
        if(colNames == null)
            throw new IllegalArgumentException("You must provide valid column names to be updated.");
        if(colValues == null)
            throw new IllegalArgumentException("You must provide appropriate values for each colum to be updated.");
        
        //logic
        return authorDao.addAuthor(colNames, colValues);
        
    }
    
    public final int updateAuthor(List<String> colNames, List<Object> colValues, 
            int pkValue) throws ClassNotFoundException, SQLException{
        
        //validation
        if (colNames == null) {
            throw new IllegalArgumentException("You must provide valid column names to be updated.");
        }
        if (colValues == null) {
            throw new IllegalArgumentException("You must provide appropriate values for each colum to be updated.");
        }
        if (pkValue <= 0 || pkValue > Integer.MAX_VALUE)
            throw new IllegalArgumentException("You must provide a valid Author Id to update any records.");
        
        //logic
        return authorDao.updateAuthorById(colNames, colValues, pkValue);
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
        
        //test findOneAuthorById()
        System.out.println("Test findOneAuthorById: ");
        Author author = authorService.findOneAuthorById(7);
        System.out.println(author.getAuthorId() + ", " + author.getAuthorName()
                + ", " + author.getDateAdded() + "\n");

        
        //test updateAuthorById
//        System.out.println("Test updateAuthorById: ");
//        int recsUpdated = authorService.updateAuthor(Arrays.asList("author_name", "date_added"), Arrays.asList("Gus Ramirez", "2017-10-07"), 10);
//        System.out.println("Records Updated: " + recsUpdated);
//        
//        List<Author> updateAuthorTestList = authorService.getAuthorList();
//
//        for (Author a : updateAuthorTestList) {
//            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
//                    + ", " + a.getDateAdded() + "\n");
//        }
        
        //test addAuthor
//        System.out.println("Test addAuthor:");
//        int recsAdded = authorService.addAuthor(Arrays.asList("author_name, date_added"), Arrays.asList("Agatha Christie", "2017-10-08"));
//        System.out.println("Added Records: " + recsAdded);
//        
//        List<Author> addAuthorTestList = authorService.getAuthorList();
//
//        for (Author a : addAuthorTestList) {
//            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
//                    + ", " + a.getDateAdded() + "\n");
//        }
        
        //test removeAuthorById();
//        System.out.println("test removeAuthorById:");
//        int recsDeleted = authorService.removeAuthorById("11");
//        System.out.println(recsDeleted);
//        List<Author> singleDelTestList = authorService.getAuthorList();        
//        for (Author a : singleDelTestList) {
//            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
//                    + ", " + a.getDateAdded() + "\n");
//        }
        
    }

}
