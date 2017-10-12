<%-- 
    Document   : authorList
    Created on : Sep 19, 2017, 8:35:53 PM
    Author     : cscherbert1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>

    </head>
    <body>
        <h1>Author List</h1>       

        <table border="1">
            <c:forEach var="a" items="${authorList}">
                <tr>                    
                    <td>${a.authorName}</a></td>
                    <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.dateAdded}" /></td>
                    <td><input type="button" class="btn btn-primary" value="Edit" onclick="location.href='authorController?action=edit&id=${a.authorId}'"</td>
                    <td><input type="button" class="btn btn-danger" value="Delete" onclick="location.href='authorController?action=delete&id=${a.authorId}'"</td>
                </tr>                
            </c:forEach>
        </table>
        <br>
        <input type="button" class="btn btn-primary" value="Add" onclick="location.href='authorController?action=add'">
        

    </body>
</html>
