/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cscherbert1
 */
@Stateless
public class BookService extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public BookService() {
        super(Book.class);
    }
    
    public int removeBookById(String id)
        throws SQLException, ClassNotFoundException, NumberFormatException{
        if(id == null || Integer.parseInt(id) <= 0){
            throw new IllegalArgumentException("Id must be an integer greater than 0");
        }
        Integer value = Integer.parseInt(id);
        int recordsDeleted = 0;
        String jpql = "DELETE FROM Book b WHERE b.bookId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", value);
        recordsDeleted = q.executeUpdate();
        
        return recordsDeleted;
    }
    
    public void updateBook(String bookId, String title, String isbn, String authorId){
        //validation
        
        //logic
        //Find the book using the Id
        Book book = getEm().find(Book.class, new Integer(bookId));
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        getEm().merge(book);
        
    }
    
    public void addBook(String title, String isbn, String authorId){
        //validation
        
        //logic
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        getEm().merge(book);
    }

    //Example of combined method to add and update books. 
    //May not be an appropriate solution, but demonstrates possibility for creativity in code
    
//    public void addOrBupdateBook(String bookId, String title, String isbn, String authorId) {
//        Book book = null;
//
//        if (bookId == null || bookId.isEmpty()) {
//            //must be new record  
//            book = new Book();
//
//        } else {
//            //must be updated record
//            book = new Book(new Integer(bookId));
//        }
//
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        //find author
//        Author author = getEm().find(Author.class, new Integer(authorId));
//        book.setAuthor(author);
//        //getEm().persist(book);
//        getEm().merge(book);
//
//    }

}
