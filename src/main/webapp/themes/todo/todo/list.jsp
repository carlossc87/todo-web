<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        <spring:message code="views.todo.list.title" />
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="page-header">
            <h1><spring:message code="views.todo.list.title" /></h1>
        </div>
        <c:if test="${empty todos}">
            <div class="alert alert-info" role="alert">
                <spring:message code="views.todo.list.nada" />
            </div>
        </c:if>
        <c:if test="${not empty todos}">
            <ul class="list-group">
                <c:forEach var="todo" items="${todos}">
                    <c:url var="todoEditUrl" value="/todo/edit" >
                        <c:param name="id" value="${todo.id}" />
                    </c:url>
                    <c:url var="todoDeleteUrl" value="/todo/delete" >
                        <c:param name="id" value="${todo.id}" />
                    </c:url>
                    <li class="list-group-item">
                        ${todo.title} - <a href="${todoEditUrl}"><spring:message code="views.todo.list.editar" /></a>, <a href="${todoDeleteUrl}"><spring:message code="views.todo.list.borrar" /></a> 
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:url var="todoNewUrl" value="/todo/new" />
        <a href="${todoNewUrl}" class="btn btn-lg btn-success"><spring:message code="views.todo.list.nuevo" /></a>
    </tiles:putAttribute>
</tiles:insertDefinition>
