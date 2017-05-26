<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="internalization.msgs" var="msgs" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">       
    <title><fmt:message bundle="${msgs}" key="smeta.title" /></title>
    <link type="text/css" rel="stylesheet" href="css/commonStyle.css"/>
</head>    
<body>
	<c:import url="header.jsp"/>
	<c:import url="language.jsp">
    	 	<c:param name="webPage" value="${showPage}"/>
		</c:import>
	<table>
	    <caption>
	        <fmt:message bundle="${msgs}" key="smeta.chapter" /><br>
	        <fmt:message bundle="${msgs}" key="smeta.caption"/>
	    </caption>        
	    <tr class="tdStyle">
	        <th class="width10"><fmt:message bundle="${msgs}" key="smeta.pp" /></th>
	        <th class="width20"><fmt:message bundle="${msgs}" key="smeta.obosnovanie" /></th>
	        <th><fmt:message bundle="${msgs}" key="smeta.discription"/></th>
	        <th class="width10"><fmt:message bundle="${msgs}" key="smeta.edizm"/></th>
	        <th class="width10"><fmt:message bundle="${msgs}" key="smeta.number"/></th>            
	    </tr>
	    <c:forEach items="${smeta}" var="smeta">
		    <tr>
		        <td>${smeta.pp}</td>
		        <td>${smeta.obosnovanie}</td>
		        <td class="tdJustify">${smeta.naimenovanie}</td>
		        <td>${smeta.edIzm}</td>
		        <td>${smeta.kolVo}</td>        
		    </tr>
	    </c:forEach>        
	</table>
</body>
</html>