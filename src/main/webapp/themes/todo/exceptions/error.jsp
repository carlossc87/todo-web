<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div role="main" class="container theme-showcase">
    <div class="page-header">
        <h1><spring:message code="views.exceptions.error.title" /></h1>
    </div>
    <div class="panel panel-danger">
      <div class="panel-heading">
          <h2 class="panel-title"><spring:message code="views.exceptions.error.title" /></h2>
      </div>
      <div class="panel-body">
          <p><spring:message code="views.exceptions.error.message" /></p>
          <ul>
              <li><strong><spring:message code="views.exceptions.error.info.id" />:</strong> ${error}</li>
          </ul>
      </div>
    </div>
</div>
