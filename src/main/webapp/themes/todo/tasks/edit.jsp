<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div role="main" class="container theme-showcase">
    <div class="page-header">
        <h1><spring:message code="views.tasks.edit.title" /></h1>
    </div>
    <c:url var="tasksSaveUrl" value="/tasks/save" />
    <form:form action="${tasksSaveUrl}" modelAttribute="task" >
        <form:errors path="*" cssClass="errorblock" element="div" />
        <form:hidden path="id" />
        <fieldset>
            <div class="form-row">
                <label for="title"><spring:message code="views.tasks.edit.field.titulo" />:</label>
                <span class="input"><form:input path="title" /></span>
                <form:errors path="title" cssClass="error" />
            </div>
            <div class="form-buttons">
                <div class="button"> 
                    <spring:message code="views.tasks.edit.action.save" var="actionSave" />
                    <input type="submit" id="actionSave" name="actionSave" value="${actionSave}" />
                </div>    
            </div>
        </fieldset>
    </form:form>
</div>