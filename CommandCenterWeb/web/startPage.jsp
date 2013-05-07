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
            $(document).ready(function(){
                // первоначальная загрузка дивов
                $('#taskForm').hide();
                $('#agentDiv').load("agents.jsp");
                
                
                // обновление каждые 3 секундs
                setInterval(function() {
                    $('#agentDiv').load("agents.jsp");
                }, 3000);
                
                var options = {
                    beforeSubmit: checkFields,
                    success: showResult
                };
                
                function checkFields(formData, jqForm, options) {
                    // проверить поля - все заполнены, цифры
                    // если все нормально то alert
                    $("#taskForm").clearForm();
                    $('#taskForm').toggle();
                    $('#addTaskBtn').show();
                    alert("Задание отправлено! Ожидайте ответа.");
                    $('#resultDiv').hide();
                }
                
                function showResult(responseText, statusText, xhr, $form) {
                    // загрузить div с результатом
                    alert("Задание выполнено!");
                    $('#resultDiv').load("resultDiv.jsp");
                    $('#resultDiv').show();
                }
                // отправка задания 
                $('#taskForm').ajaxForm(options);
                
                // при нажатии кнопки показать форму
                $('#addTaskBtn').click(function(event) {
                    $('#agentsTask').load("freeAgentsCheckbox.jsp");
                    $('#taskForm').toggle();
                    $('#addTaskBtn').hide();
                });
                
                // при нажатии кнопки обновить клиенты с чекбоксами
                $('#refrBtn').click(function(event) {
                    $('#agentsTask').load("freeAgentsCheckbox.jsp");
                });
                
                // при нажатии кнопки спрятать форму
                $('#hideTaskFormBtn').click(function(event) {
                    $('#taskForm').toggle();
                    $('#addTaskBtn').show();
                });
            });
            
            
            
        </script>
    </head>
    <body>
        <div class="container">
            <div class="masthead">
                <h1>Command Center</h3>
                    <h3>${serverIP}</h3>
            </div>
            <hr>
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span3" id="agentDiv">
                        <!-- Подгружается из agents.jsp -->
                    </div>
                    <div class="span9">
                        <!-- Спрятать форму под кнопку -->
                        <button class="btn btn-primary" id="addTaskBtn">Новое задание</button>
                        <form id="taskForm" action="<c:url value="/SendTask"/>" method="post">
                            <fieldset>
                                <legend>Новое задание</legend>
                                <label>Функция f(x)=</label>
                                <input class="input-xxlarge" type="text" name="function" value="x*x*x-Math.sin(3*x)">
                                <label>Интервал от </label>
                                <input type="text" class="input-small" name="ainterval" placeholder="a">
                                <label>до</label>
                                <input type="text" class="input-small" name="binterval" placeholder="b">
                                <label>Количество точек</label>
                                <input type="text" class="input-medium" name="dots" value="100000">
                                <label>Отправить агентам:</label>
                                <button class="btn btn-mini" type="button" id="refrBtn">Обновить</button>
                                <div id="agentsTask">
                                    <!-- Список свободных агентов с чекбоксами -->
                                    <!-- Подгружается из freeAgentsCheckbox.jsp -->
                                </div>
                                <button type="submit" class="btn">Отправить задание</button>
                                <button class="btn btn-primary" id="hideTaskFormBtn">Убрать форму</button>
                            </fieldset>
                        </form>
                        <!--Здесь div  в котором будет выводиться результат - загружается из resultDiv.jsp -->
                        <div id="resultDiv">

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
