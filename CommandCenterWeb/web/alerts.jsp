<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:if test="${!empty error}">
    <c:if test="${!error}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4 class="alert-heading">Успех!</h4>
            ${sendTaskResp}<br>
        </div>
    </c:if>
    <c:if test="${error}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4 class="alert-heading">Ошибка!</h4>
            ${sendTaskResp}
        </div>
    </c:if>	
</c:if>