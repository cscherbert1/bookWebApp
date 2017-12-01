/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.cms.bookwebapp.model;

import edu.wctc.distjava.cms.bookwebapp.repository.AuthorRepository;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
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
 * 
 */
@Service
public class AuthorService{

    @Autowired
    private AuthorRepository authorRepo;
    
    public AuthorService() {
        
    }
    
    //eg method using Spring repositiory to do CRUD operations
    public List<Author> findAll(){
        return authorRepo.findAll();
    }
    
    public Author findById(String id) throws DataAccessException{
        Author author = authorRepo.findOne(Integer.parseInt(id));
        return author;
    }
    
    //save method 
    public void addAuthor(String authorName){
        Date date = new Date();
        Author author = new Author();
        author.setAuthorName(authorName);
        author.setBookSet(new HashSet());
        author.setDateAdded(date);
        
        /*
        SaveAndFlush is for non-transactional methods. No rollback, etc. 
        For transactions, use .save()
        This syncs cache w/ DB right away. 
        */
        authorRepo.save(author); //adds and works for updates as well. Uses Merge from JPA

    }
    
    public void removeAuthorById(String id) 
            throws SQLException, ClassNotFoundException, NumberFormatException{
        
        if(id == null || Integer.parseInt(id) <= 0){
            throw new IllegalArgumentException("Id must be an integer greater than Zero.");
        }
        
        Integer value = Integer.parseInt(id);
        authorRepo.delete(value);
        
//        String jpql = "DELETE FROM Author a WHERE a.authorId = :id";
//        Query q = getEm().createQuery(jpql);
//        q.setParameter("id", value);
//        recordsDeleted = q.executeUpdate();      

    } 
    
    
    public void updateAuthor(String id, String authorName)
            throws ClassNotFoundException, SQLException{
        
        Author author = findById(id);
        author.setAuthorName(authorName);
        authorRepo.save(author);
        
        //validation
        
        //Add for authorName and dateAdded

//        if (Integer.parseInt(pkValue) <= 0 || Integer.parseInt(pkValue) > Integer.MAX_VALUE)
//            throw new IllegalArgumentException("You must provide a valid Author Id to update any records.");
//        
        //logic
        //return authorDao.updateAuthorById(colNames, colValues, pkValue);
        
//        Date dateAdded = new Date();
//        String jpql = "UPDATE Author a SET a.authorName = '" + authorName +
//                "', a.dateAdded = '" + dateAdded + "'WHERE a.authorId = '" + id + "'";
//        Query q = getEm().createQuery(jpql);
//        recordsUpdated = q.executeUpdate();   
//        int id = Integer.parseInt(pkValue);
//        Author tempAuth = findById(id);
//        tempAuth.setAuthorName(authorName);
////        tempAuth.setDateAdded(dateAdded);
//        
//        getEm().merge(tempAuth);
                 
    }

    
}
