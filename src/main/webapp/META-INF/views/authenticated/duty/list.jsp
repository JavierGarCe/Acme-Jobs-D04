<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="authenticated.duty.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.duty.list.label.description" path="description" width="40%"/>
</acme:list>

<jstl:if test="${model$size == 1 }">
<jstl:set var="jobIdT" value="${jobId}"/>
</jstl:if>
<jstl:if test="${model$size >= 2 }">
<jstl:set var="jobId" value="jobId[0]"/>
<jstl:set var="jobIdT" value="${requestScope[jobId]}"/>
</jstl:if>

	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/job/show?id=${jobIdT}')"
			class="btn btn-light">
			<acme:message code="authenticated.job.list.button.return" />
		</button>
		