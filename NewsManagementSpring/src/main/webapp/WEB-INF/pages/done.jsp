<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.done" var="done" />

<script>
   $("#csrf").val("${_csrf.token}");
</script>

<div class="toast fade show" role="alert">
	<div class="toast-header">
		<svg class="bd-placeholder-img rounded me-2" width="20" height="20">
					<rect width="100%" height="100%" fill="green"></rect></svg>
		<strong class="me-auto">${done}</strong>
		<button type="button" class="btn-close" data-bs-dismiss="toast"></button>
	</div>
</div>
