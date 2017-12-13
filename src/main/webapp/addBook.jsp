<%-- 
    Document   : addAuthor
    Created on : Oct 11, 2017, 9:47:26 PM
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

        <title>Add Book</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        
        <div class="conatiner-fluid">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <h1>Add New Book</h1>
                    <!--
                                        <form method ="POST" 
                                              action = "${pageContext.request.contextPath}/bc?action=submitbook">
                                            Title:<br>
                                            <input type="text" name="title">
                                            <br>
                                            ISBN:c<br>
                                            <input type="text" name="isbn">
                                            <br>
                                            :<br>
                                            <input type="text" name="title">
                                            <br>
                                            <input type="submit" name="submit" value="Submit">
                                        </form>
                    -->

                    <form method = "POST"
                          action = "${pageContext.request.contextPath}/bc?action=submitbook">
                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" id="title" name="title">
                        </div>
                        <div class="form-group">
                            <label for="isbn">ISBN:</label>
                            <input type="text" class="form-control" id="isbn" name="isbn">
                        </div>
                        <div class="form-group">
                            <label for="author">Author:</label>
                            <select class="form-control" id="author" name="author">
                                <c:forEach var="a" items="${authorList}">
                                    <option value="${a.authorId}">${a.authorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">Add</button>
                    </form>

                    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
                    <script
                        src="https://code.jquery.com/jquery-3.2.1.min.js"
                        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
                        crossorigin="anonymous">
                    </script>
                    <!-- Bootstrap minified JavaScript -->
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

                </div>
                <div class="col-sm-2"></div>
            </div>

        </div>



    </body>
</html>
