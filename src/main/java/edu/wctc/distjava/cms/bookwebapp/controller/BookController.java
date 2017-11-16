package edu.wctc.distjava.cms.bookwebapp.controller;

import edu.wctc.distjava.cms.bookwebapp.model.Book;
import edu.wctc.distjava.cms.bookwebapp.model.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cscherbert1
 */
@WebServlet(name = "BookController", urlPatterns = {"/bc"})
public class BookController extends HttpServlet {
    
    public static final String ACTION = "action";
    public static final String BOOK_ID = "bookId";
    public static final String LIST_ACTION = "list";
    public static final String ADD_ACTION = "add";
    public static final String DELETE_ACTION = "delete";
    public static final String EDIT_ACTION = "edit";
    public static final String SUBMIT_BOOK_ACTION = "submitbook";
    public static final String EDIT_BOOK_ACTION = "editbook";
    
    public static final String TITLE = "title";
    public static final String ISBN = "isbn";
    
    public static final String DESTINATION_HOME = "/index.jsp";
    public static final String DESTINATION_BOOKLIST = "/bookList.jsp";
    public static final String DESTINATION_ERROR = "/error.jsp";
    
    @EJB
    private BookService bookService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String destination = DESTINATION_BOOKLIST; //default
        
        try{
            
            List<Book> bookList = null;
            String action = request.getParameter(ACTION);

            //Home
            if(action.equalsIgnoreCase(DESTINATION_HOME)){
                
                destination = DESTINATION_HOME;
            }
            
            //List
            if (action.equalsIgnoreCase(LIST_ACTION)) {

                getBookList(bookList, bookService, request);

            }
            
        }catch (Exception e){
             destination = DESTINATION_ERROR;
            request.setAttribute("errorMessage", e.getMessage());
        }
        
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }
    
    private void getBookList(List<Book> bookList,
            BookService bookService, HttpServletRequest request)
            throws SQLException, ClassNotFoundException, Exception {

        bookList = bookService.findAll();
        request.setAttribute("bookList", bookList);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
