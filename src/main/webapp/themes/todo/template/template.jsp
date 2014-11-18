<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--meta name="description" content=""-->
        <!--meta name="author" content=""-->
        <!--link rel="icon" href="../../favicon.ico"-->
        
        <title><tiles:insertAttribute name="title" /></title>
        
        <link rel='stylesheet' href='webjars/bootstrap/3.3.1/css/bootstrap.min.css' />
        <link rel='stylesheet' href='webjars/bootstrap/3.3.1/css/bootstrap-theme.min.css' />
        
        <link rel='stylesheet' href='theme/css/theme.css' />
        
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body role="document">
        <tiles:insertAttribute name="header" />
        <div class="container theme-showcase" role="main">
            <tiles:insertAttribute name="body" />
        </div><!-- /container -->       
        <tiles:insertAttribute name="footer" />
        <script src="webjars/jquery/1.11.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
