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
	<acme:form-textbox code="authenticated.job.form.label.title" path="title" />
	<acme:form-textbox code="authenticated.job.form.label.reference" path="reference" />
	<acme:form-textbox code="authenticated.job.form.label.status" path="status" />
	<acme:form-money code="authenticated.job.form.label.salary" path="salary" />
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline" />
	<acme:form-url code="authenticated.job.form.label.moreInfo" path="moreInfo" />



	<b><acme:message code="authenticated.job.form.label.descriptor" /></b>
	<div class="card border-secondary mb-3 ">
		<div class="card-body">
			<acme:form-textbox code="authenticated.job.form.label.descriptor.description" path="descriptor.description" />
			<jstl:forEach begin="${0}" step="${1}" end="${descriptor.duties.size() - 1}" var="i">
				<b><acme:message code="authenticated.job.form.label.duty" /></b>
				<div class="card border-secondary mb-3">
					<div class="card-body">
						<acme:form-textbox code="authenticated.job.form.label.duty.title" path="duty${i}.title" />
						<acme:form-textarea code="authenticated.job.form.label.duty.description" path="duty${i}.description" />
						<acme:form-double code="authenticated.job.form.label.duty.percentage" path="duty${i}.percentage" />
					</div>
				</div>
			</jstl:forEach>
		</div>
	</div>







	<acme:form-return code="authenticated.job.form.button.return" />
</acme:form>