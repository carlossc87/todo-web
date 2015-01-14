<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav role="navigation" class="navbar navbar-default" >
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" 
                    data-target=".navbar-default-collapse">
                <span class="sr-only"><spring:message code="views.menu" /></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:url var="home" value="/" />
            <a class="navbar-brand" href="${home}"><spring:message code="views.home" /></a>
        </div>
        <div class="collapse navbar-collapse navbar-default-collapse">
            <ul class="nav navbar-nav">
                <c:url var="tasksIndex" value="/tasks/index" />
                <c:choose>
                    <c:when test="${controller == 'tasks'}">
                        <li class="active"><a href="${tasksIndex}"><spring:message code="views.tasks.index.title" /></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${tasksIndex}"><spring:message code="views.tasks.index.title" /></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <spring:message code="views.lang" />: 
                        <c:choose>
                            <c:when test="${lang == 'es'}">
                                <spring:message code="views.lang.es" />
                            </c:when>
                            <c:otherwise>
                                <spring:message code="views.lang.gl" />
                            </c:otherwise>
                        </c:choose>
                        <b class="caret" ></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="?lang=es_ES">
                                <c:if test="${lang == 'es'}">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                </c:if>
                                <spring:message code="views.lang.es" />
                            </a>
                        </li>
                        <li>
                            <a href="?lang=gl_ES">
                                <c:if test="${lang == 'gl'}">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                </c:if>
                                <spring:message code="views.lang.gl" />
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

