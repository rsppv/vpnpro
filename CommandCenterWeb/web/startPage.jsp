<%-- 
    Document   : index
    Created on : 01.05.2013, 22:24:03
    Author     : aipova
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" content="text/css" media="screen">
        <style>
            
            body {
                padding-top: 20px; 
            }
        </style>
        <title>Command Center</title>
    </head>
    <body>
        <div class="container">
            <div class="masthead">
                <h1>Command Center</h3>
            </div>
            <hr>
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span3">
                        <h3>Clients:</h3>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>IP</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach  items="${agents}" var="agent"> 
                                    <tr>
                                        <td>${agent.id}</td>
                                        <td>${agent.ip}</td>
                                        <td>C</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="span9">
                        <!--Body content-->
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
