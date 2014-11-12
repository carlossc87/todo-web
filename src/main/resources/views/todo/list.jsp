<%-- 
    Document   : add
    Created on : 03-nov-2014, 22:53:54
    Author     : Carlos Serramito Calvo <carlos@cabestro.es>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><spring:message code="todo.list.title" /></h1>
        <c:if test="${not empty todos}">
            <ul>
                <c:forEach var="todo" items="${todos}">
                    <c:url var="todoEditUrl" value="/todo/edit" >
                        <c:param name="id" value="${todo.id}" />
                    </c:url>
                    <c:url var="todoDeleteUrl" value="/todo/delete" >
                        <c:param name="id" value="${todo.id}" />
                    </c:url>
                    <li>${todo.title} - <a href="${todoEditUrl}">editar</a>, <a href="${todoDeleteUrl}">borrar</a> </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty todos}">
            <p>No hai nada para hacer.</p>
        </c:if>
        
        <div>
            <c:url var="todoNewUrl" value="/todo/new" />
            <a href='${todoNewUrl}'>Nuevo</a>
        </div>
    </body>
</html>
