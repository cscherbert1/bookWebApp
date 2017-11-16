/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.SQLException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cscherbert1
 * 
 */
@Stateless
public class AuthorService extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public AuthorService() {
        super(Author.class);
    }
    
    public int removeAuthorById(String id) 
            throws SQLException, ClassNotFoundException, NumberFormatException{
        
        if(id == null || Integer.parseInt(id) <= 0){
            throw new IllegalArgumentException("Id must be an integer greater than Zero.");
        }
        
        Integer value = Integer.parseInt(id);
        int recordsDeleted = 0;
        String jpql = "DELETE FROM Author a WHERE a.authorId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", value);
        recordsDeleted = q.executeUpdate();

        return recordsDeleted;
    } 
    
    public void updateAuthor(String authorName, String pkValue)
            throws ClassNotFoundException, SQLException{
        
        //validation
        
        //Add for authorName and dateAdded

        if (Integer.parseInt(pkValue) <= 0 || Integer.parseInt(pkValue) > Integer.MAX_VALUE)
            throw new IllegalArgumentException("You must provide a valid Author Id to update any records.");
        
        //logic
        //return authorDao.updateAuthorById(colNames, colValues, pkValue);
        
//        Date dateAdded = new Date();
//        String jpql = "UPDATE Author a SET a.authorName = '" + authorName +
//                "', a.dateAdded = '" + dateAdded + "'WHERE a.authorId = '" + id + "'";
//        Query q = getEm().createQuery(jpql);
//        recordsUpdated = q.executeUpdate();   
        int id = Integer.parseInt(pkValue);
        Author tempAuth = findById(id);
        tempAuth.setAuthorName(authorName);
//        tempAuth.setDateAdded(dateAdded);
        
        getEm().merge(tempAuth);
                 
    }
    
    public void addAuthor(String authorName)
            throws ClassNotFoundException, SQLException{
        
        //validation

        
        //logic
        //return authorDao.addAuthor(colNames, colValues);
        
        Author author = new Author();
        author.setAuthorName(authorName);
        author.setDateAdded(new Date());
        
        getEm().merge(author);
        
    }
    
}
