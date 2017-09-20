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
                    <td>${a.authorId}</td>
                    <td>${a.authorName}</td>
                    <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.dateAdded}" /></td>
                </tr>                
            </c:forEach>
        </table>

    </body>
</html>
