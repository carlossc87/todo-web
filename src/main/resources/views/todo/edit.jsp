<%-- 
    Document   : add
    Created on : 03-nov-2014, 22:53:54
    Author     : Carlos Serramito Calvo <carlos@cabestro.es>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Modificar tarea</h1>
        <c:url var="todoSaveUrl" value="/todo/save" />
        <form:form action="${todoSaveUrl}" modelAttribute="todo" >
            <form:errors path="*" cssClass="errorblock" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="form-row">
                    <label for="title">Titulo:</label>
                    <span class="input"><form:input path="title" /></span>
                    <form:errors path="title" cssClass="error" />
                </div>
                <div class="form-buttons">
                    <div class="button">
                        <input type="submit" id="todoSave" name="todoSave" value="Guardar"/>
                    </div>    
                </div>
            </fieldset>
        </form:form>
    </body>
</html>
