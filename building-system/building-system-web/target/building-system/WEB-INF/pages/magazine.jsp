<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="internalization.msgs" var="msgs" />
<c:forEach items="${records}" var="records">
	<tr>
		<td>
			${records.date}
			смена ${records.smena}
			<p class="correction">${records.archive.date}</p>
			<p class="correction">${records.archive.smena}</p>
		</td>
		<td class="tdJustify">
			${records.obosnovanie}<br>
			${records.location}
			<p class="correction">${records.archive.obosnovanie}</p>
			<p class="correction">${records.archive.location}</p>
		</td>
		<td>
			${records.weather}
			<p class="correction">${records.archive.weather}</p>
		</td>
		<td>
			${records.conditions}
			<p class="correction">${records.archive.conditions}</p>
		</td>
		<td>
			${records.edIzm}
		</td>
		<td>
			${records.kolVo}
			<p class="correction">${records.archive.kolVo}</p>
		</td>
		<td class="tdJustify">
			${records.controle}
			<p class="correction">${records.archive.controle}</p>
		</td>
		<td>
			${records.role}<br>${records.surnameInitials}
			<p class="correction">${records.archive.surnameInitials}</p><br>
            <input type="button" value='<fmt:message bundle="${msgs}" key="magaz.redaction"/>' onclick="redactionRecord('${records.id}')">
		</td>
	</tr>
</c:forEach>