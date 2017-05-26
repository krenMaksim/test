<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="internalization.msgs" var="msgs" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">       
        <title><fmt:message bundle="${msgs}" key="avt.title" /></title>
        <link type="text/css" rel="stylesheet" href="css/commonStyle.css"/>
        <link type="text/css" rel="stylesheet" href="css/magazineStyle.css"/>
        <style >
        	#avtoriz{
        		text-align: center;
        		font-size: 25px;
        	}
        	input{
        		font-size: 25px;
        	}
        </style>
    </head>
    <body>
    	<c:import url="language.jsp">
    	 	<c:param name="webPage" value="/index.html" />
		</c:import>
        <h1><fmt:message bundle="${msgs}" key="application.magazine"/></h1>
        <form id="avtoriz" method="post" action="${requestURI}" accept-charset="utf-8">
        	<c:if test="${avtorizationError != null}">
			   <h3><fmt:message bundle="${msgs}" key="${avtorizationError}"/></h3>
			</c:if>
            <fmt:message bundle="${msgs}" key="avt.login"/><br>
            <input type="text" name="login" value="Gleb"><br>
            <fmt:message bundle="${msgs}" key="avt.password"/><br>
            <input type="password" name="password" value="dfsdflj"><br>
            <input type="submit" value='<fmt:message bundle="${msgs}" key="avt.enter"/>'>
        </form>
    </body>
</html>