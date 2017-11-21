/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.cms.bookwebapp.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
        public void addOrBupdateBook(String bookId, String title, String isbn, String authorId){
        Book book = null;
        
        if(bookId == null || bookId.isEmpty()){
            //must be new record  
            book = new Book();
 
        } else {
            //must be updated record
            book = new Book(new Integer(bookId));
        }
        
        book.setTitle(title); 
        book.setIsbn(isbn);
        //find author
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        //getEm().persist(book);
        getEm().merge(book);


    }
    
}
