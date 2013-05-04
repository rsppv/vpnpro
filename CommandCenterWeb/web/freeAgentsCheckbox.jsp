<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<label>Отправить агентам:</label>
<c:forEach  items="${agents}" var="agent">
    <c:if test="${agent.free}">
        <label class="checkbox">
            <input type="checkbox" id="agent${agent.id}" value="agent${agent.id}">${agent.id}
        </label>
    </c:if>
</c:forEach>