<%-- 
    Document   : addAuthor
    Created on : Oct 11, 2017, 9:47:26 PM
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
        <title>Add Author</title>
    </head>
    <body>
        <h1>Add New Author</h1>
        
        <form method ="POST" 
              action = "${pageContext.request.contextPath}/authorController?action=submitauthor">
            Author name:<br>
            <input type="text" name="author_name">
            <br>
            <input type="submit" name="submit" value="Submit">
        </form>
    </body>
</html>
