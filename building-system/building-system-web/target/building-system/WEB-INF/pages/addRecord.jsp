<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="internalization.msgs" var="msgs" />
<link type="text/css" rel="stylesheet" href="css/magazineRedaction.css"/>
<script>
	window.onload = function(){
		document.getElementById("date").innerHTML = "";
		selectSmeta();
		getTodayDate();
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
	
	function getTodayDate(){
		
		var today = new Date();
		var year = today.getFullYear();
		var month = today.getMonth()+1;
		if(month<10){
			month = "0"+month;
		}
		var dat = today.getDate();
        if(dat<10){
			dat = "0"+dat;
		}
		var dateFormat = year+"-"+month+"-"+dat;
		
		var date = document.getElementById("addDate");
		date.innerHTML = dateFormat;
		
		document.getElementsByName("addDate")[0].value = dateFormat;
	}
</script>
<tr>
	<th colspan="8"><fmt:message bundle="${msgs}" key="record.new"/></th>
</tr>
<tr>
    <td>
    	<fmt:message bundle="${msgs}" key="record.change"/>
        <select name="addSmena" style="width:50px">		
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>    			       
        </select><br>
    	<p id="addDate"></p>
    	<input type="text" hidden="hidden" name="addDate">
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
				<option value="${smeta.naimenovanie}">${smeta.obosnovanie} &emsp; ${smeta.naimenovanie}</option>			
			</c:forEach>		       
        </select><br>
        <textarea rows="5" id="smetaText" disabled></textarea>
        <fmt:message bundle="${msgs}" key="record.place"/><br>
        <textarea rows="5" name="addLocation"></textarea>
    </td>
    <td><input type="text" name="addWeather"></td>
    <td><textarea rows="5" name="addConditions"></textarea></td>
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
    	<input type="text" name="addKolVo">
    	<select name="ostatok"  hidden="hidden">
    		<option></option>
        	<c:forEach items="${smeta}" var="smeta">
				<option value="${smeta.rest}"></option>
			</c:forEach>    			       
        </select>
    	<p id="rest"></p>
    </td>
    <td><textarea rows="5" name="addControle"></textarea></td>
    <td>${user.role}<br>${user.surnameInitials}</td>
</tr>