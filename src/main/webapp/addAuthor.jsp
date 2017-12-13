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

        <title>Add Author</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        
        <div class="conatiner-fluid">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <h1>Add New Author</h1>

                    <form method ="POST" 
                          action = "${pageContext.request.contextPath}/authorController?action=submitauthor">
                        Author name:<br>
                        <input type="text" name="author_name">
                        <br>
                        <input type="submit" name="submit" value="Submit">
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
