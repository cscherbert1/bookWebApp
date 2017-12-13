<%-- 
    Document   : bookList
    Created on : Nov 15, 2017, 7:56:58 PM
    Author     : cscherbert1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Bootstrap minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
        <title>Book List</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="conatiner-fluid">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2">

                    <h1>Book List</h1>       

                    <div class="table-responsive">
                        <table class="table-striped table-hover" style="width:100%;">
                            <tr>
                                <th>Title</th>
                                <th>ISBN</th>
                                <th>Author</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <c:forEach var="b" items="${bookList}">
                                <tr>                    
                                    <td>${b.title}</td>
                                    <td>${b.isbn}</td>
                                    <td>${b.author.authorName}</td>
                                    <td><input type="button" class="btn btn-info text-center" value="Edit" 
                                               onclick="location.href = 'bc?action=edit&bookId=${b.bookId}'"</td>
                                    <td><input type="button" class="btn btn-danger text-center" value="Delete" 
                                               onclick="location.href = 'bc?action=delete&bookId=${b.bookId}'"</td>
                                </tr>                
                            </c:forEach>
                        </table>
                    </div>

                    <br>
                    <input type="button" class="btn btn-primary" value="Add" onclick="location.href = 'bc?action=add'">


                    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
                    <script
                        src="https://code.jquery.com/jquery-3.2.1.min.js"
                        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
                        crossorigin="anonymous">
                    </script>
                    <!-- Bootstrap minified JavaScript -->
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

                </div>
            </div>

        </div>
    </body>
</html>
