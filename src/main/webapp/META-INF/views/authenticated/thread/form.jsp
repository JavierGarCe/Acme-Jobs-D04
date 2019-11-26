<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.threads.form.label.title" path="title" />
	<acme:form-moment code="authenticated.threads.form.label.moment" path="moment"/>
	<b><acme:message code="authenticated.threads.form.panel.messages" /></b>
	<div class="card border-secondary mb-3">
	 <div class="card-body">
			<jstl:forEach var="index" begin="${0}" end="${messages.size() - 1}">
			<jstl:set var="id" value="id[${index}]"/>
			  <div class="card border-secondary mb-3">
			 	 <div class="card-body">
					<acme:form-textarea code="authenticated.message.form.label.title" path="title[${index}]"/>
					<acme:form-textarea code="authenticated.message.form.label.body" path="body[${index}]"/>
					<acme:form-textarea code="authenticated.message.form.label.tags" path="tags[${index}]"/>
			 		<div align="right">
			 		<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/message/show?id=${requestScope[id]}')" class="btn btn-primary">
				<acme:message code="authenticated.threads.form.label.message"/>
				</button>
				</div>
			 	</div>
			  </div>
			</jstl:forEach>
	 </div>
	</div>
	<acme:form-return code="authenticated.threads.form.button.return"/>
</acme:form>
