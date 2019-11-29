<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="employer.auditRecord.list.label.title" path="title" width="40%"/>
	<acme:list-column code="employer.auditRecord.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="employer.auditRecord.list.label.status" path="status" width="40%"/>
</acme:list>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/job/show?id=${param.id}')" class="btn btn-primary">
		<acme:message code="employer.auditRecord.form.button.return" />
	</button>