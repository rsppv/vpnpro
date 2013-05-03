<%-- 
    Document   : computes
    Created on : 01.05.2013, 23:28:51
    Author     : aipova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>HELLO</h1>
        <h1><%= request.getAttribute("computed").toString() %> </h1>
    </body>
</html>
