<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:forEach  items="${agents}" var="agent">
    <c:if test="${agent.free}">
        <label class="checkbox">
            <input type="checkbox" id="agent${agent.id}" name ="agentsCheckBox" value="${agent.id}">${agent.id} - ${agent.ip}
        </label>
    </c:if>
</c:forEach>