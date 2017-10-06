package edu.wctc.distjava.cms.bookwebapp.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author cscherbert1
 */
public class AuthorDAO implements iAuthorDAO {

    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataAccess db;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";

    public AuthorDAO(String driverClass, String url,
            String userName, String password, DataAccess db) {

        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }

    @Override
    public List<Author> getListOfAuthors()
            throws SQLException, ClassNotFoundException {
        
        db.openConnection(driverClass, url, userName, password);

        List<Author> list = new Vector<>(); //vector is threadsafe

        List<Map<String, Object>> rawData = db.getAllRecords("author", 0);

        Author author = null;
        for (Map<String, Object> rec : rawData) {
            author = new Author();

            //data validation
            Object objRecId = rec.get("author_id");
            Integer recId = objRecId == null
                    ? 0 : Integer.parseInt(objRecId.toString());

            //set "into" the Author obj
            author.setAuthorId(recId);

            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);

            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
            author.setDateAdded(recAdded);

            //this is fragile. if we spell column name wrong, it will break. See code above to fix/ replace
            //remember that some datatypes differ btwn db and oop language, relies on programmer knowledge to ensure values are the correct type
            //this also assumes that we will always get data back and that the Author obj will accept nulls. No constraints used
//            author.setAuthorId(Integer.parseInt(rec.get("author_id").toString())); 
//            author.setAuthorName(rec.get("author_name").toString());
//            author.setDateAdded((Date)rec.get("date_added"));
            list.add(author);
        }
        
        db.closeConnection();
        
        return list;
    }
    
    public final int removeAuthorById(Integer id)
        throws SQLException, ClassNotFoundException{
        
        //validation here
        if(id == null || id < 1){
            throw new IllegalArgumentException("Id must be an integer greater than Zero.");
        }
        
        db.openConnection(driverClass, url, userName, password);
        int recsDeleted =  db.deleteRecordById(AUTHOR_TBL, AUTHOR_PK, id);
        db.closeConnection();
        
        return recsDeleted;
        
        
    }
    
    public int addAuthor(List<String> colNames, List<Object>colValues){
        
        return 0;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {
        //this list of Author objects is needed to test the remove Authors method.
        //A list of Authors will be created during the first test
        List<Author> tempAuthors = new Vector();        
        
        //Test getListOfAuthors()
        System.out.println("Test getListOfAuthors:");
        AuthorDAO dao = new AuthorDAO("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess()
        );

        List<Author> list = dao.getListOfAuthors();
        
        for(Author a: list) {
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() + 
                    ", " + a.getDateAdded() + "\n");
            if(a.getAuthorId() == 6){
                Author tempAuth = new Author(a.getAuthorId(), a.getAuthorName(), a.getDateAdded());
                tempAuthors.add(tempAuth);
            }
        }
        
        //single pk del
        System.out.println("Test removeAuthorById:");
        int recsDeleted = dao.removeAuthorById(7);
        List<Author> removeTestList = dao.getListOfAuthors();
        
        for(Author a: removeTestList) {
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() + 
                    ", " + a.getDateAdded() + "\n");
        }
        
//        //test removeAuthors()
//        System.out.println("Test removeAthors:");
//        dao.removeAuthors(tempAuthors);
//        
//        List<Author> removeTestList = dao.getListOfAuthors();
//        
//        for(Author a: removeTestList) {
//            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() + 
//                    ", " + a.getDateAdded() + "\n");
//        }
        
        

    }
}
