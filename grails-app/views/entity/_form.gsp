<%@ page import="me.nvorotnikov.grailstest.Entity" %>



<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="entity.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${entityInstance?.title}"/>

</div>

