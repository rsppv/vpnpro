<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<h3>Получен результат:</h3>
<p>Интеграл функции F(x)=${task.function} на промежутке [${task.a}, ${task.b}] равен <b>${task.result}</b></p>
<p>Задача выполнялась агентами:</p>
<c:forEach  items="${task.agents}" var="agent"> 
    Агент №${agent.id}  - ${agent.ip}</ br>
</c:forEach>