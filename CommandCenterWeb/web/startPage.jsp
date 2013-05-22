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
                $('#MCTaskForm').hide();
                $('#hideMCTaskFormBtn').hide();
                $('#HashTaskForm').hide();
                $('#hideHashTaskFormBtn').hide();
                $('#agentDiv').load("agents.jsp");
                
                
                // обновление каждые 3 секундs
                setInterval(function() {
                    $('#agentDiv').load("agents.jsp");
                }, 3000);
                
                var optionsMC = {
                    beforeSubmit: checkFieldsMC,
                    success: showResult
                };
                
                var optionsHash = {
                    beforeSubmit: checkFieldsHash,
                    success: showResult
                };
                
                function checkFieldsMC(formData, jqForm, options) {
                    $("#MCTaskForm").clearForm();
                    $('#MCTaskForm').toggle();
                    $('#addMCTaskBtn').show();
                    $('#hideMCTaskFormBtn').hide();
                    // здесь noty
                    noty({text: 'Задание отправлено', layout: 'topRight', type:'information',   closeWith: ['hover']});
                    $('#resultDiv').hide();
                }
                
                function checkFieldsHash(formData, jqForm, options) {
                    $("#HashTaskForm").clearForm();
                    $('#HashTaskForm').toggle();
                    $('#addHashTaskBtn').show();
                    $('#hideHashTaskFormBtn').hide();
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
                
                $('#MCTaskForm').ajaxForm(optionsMC);
                $('#HashTaskForm').ajaxForm(optionsHash);
               
                
                
                // при нажатии кнопки показать форму
                $('#addMCTaskBtn').click(function(event) {
                    refrAgents();
                    $('#MCTaskForm').toggle();
                    $('#addMCTaskBtn').hide();
                    $('#hideMCTaskFormBtn').show();
                    $('#resultDiv').hide();
                });
                
                // при нажатии кнопки показать форму
                $('#addHashTaskBtn').click(function(event) {
                    refrAgents();
                    $('#HashTaskForm').toggle();
                    $('#addHashTaskBtn').hide();
                    $('#hideHashTaskFormBtn').show();
                    $('#resultDiv').hide();
                });
                
                // при нажатии кнопки обновить клиенты с чекбоксами
                $('#refrMCBtn').click(function(event) {
                    refrAgents();
                });
                
                // при нажатии кнопки обновить клиенты с чекбоксами
                $('#refrHashBtn').click(function(event) {
                    refrAgents();
                });
                
                // при нажатии кнопки спрятать форму
                $('#hideMCTaskFormBtn').click(function(event) {
                    $('#MCTaskForm').toggle();
                    $('#addMCTaskBtn').show();
                    $('#hideMCTaskFormBtn').hide();
                });
                
                // при нажатии кнопки спрятать форму
                $('#hideHashTaskFormBtn').click(function(event) {
                    $('#HashTaskForm').toggle();
                    $('#addHashTaskBtn').show();
                    $('#hideHashTaskFormBtn').hide();
                });
                
                function refrAgents(){
                    $.ajax({
                        type: "GET",
                        url: "GetAgents"
                    }).done(function() {
                        $('#agentsMCTask').load("freeAgentsCheckbox.jsp");
                         $('#agentsHashTask').load("freeAgentsCheckbox.jsp");
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
                        <button class="btn btn-primary" id="addMCTaskBtn">Новое задание: Интеграл</button>
                        <button class="btn btn-primary" id="addHashTaskBtn">Новое задание: Хеш</button>
                        <form id="MCTaskForm" action="<c:url value="/SendMCTask"/>" method="post">
                            <fieldset>
                                <legend>Вычислить определенный интеграл.</legend>
                                <label for="func">(*)Функция f(x)= <em></em></label>
                                <input class="input-xxlarge" type="text" name="func" value="x*x*x-Math.sin(3*x)">
                                <label for="ainterval">(*)Интервал от <em></em></label>
                                <input type="text" class="input-small" name="ainterval" placeholder="a">
                                <label for="binterval">до <em></em></label>
                                <input type="text" class="input-small" name="binterval" placeholder="b">
                                <label for="dots">Количество точек <em></em></label>
                                <input type="text" class="input-medium" name="dots" value="10000">
                                <label for="agentsCheckBox">Отправить агентам: <button class="btn btn-mini" type="button" id="refrMCBtn">Обновить</button></label>
                                
                                <div id="agentsMCTask">
                                    <!-- Список свободных агентов с чекбоксами -->
                                    <!-- Подгружается из freeAgentsCheckbox.jsp -->
                                </div>
                                <button type="submit" class="btn btn-success">Отправить задание</button>
                                <a class="btn btn-mini" id="hideMCTaskFormBtn">Убрать форму</a>
                            </fieldset>
                        </form>
                        


                        
                        <form id="HashTaskForm" action="<c:url value="/SendHashTask"/>" method="post">
                            <fieldset>
                                <legend>Определить пароль:</legend>
                                <label for="func">(*)Хеш</label>
                                <input class="input-xxlarge" type="text" name="hash">
                                <label>Количество символов:</label>
                                <label class="radio inline">
                                    <input type="radio" name ="symCountRadio" value="3" checked="true">3
                                </label>
                                <label class="radio inline">
                                    <input type="radio" name ="symCountRadio" value="4">4
                                </label>
                                <label class="radio inline">
                                    <input type="radio" name ="symCountRadio" value="5">5
                                </label>
                                
                                <label>Алфавит:</label>
                                <label class="checkbox">
                                    <input type="checkbox" name ="alphabetCheckBox" value="abc">abcd...
                                </label>
                                <label class="checkbox">
                                    <input type="checkbox" name ="alphabetCheckBox" value="ABC">ABCD...
                                </label>
                                <label class="checkbox">
                                    <input type="checkbox" name ="alphabetCheckBox" value="123">1234...
                                </label>

                                <label for="agentsCheckBox">Отправить агентам: <button class="btn btn-mini" type="button" id="refrHashBtn">Обновить</button></label>
                                
                                <div id="agentsHashTask">
                                    <!-- Список свободных агентов с чекбоксами -->
                                    <!-- Подгружается из freeAgentsCheckbox.jsp -->
                                </div>
                                <button type="submit" class="btn btn-success">Отправить задание</button>
                                <a class="btn btn-mini" id="hideHashTaskFormBtn">Убрать форму</a>
                            </fieldset>
                        </form>
                        

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
