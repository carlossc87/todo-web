<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <h1><spring:message code="views.todo.new.title" /></h1>
        <c:url var="todoAddUrl" value="/todo/add" />
        <form:form action="${todoAddUrl}" modelAttribute="todo"   >
            <form:errors path="*" cssClass="errorblock" element="div" />
            <fieldset>
                <div class="form-row">
                    <label for="title"><spring:message code="views.todo.new.titulo" /></label>
                    <span class="input"><form:input path="title" /></span>
                    <form:errors path="title" cssClass="error" />
                </div>
                <div class="form-buttons">
                    <div class="button">
                        <spring:message code="views.todo.new.add" var="todoAdd" />
                        <input type="submit" id="todoAdd" name="todoAdd" value="${todoAdd}"/>
                    </div>    
                </div>
            </fieldset>
        </form:form>
</tiles:putAttribute>
</tiles:insertDefinition>