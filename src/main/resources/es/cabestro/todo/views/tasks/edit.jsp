<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div role="main" class="container theme-showcase">
    <div class="page-header">
        <h1><spring:message code="views.tasks.edit.title" /></h1>
    </div>
    <c:url var="tasksSaveUrl" value="/tasks/saveedit" />
    <form:form action="${tasksSaveUrl}" modelAttribute="task" cssClass="form-horizontal" >
        <form:hidden path="id" />
        
        <c:set var="errors"><form:errors path="*" /></c:set>
        <c:set var="titleError"><form:errors path="title" /></c:set>
        <c:if test="${not empty errors}">
            <div>
                <span class="sr-only">Errores:</span>
                <c:if test="${not empty titleError}">
                    <div class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        ${titleError}
                    </div>
                </c:if>
            </div>
        </c:if>
        <div class="form-group <c:if test="${not empty titleError}">has-error has-feedback</c:if>">
            <spring:message code="views.tasks.add.field.title" var="fieldTitle" />
            <form:label path="title" cssClass="col-sm-2 control-label">
                ${fieldTitle}
            </form:label> 
            <div class="col-sm-10" >
                <form:input path="title" cssClass="form-control" placeholder="${fieldTitle}" />
                <c:if test="${not empty titleError}">
                    <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                    <span id="inputError2Status" class="sr-only">(error)</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button class="btn btn-success" >
                    <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                    <spring:message code="views.tasks.edit.action.save" />
                </form:button>
            </div>
        </div>
    </form:form>
    <div class="text-right">
        <c:url var="tasksIndex" value="/tasks/index" />
        <a href="${tasksIndex}" class="btn btn-danger">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            <spring:message code="views.cancel" />
        </a>
    </div>
</div>