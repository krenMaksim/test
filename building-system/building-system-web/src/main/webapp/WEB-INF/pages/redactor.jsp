<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="internalization.msgs" var="msgs" />
<link type="text/css" rel="stylesheet" href="css/magazineRedaction.css"/>
<script>
	window.onload = function(){
		document.getElementById("date").innerHTML = "";
		selectSmeta();
        selectSmena();
        selectedUser();
	}
	
	function selectSmeta(){
        var smeta = document.getElementsByName("addSmeta")[0];
        var selectedNumber = smeta.options.selectedIndex;
        
        var addEdIzm = document.getElementsByName("addEdIzm")[0];
        addEdIzm.options[selectedNumber].selected = true;
        
        var ostatok = document.getElementsByName("ostatok")[0];
        ostatok.options[selectedNumber].selected = true;
        
        var pp = document.getElementsByName("ppSmeta")[0];
        pp.options[selectedNumber].selected = true;
        
        var textarea = document.getElementById("smetaText");
        textarea.innerHTML = smeta.value;
        
        var rest = document.getElementById("rest");
        rest.innerHTML ="*остаток: "+ ostatok.value;
        
        var edIzm = document.getElementById("edIzm");
        edIzm.innerHTML = addEdIzm.value;
    }
    
    function selectSmena(){
        var selectSmena = document.getElementById("selectSmena");
        var smena = document.getElementsByName("addSmena")[0];
        
        for(var i = 0; i < smena.length; i++) {            
            if(smena[i].value == selectSmena){
                smena.options[i].selected = true;
                break;
            }            
        }
    }
    
    function selectedUser(){
        var user = document.getElementsByName("addUserId")[0];
        var selectedNumber = user.options.selectedIndex;
        
        var role = document.getElementsByName("role")[0];
        role.options[selectedNumber].selected = true;
        
        var userRole = document.getElementById("userRole");
        userRole.innerHTML = role.value;
    }
</script>
<tr>
	<th colspan="8"><fmt:message bundle="${msgs}" key="redactor.record"/></th>
</tr>
<tr>
    <td>
    	<fmt:message bundle="${msgs}" key="record.change"/>
    	<input type="text" hidden="hidden" id="selectSmena" value="${oldRecord.smena}">
        <select name="addSmena" style="width:50px">		
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>    			       
        </select><br>
    	<input type="date" name="addDate" value="${oldRecord.date}">
    </td>
    <td>
        <select name="ppSmeta" hidden="hidden">
        	<option></option>
        	<c:forEach items="${smeta}" var="smeta">		
				<option value="${smeta.pp}"></option>			
			</c:forEach>	       
        </select>
        <fmt:message bundle="${msgs}" key="record.description"/>
        <select name="addSmeta" style="width:100px" onclick="selectSmeta()">
        	<option></option>
        	<c:forEach items="${smeta}" var="smeta">
                <c:choose>
				    <c:when test="${smeta.pp != oldRecord.ppSmeta}">
				    	<option value="${smeta.naimenovanie}">${smeta.obosnovanie} &emsp; ${smeta.naimenovanie}</option>
				    </c:when>
				    <c:otherwise>
				    	<option value="${smeta.naimenovanie}" selected>${smeta.obosnovanie} &emsp; ${smeta.naimenovanie}</option>
				    </c:otherwise>
				</c:choose>
			</c:forEach>		       
        </select><br>
        <textarea rows="5" id="smetaText" disabled></textarea>
        <fmt:message bundle="${msgs}" key="record.place"/><br>
        <textarea rows="5" name="addLocation">${oldRecord.location}</textarea>
    </td>
    <td><input type="text" name="addWeather" value="${oldRecord.weather}"></td>
    <td><textarea rows="5" name="addConditions">${oldRecord.conditions}</textarea></td>
    <td>
        <select name="addEdIzm" hidden="hidden">
        	<option></option>
        	<c:forEach items="${smeta}" var="smeta">
				<option value="${smeta.edIzm}"></option>
			</c:forEach>  			       
        </select>
        <p id="edIzm"></p>
    </td>
    <td>
    	<input type="text" name="addKolVo" value="${oldRecord.kolVo}">
    	<select name="ostatok"  hidden="hidden">
    		<option></option>
        	<c:forEach items="${smeta}" var="smeta">
				<option value="${smeta.rest}"></option>
			</c:forEach>    			       
        </select>
    	<p id="rest"></p>
    </td>
    <td><textarea rows="5" name="addControle">${oldRecord.controle}</textarea></td>
    <td>
        <select name="role" hidden="hidden">
	    	<c:forEach items="${users}" var="user">
	    		<option >${user.role}</option>
	    	</c:forEach>	       
        </select>
        <p id="userRole"></p>
    	<select name="addUserId" onclick="selectedUser()">
	    	<c:forEach items="${users}" var="user">
	    		<c:choose>
				    <c:when test="${user.id != oldRecord.userId}">
				    	<option value="${user.id}">${user.surnameInitials}</option>
				    </c:when>
				    <c:otherwise>
				    	<option value="${user.id}" selected>${user.surnameInitials}</option>
				    </c:otherwise>
				</c:choose>
	    	</c:forEach>	       
        </select>
    </td>
</tr>