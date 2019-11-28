
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.message.list.label.title" path="title" />
	<acme:list-column code="authenticated.message.list.label.body" path="body" />
	<acme:list-column code="authenticated.message.list.label.tags" path="tags" />
</acme:list>

<jstl:if test="${model$size == 1}">
<jstl:set var="idT" value="${idThread}"/>	
</jstl:if>
<jstl:if test="${model$size >= 2}">
<jstl:set var="thread" value="idThread[0]" />
<jstl:set var="idT" value="${requestScope[thread]}"/>
</jstl:if>

<button type="button" class="btn btn-default" onclick="javascript: clearReturnUrl(); redirect('/authenticated/thread/show?id=${idT}')">
	<acme:message code="authenticated.message.form.button.return"/>
	</button>