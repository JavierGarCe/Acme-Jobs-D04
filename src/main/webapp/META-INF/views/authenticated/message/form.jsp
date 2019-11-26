

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.message.form.label.title" path="title" />
	<acme:form-textarea code="authenticated.message.form.label.body" path="body" />
	<acme:form-textarea code="authenticated.message.form.label.tags" path="tags" />
	<acme:form-textarea code="authenticated.message.form.label.user" path="author" />
	<acme:form-moment code="authenticated.message.form.label.moment" path="moment" />
	<button type="button" class="btn btn-default" onclick="javascript: clearReturnUrl(); redirect('/authenticated/thread/show?id=${idThread}')">
	<acme:message code="authenticated.message.form.button.return"/>
	</button>
</acme:form>