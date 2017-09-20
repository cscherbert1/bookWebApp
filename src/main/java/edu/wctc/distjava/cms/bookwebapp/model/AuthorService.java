package edu.wctc.distjava.cms.bookwebapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cscherbert1
 */
public class AuthorService {
    
    public List<Author> getAuthorList(){
        //this is "faked"/ hard coded data. In future, this method will get author values from DB
        return Arrays.asList(
                new Author(1,"Mark Twain", new Date()),
                new Author(2,"Stephen King", new Date()),
                new Author(3,"JK Rowling", new Date())
        );
        
    }
}
