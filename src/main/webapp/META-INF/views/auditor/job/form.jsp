<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="auditor.job.form.label.title" path="title" />
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference" />
	<acme:form-textbox code="auditor.job.form.label.status" path="status" />
	<acme:form-money code="auditor.job.form.label.salary" path="salary" />
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline" />
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-panel code="auditor.job.form.label.descriptor">
		<acme:form-textbox code="auditor.job.form.label.descriptor.description" path="descriptor.description" />
		<acme:form-textarea code="auditor.job.form.label.descriptor.duties" path="duties" />
	</acme:form-panel>
	
	
	<acme:menu-suboption code="master.menu.anonymous.listAuditRecords" action="/auditor/auditRecord/list-mine?id=${param.id}" />
	<acme:form-return code="auditor.job.form.button.return" />

	
	
	
</acme:form>