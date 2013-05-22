<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<h3>Получен результат:</h3>
<c:if test = "${task.type == 'mc'}">
    <p>Интеграл функции F(x)=${task.function} на промежутке [${task.a}, ${task.b}] равен <b>${task.result}</b></p>
    <p>Количество точек: ${task.dots}</p>
    <p>Задача выполнялась агентами:</p>
    <c:forEach  items="${task.agents}" var="agent"> 
        Агент №${agent.id}  - ${agent.ip}<br>
    </c:forEach>
</c:if>
<c:if test = "${task.type == 'hash'}">
    <p>Хеш = ${task.hash} </p>
    <p>Найденные пароли:</p>
    <c:forEach  items="${task.agents}" var="agent"> 
        Агент №${agent.id}  - ${agent.ip}; Пароль: ${agent.result}<br>
    </c:forEach>
</c:if>
