<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<fmt:setBundle basename="internalization.msgs" var="msgs" />
<h4 style="text-align: right">
     <a href="${webPage}?locale=ru_RU&magazineId=${magazineId}">
         <fmt:message bundle="${msgs}" key="application.rusLocale"/>
     </a>
     &nbsp;&nbsp;
     <a href="${webPage}?locale=en_EN&magazineId=${magazineId}">
         <fmt:message bundle="${msgs}" key="application.enLocale" />
     </a>
</h4>
	