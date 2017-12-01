//package edu.wctc.distjava.cms.bookwebapp.model;
//
//import java.io.Serializable;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Vector;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//
//@Stateless
//public class AuthorService_Old implements Serializable {
//
//    //private iAuthorDAO authorDao;
//
//    @PersistenceContext(unitName = "book_PU")
//    private EntityManager em;
//    
//    public AuthorService_Old() {
//    }
//
//    /*
//    Better to catch exceptions in a more robust manner, but this is faster for now. 
//    See class 20 lecture recording for description of what a better strategy may be. 
//    */
//    public List<Author> getAuthorList() throws Exception{
//
//        List<Author> authorList = new ArrayList<>();
//        String jpql = "select a from Author a";
//        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
//        //q.setMaxResults(500);  //Optional
//        authorList = q.getResultList();
//
//        return authorList;
//    }
//    
//    public Author findOneAuthorById(Object authorId) 
//            throws SQLException, ClassNotFoundException{
//        //validation
//        if (authorId == null){
//            throw new IllegalArgumentException("You must provide a valid author Id");
//        }
//        
//        //logic
////        return authorDao.findOneAuthorById(authorId);
//        int id = Integer.parseInt(authorId.toString());
//        Author author = getEm().find(Author.class, id);
//        return author;
//        
//    }
//    
//    //could wrap all 3 exceptions into 1 custom exception as seen in adv. Java
//    public int removeAuthorById(String id) //the value passed in will come from a web form, which always returns strings, so this should be your input 
//            throws SQLException, ClassNotFoundException, NumberFormatException{
//        
//        if(id == null || Integer.parseInt(id) <= 0){
//            throw new IllegalArgumentException("Id must be an integer greater than Zero.");
//        }
//        
//        Integer value = Integer.parseInt(id); //throws number format exception
//        int recordsDeleted = 0;
//        String jpql = "DELETE FROM Author a WHERE a.authorId = :id";
//        Query q = getEm().createQuery(jpql);
//        q.setParameter("id", value);
//        recordsDeleted = q.executeUpdate();
//        
////        return authorDao.removeAuthorById(value);
//        return recordsDeleted;
//    }      
//        
//    public void addAuthor(String authorName)
//            throws ClassNotFoundException, SQLException{
//        
//        //validation
//
//        
//        //logic
//        //return authorDao.addAuthor(colNames, colValues);
//        
//        Author author = new Author();
//        author.setAuthorName(authorName);
//        author.setDateAdded(new Date());
//        
//        getEm().merge(author);
//        
//    }
//    
//    public void updateAuthor(String authorName, String pkValue)
//            throws ClassNotFoundException, SQLException{
//        
//        //validation
//        
//        //Add for authorName and dateAdded
//
//        if (Integer.parseInt(pkValue) <= 0 || Integer.parseInt(pkValue) > Integer.MAX_VALUE)
//            throw new IllegalArgumentException("You must provide a valid Author Id to update any records.");
//        
//        //logic
//        //return authorDao.updateAuthorById(colNames, colValues, pkValue);
//        
////        Date dateAdded = new Date();
////        String jpql = "UPDATE Author a SET a.authorName = '" + authorName +
////                "', a.dateAdded = '" + dateAdded + "'WHERE a.authorId = '" + id + "'";
////        Query q = getEm().createQuery(jpql);
////        recordsUpdated = q.executeUpdate();   
//        
//        Author tempAuth = findOneAuthorById(pkValue);
//        tempAuth.setAuthorName(authorName);
////        tempAuth.setDateAdded(dateAdded);
//        
//        getEm().merge(tempAuth);
//                 
//    }
//    
//    /**
//     * 
//     * @return em
//     */
//    public EntityManager getEm() {
//        return em;
//    }
//
//    /**
//     * 
//     * @param em 
//     */
//    public void setEm(EntityManager em) {
//        //validation
//        
//        //logic
//        this.em = em;
//    }
//    
//
////    public iAuthorDAO getAuthorDao() {
////        return authorDao;
////    }
////
////    //do validation /final
////    public void setAuthorDao(iAuthorDAO authorDao) {
////        this.authorDao = authorDao;
////    }
//    
//    
//    
//    
//    /*
//    Main method to test. Comment out before production
//    */
//       
//
////    public static void main(String[] args)
////            throws SQLException, ClassNotFoundException {
////
////        iAuthorDAO dao = new AuthorDAO("com.mysql.jdbc.Driver",
////                "jdbc:mysql://localhost:3306/book",
////                "root", "admin",
////                new MySqlDataAccess()
////        );
////
//////        AuthorService_Old authorService = new AuthorService_Old(dao);
////
////        //test getAuthorList();
////        System.out.println("Test getAuthorList:");
////        List<Author> list = authorService.getAuthorList();
////
////        for (Author a : list) {
////            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
////                    + ", " + a.getDateAdded() + "\n");
////        }
////        
////        //test findOneAuthorById()
////        System.out.println("Test findOneAuthorById: ");
////        Author author = authorService.findOneAuthorById(7);
////        System.out.println(author.getAuthorId() + ", " + author.getAuthorName()
////                + ", " + author.getDateAdded() + "\n");
////
////        
//        //test updateAuthorById
////        System.out.println("Test updateAuthorById: ");
////        int recsUpdated = authorService.updateAuthor(Arrays.asList("author_name", "date_added"), Arrays.asList("Gus Ramirez", "2017-10-07"), 10);
////        System.out.println("Records Updated: " + recsUpdated);
////        
////        List<Author> updateAuthorTestList = authorService.getAuthorList();
////
////        for (Author a : updateAuthorTestList) {
////            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
////                    + ", " + a.getDateAdded() + "\n");
////        }
//        
//        //test addAuthor
////        System.out.println("Test addAuthor:");
////        int recsAdded = authorService.addAuthor(Arrays.asList("author_name, date_added"), Arrays.asList("Agatha Christie", "2017-10-08"));
////        System.out.println("Added Records: " + recsAdded);
////        
////        List<Author> addAuthorTestList = authorService.getAuthorList();
////
////        for (Author a : addAuthorTestList) {
////            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
////                    + ", " + a.getDateAdded() + "\n");
////        }
//        
//        //test removeAuthorById();
////        System.out.println("test removeAuthorById:");
////        int recsDeleted = authorService.removeAuthorById("11");
////        System.out.println(recsDeleted);
////        List<Author> singleDelTestList = authorService.getAuthorList();        
////        for (Author a : singleDelTestList) {
////            System.out.println(a.getAuthorId() + ", " + a.getAuthorName()
////                    + ", " + a.getDateAdded() + "\n");
////        }
//        
////    }
//
//}
