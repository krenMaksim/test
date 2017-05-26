<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="internalization.msgs" var="msgs" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">       
        <title><fmt:message bundle="${msgs}" key="${title}"/></title>
        <link type="text/css" rel="stylesheet" href="css/commonStyle.css"/>
        <link type="text/css" rel="stylesheet" href="css/magazineStyle.css"/>
        <script src="js/script.js"></script>        
    </head>    
    <body>
        <c:import url="header.jsp"/>
        <c:import url="language.jsp">
    	 	<c:param name="webPage" value="${showPage}"/>
		</c:import>
        <h1><fmt:message bundle="${msgs}" key="main.object"/></h1>
	<form class="tableRaw" method="post" action="${requestURI}" accept-charset="utf-8">
        <div class="tableContener">
            <p id="magazineButton" class="cell">
                <input class="magazineButton" type="button" value='<fmt:message bundle="${msgs}" key="main.addRecord"/>'  onclick="javascript:window.location='add-record.html'">
                <input class="magazineButton" type="submit" value='<fmt:message bundle="${msgs}" key="main.enter"/>' onclick="saveStorage()">
            </p>
            
            <p id="date" class="cell">
	            <fmt:message bundle="${msgs}" key="main.with"/><input name="date_with" class="date" type="date" size="15" onchange="localStorage.setItem('date_with', this.value);"><br>
	            <fmt:message bundle="${msgs}" key="main.on"/><input name="date_on" class="date" type="date" onchange="localStorage.setItem('date_on', this.value);"><br><br>
	            <input class="magazinCheckbox" id="corrections" type="checkbox" onchange="showCorrections()"><b><fmt:message bundle="${msgs}" key="main.corrections"/></b>
            </p>
            <p class="cell">
	            <c:if test="${surname != null}">
	   				<c:forEach var="i" begin="0" end="${fn:length(surname)-1}">
			    		<input class="magazinCheckbox" name="surname_initials" type="checkbox" value="${surname[i]}">${surname[i]}<br>
					</c:forEach>
				</c:if>
            </p>
        </div> 
	<table>
        <caption>
            <fmt:message bundle="${msgs}" key="main.chapter"/><br><fmt:message bundle="${msgs}" key="main.caption"/>
        </caption>
        <tr class="tdStyle">
            <th class="width5" rowspan="2">
            	<fmt:message bundle="${msgs}" key="main.date"/>
            </th>
            <th class="width30" rowspan="2">
            	<fmt:message bundle="${msgs}" key="main.naimenovanie"/>
            </th>
            <th class="width5" rowspan="2">
            	<fmt:message bundle="${msgs}" key="main.weather"/>
            </th>
            <th class="width10" rowspan="2">
            	<fmt:message bundle="${msgs}" key="main.condition"/>
            </th>
            <th colspan="2">
            	<fmt:message bundle="${msgs}" key="main.priemka"/>
            </th>            
            <th class="width20" rowspan="2">
            	<fmt:message bundle="${msgs}" key="main.controle"/>
            </th>
            <th class="width20" rowspan="2">
            	<fmt:message bundle="${msgs}" key="main.initials"/>
            </th>
        </tr>
        <tr class="tdStyle">  
            <th class="width5"><fmt:message bundle="${msgs}" key="main.edizm"/></th>
            <th class="width5"><fmt:message bundle="${msgs}" key="main.number"/></th>
        </tr>
        <c:choose>
		    <c:when test="${showPage == '/magazine.html'}">
		       <c:import url="magazine.jsp"/>
		    </c:when>
		    <c:when test="${showPage == '/add-record.html'}">
		       <c:import url="addRecord.jsp"/>
		    </c:when>
		    <c:when test="${showPage == '/redactor.html'}">
		       <c:import url="redactor.jsp"/>
		    </c:when>
		</c:choose>
    </table>
	</form>
	<form id="redactor" method="get" action="${pageContext.request.contextPath}/redactor.html" accept-charset="utf-8">
		<input type="text" hidden="hidden" name="magazineId">
	</form>
    <script>
        function redactionRecord(id){
            var magazineId = document.getElementsByName("magazineId")[0];
            magazineId.value = id;
            document.getElementById("redactor").submit();
        }
    </script>      
    </body>
</html>