/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.cms.bookwebapp.model;

import edu.wctc.distjava.cms.bookwebapp.repository.AuthorRepository;
import edu.wctc.distjava.cms.bookwebapp.repository.BookRepository;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author cscherbert1
 */
@Service
public class BookService {

    @Autowired //spring annotation. Can also use the @Inject --> a Java EE tag that is supported by Spring
    private BookRepository bookRepo;
    
    @Autowired
    private AuthorRepository authorRepo;

    public BookService() {

    }
    
    public List<Book> findAll(){
        return bookRepo.findAll();
    }
    
    public Book findById(String id) throws DataAccessException{
        Book book = bookRepo.findOne(Integer.parseInt(id));
        return book;
    }
    
    public void addBook(String title, String isbn, String authorId){
        
        Author author = authorRepo.findOne(Integer.parseInt(authorId));
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthor(author);
        
        bookRepo.save(book);
    }
    
    public void removeBookById(String id)
        throws SQLException, ClassNotFoundException, NumberFormatException{
        if(id == null || Integer.parseInt(id) <= 0){
            throw new IllegalArgumentException("Id must be an integer greater than 0");
        }
        Integer value = Integer.parseInt(id);
        bookRepo.delete(value);        
        
//        int recordsDeleted = 0;
//        String jpql = "DELETE FROM Book b WHERE b.bookId = :id";
//        Query q = getEm().createQuery(jpql);
//        q.setParameter("id", value);
//        recordsDeleted = q.executeUpdate();
//        
//        return recordsDeleted;
    }
    
    public void updateBook(String bookId, String title, String isbn, String authorId){
        //validation
        
        //logic
        //Find the book using the Id
        Book book = findById(bookId);
        
        //find the author Object using the Id
        Author author = authorRepo.findOne(Integer.parseInt(authorId));
        
        //save the Book fields, including Author
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthor(author);
        bookRepo.save(book);
        
//        Book book = getEm().find(Book.class, new Integer(bookId));
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        Author author = getEm().find(Author.class, new Integer(authorId));
//        book.setAuthor(author);
//        getEm().merge(book);
        
    }
    
    //Old version of this method used w/ JPA, not Spring. 
//    public void addBook(String title, String isbn, String authorId){
//        //validation
//        
//        //logic
//        Book book = new Book();
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        Author author = getEm().find(Author.class, new Integer(authorId));
//        book.setAuthor(author);
//        getEm().merge(book);
//    }

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
