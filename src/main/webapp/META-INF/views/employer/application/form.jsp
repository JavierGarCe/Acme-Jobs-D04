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
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" />
	<acme:form-moment code="employer.application.form.label.moment" path="moment" />
	<acme:form-textbox code="employer.application.form.label.status" path="status" />
	<acme:form-textarea code="employer.application.form.label.statement" path="statement" />
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" />
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" />

	<acme:form-panel code="employer.application.form.panel.job">
		<button type="button" class="btn btn-default" onclick="javascript: clearReturnUrl(); redirect('/employer/job/show?id=${idJob}')">
		<acme:message code="employer.application.form.button.job"/>
		</button>
	</acme:form-panel>
	<acme:form-return code="employer.application.form.button.return" />
	
</acme:form>