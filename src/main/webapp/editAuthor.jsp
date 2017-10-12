<%-- 
    Document   : editAuthor
    Created on : Oct 12, 2017, 11:28:54 AM
    Author     : cscherbert1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Author</title>
    </head>
    <body>
        <h1>Edit Author</h1>

        <form method ="POST" 
              action = "${pageContext.request.contextPath}/authorController?action=editauthor">
            
            <input type="hidden" name="author_id" value="${eAuthor.authorId}"
            Author name:<br>
            <input type="text" name="author_name" value="${eAuthor.authorName}"><br>
            Date added:<br>
            <input type="text" name="date_added" value="${eAuthor.dateAdded}"><br>
            <br>
            <input type="submit" name="submit" value="Submit">
        </form>
    </body>
</html>
