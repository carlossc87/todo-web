<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div role="main" class="container theme-showcase">
    <div class="page-header">
        <h1><spring:message code="views.tasks.index.title" /></h1>
    </div>
    <c:if test="${empty tasks}">
        <div class="alert alert-info" role="alert">
            <spring:message code="views.tasks.index.nothing" />
        </div>
    </c:if>
    <c:if test="${not empty tasks}">
        <ul class="list-group">
            <c:forEach var="task" items="${tasks}">
                <c:url var="taskEditUrl" value="/tasks/edit" >
                    <c:param name="id" value="${task.id}" />
                </c:url>
                <c:url var="taskDeleteUrl" value="/tasks/delete" >
                    <c:param name="id" value="${task.id}" />
                </c:url>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-9 col-sm-8 task-center">${task.title}</div>
                        <div class="col-md-3 col-sm-4 text-right"><a href="${taskEditUrl}" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span><spring:message code="views.tasks.index.edit" /></a> <a href="${taskDeleteUrl}" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span><spring:message code="views.tasks.index.delete" /></a></div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <div class="text-right">
        <c:url var="taskAddUrl" value="/tasks/add" />
        <a href="${taskAddUrl}" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span><spring:message code="views.tasks.index.add" /></a>
    </div>
</div>
