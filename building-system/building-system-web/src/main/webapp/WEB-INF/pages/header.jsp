<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="internalization.msgs" var="msgs" />
<header>
    <h2 class="headerCell">
    	<fmt:message bundle="${msgs}" key="header.user"/> ${user.surnameInitials}<br>
    	<fmt:message bundle="${msgs}" key="header.status"/> ${user.role}
    </h2>
    <p class="headerCell">
        <input class="headerButton" type="button" value='<fmt:message bundle="${msgs}" key="header.smeta"/>' onclick="javascript:window.location='smeta.html'"><br>
    </p>
    <p class="headerCell">
        <input class="headerButton" type="button" value='<fmt:message bundle="${msgs}" key="header.magazine"/>'  onclick="javascript:window.location='magazine.html'"><br>
    </p>
    <p class="headerCell">
        <input class="headerButton" name="exit" type="button" value='<fmt:message bundle="${msgs}" key="header.exit"/>' onclick="javascript:window.location='index.html'"><br>
    </p> 
</header>
	