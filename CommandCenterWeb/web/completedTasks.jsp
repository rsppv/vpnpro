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
        <script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery.form.js"/>"></script>
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" content="text/css" media="screen">
        <style>

            body {
                padding-top: 20px; 
            }
        </style>
        <title>Command Center</title>
        <script>
           
            
        </script>
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <h1>Command Center <br><small>IP: ${serverIP}</small></h1>
            </div>
            <!-- навигация -->
            <ul class="nav nav-pills">
                <li>
                    <a href="<c:url value="/main"/>">Главная</a>
                </li>
                <li class="active"><a href="#">Выполненные задания</a></li>
            </ul>
            <div class="container">
                <c:forEach  items="${tasks}" var="task"> 
                    <div class="well">
                        <c:if test = "${task.type == 'mc'}">
                            <h4>Задача нахождения интеграла.</h4>
                            Интеграл функции F(x)=${task.function} на промежутке [${task.a}, ${task.b}] равен <b>${task.result}</b></p>
                            <p>Количество точек: ${task.dots}</p>  

                            <p>Задача выполнялась агентами:</p>
                            <c:forEach  items="${task.agents}" var="agent"> 
                                Агент №${agent.id}  - ${agent.ip}<br>
                            </c:forEach>
                        </c:if>
                        <c:if test = "${task.type == 'hash'}">
                            <h2>Задача определения пароля.</h2>
                            <p>Хеш = ${task.hash} </p>
                            <p>Найденные пароли:</p>
                            <c:forEach  items="${task.agents}" var="agent"> 
                                Агент №${agent.id}  - ${agent.ip}; Пароль: ${agent.result}<br>
                            </c:forEach>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
