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
        <script type="text/javascript" src="<c:url value="/js/bootstrap-alert.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery.validate.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/additional-methods.js"/>"></script>
        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>
        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/topRight.js"></script>
        <script type="text/javascript" src="js/noty/themes/default.js"></script>
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
                $('#hideTaskFormBtn').hide();
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
                    $("#taskForm").clearForm();
                    $('#taskForm').toggle();
                    $('#addTaskBtn').show();
                    $('#hideTaskFormBtn').hide();
                    // здесь noty
                    noty({text: 'Задание отправлено', layout: 'topRight', type:'information',   closeWith: ['hover']});
                    $('#resultDiv').hide();
                }
                
                function showResult(responseText, statusText, xhr, $form) {
                    // загрузить div с результатом
                    $('#alertsDiv').load("alerts.jsp");
                    //если не ошибка только тогда показывать результат
                    $('#resultDiv').load("resultDiv.jsp");
                    $('#resultDiv').show();
                }
                
                $('#taskForm').ajaxForm(options);
               
                
                
                // при нажатии кнопки показать форму
                $('#addTaskBtn').click(function(event) {
                    refrAgents();
                    $('#taskForm').toggle();
                    $('#addTaskBtn').hide();
                    $('#hideTaskFormBtn').show();
                    $('#resultDiv').hide();
                });
                
                // при нажатии кнопки обновить клиенты с чекбоксами
                $('#refrBtn').click(function(event) {
                    refrAgents();
                });
                
                // при нажатии кнопки спрятать форму
                $('#hideTaskFormBtn').click(function(event) {
                    $('#taskForm').toggle();
                    $('#addTaskBtn').show();
                    $('#hideTaskFormBtn').hide();
                });
                
                function refrAgents(){
                    $.ajax({
                        type: "GET",
                        url: "GetAgents"
                    }).done(function() {
                        $('#agentsTask').load("freeAgentsCheckbox.jsp");
                    });
                }
            });
            
            
            
        </script>
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <h1>Command Center <br><small>IP: ${serverIP}</small></h1>
            </div>
            <!-- навигация -->
            <ul class="nav nav-pills">
                <li class="active">
                    <a href="#">Главная</a>
                </li>
                <li><a href="<c:url value="/completedTasks.jsp"/>">Выполненные задания</a></li>
            </ul>
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span4" id="agentDiv">
                        <!-- Подгружается из agents.jsp -->
                    </div>
                    <div class="span8">
                        <!-- Спрятать форму под кнопку -->
                        <button class="btn btn-primary" id="addTaskBtn">Новое задание</button>
                        <form id="taskForm" action="<c:url value="/SendTask"/>" method="post">
                            <fieldset>
                                <legend>Вычислить определенный интеграл.</legend>
                                <label for="func">(*)Функция f(x)= <em></em></label>
                                <input class="input-xxlarge" type="text" name="func" value="x*x*x-Math.sin(3*x)">
                                <label for="ainterval">(*)Интервал от <em></em></label>
                                <input type="text" class="input-small" name="ainterval" placeholder="a">
                                <label for="binterval">до <em></em></label>
                                <input type="text" class="input-small" name="binterval" placeholder="b">
                                <label for="dots">Количество точек <em></em></label>
                                <input type="text" class="input-medium" name="dots" value="100000">
                                <label for="agentsCheckBox">Отправить агентам: <em></em></label>
                                <button class="btn btn-mini" type="button" id="refrBtn">Обновить</button>
                                <div id="agentsTask">
                                    <!-- Список свободных агентов с чекбоксами -->
                                    <!-- Подгружается из freeAgentsCheckbox.jsp -->
                                </div>
                                <button type="submit" class="btn">Отправить задание</button>
                            </fieldset>
                        </form>
                        <button class="btn btn-primary" id="hideTaskFormBtn">Убрать форму</button>
                        <div id="alertsDiv">
                            <!-- здесь загружаются alert из alerts.jsp-->
                        </div>
                        <!--Здесь div  в котором будет выводиться результат - загружается из resultDiv.jsp -->
                        <div id="resultDiv">

                        </div>

                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
