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
        <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" content="text/css" media="screen">
        <style>

            body {
                padding-top: 20px; 
            }
        </style>
        <title>Command Center</title>
        <script>
            $(document).ready(function(){
                // первоначальная загрузка дивов
                $('#agentDiv').load("agents.jsp");
                $('#agentsTask').load("freeAgentsCheckbox.jsp");
                
                // обновление каждые 5 секунд
                setInterval(function() {
                    $('#agentDiv').load("agents.jsp");
                    $('#agentsTask').load("freeAgentsCheckbox.jsp");
                }, 5000);
                
                // не реализовано отправка задания не нажимать на кнопку 
                $('#taskForm').ajaxForm(function() {
                    alert("Задание отправлено!");
                    // спрятать форму задания
                });
            });
            
            
            
        </script>
    </head>
    <body>
        <div class="container">
            <div class="masthead">
                <h1>Command Center</h3>
            </div>
            <hr>
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span3" id="agentDiv">
                        <!-- Подгружается из agents.jsp -->
                    </div>
                    <div class="span9">
                        <!-- Спрятать форму под кнопку -->
                        <form id="taskForm" action="/SendTask" method="post">
                            <fieldset>
                                <legend>Новое задание</legend>
                                <label>Функция f(x)=</label>
                                <input type="text" name="function" value="x*x*x-Math.sin(3*x)">
                                <label>Интервал от </label>
                                <input type="text" name="ainterval" placeholder="a">
                                <label>до</label>
                                <input type="text" name="binterval" placeholder="b">
                                <label>Количество точек</label>
                                <input type="text" name="dots" value="100000">
                                <div id="agentsTask">
                                    <!-- Список свободных агентов с чекбоксами -->
                                    <!-- Подгружается из freeAgentsCheckbox.jsp -->
                                </div>
                                <button type="submit" class="btn">Отправить задание</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
