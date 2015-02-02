<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url var="urlBootstrapCss" value="/webjars/bootstrap/3.3.1/css/bootstrap.min.css" />
<c:url var="urlBootstrapThemeCss" value="/webjars/bootstrap/3.3.1/css/bootstrap-theme.min.css" />
<c:url var="urlJqueryJs" value="/webjars/jquery/1.11.1/jquery.min.js" />
<c:url var="urlBootstrapJs" value="/webjars/bootstrap/3.3.1/js/bootstrap.min.js" />
<c:url var="urlHtml5shivJs" value="/webjars/html5shiv/3.7.2/html5shiv.min.js" />
<c:url var="urlRespondJs" value="/webjars/respond/1.4.2/dest/respond.min.js" />
<c:url var="urlCoreView" value="/core" scope="request" />

<c:set var="controller" scope="request"><tiles:getAsString name="controller" defaultValue="" /></c:set>
<c:set var="action" scope="request"><tiles:getAsString name="action" defaultValue="" /></c:set>
<c:set var="lang" scope="request"><c:out value="${pageContext.response.locale.language}"/></c:set>
<c:if test="${empty title}">
    <c:set var="titleCode"><tiles:getAsString name="title" /></c:set>
    <c:set var="title" scope="request"><spring:message code="${titleCode}"></spring:message></c:set>
</c:if>

<!DOCTYPE html>
<html lang="${lang}">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>${title}</title>
        <link rel="stylesheet" href="${urlBootstrapCss}" />
        <link rel="stylesheet" href="${urlBootstrapThemeCss}" />
        <link rel="stylesheet" href="${urlCoreView}/css/core.css" />
    </head>
    <body role="document">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="main" />
        <tiles:insertAttribute name="footer" />
        <script src="${urlJqueryJs}"></script>
        <script src="${urlBootstrapJs}"></script>
        <!--[if lt IE 9]>
            <script src="${urlHtml5shivJs}"></script>
            <script src="${urlRespondJs}"></script>
        <![endif]-->
        <script src="${urlCoreView}/js/core.js"></script>
    </body>
</html>
