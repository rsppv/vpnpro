<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<h3>Подключенные агенты:</h3>
<table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>IP адрес</th>
            <th>Статус</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach  items="${agents}" var="agent"> 
            <tr>
                <td>${agent.id}</td>
                <td>${agent.ip}</td>
                <td>
                    <c:if test="${agent.free}">
                        Свободен
                    </c:if>
                    <c:if test="${agent.free==false}">
                        Занят: 
                        <c:if test = "${agent.taskType == 'mc'}">Интеграл</c:if>
                        <c:if test = "${agent.taskType == 'hash'}">Хеш</c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>