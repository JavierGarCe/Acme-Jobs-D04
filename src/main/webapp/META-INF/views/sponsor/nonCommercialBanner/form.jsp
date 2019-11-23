<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="sponsor.banner.nonCommercial.form.label.picture" path="picture"/>
	<acme:form-textbox code="sponsor.banner.nonCommercial.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.banner.nonCommercial.form.label.targetUrl" path="targetUrl"/>
	<acme:form-textbox code="sponsor.banner.nonCommercial.form.label.jingle" path="jingle"/>
  	<acme:form-return code="sponsor.banner.nonCommercial.form.button.return"/>
</acme:form>