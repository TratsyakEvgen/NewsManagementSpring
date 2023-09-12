<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.info" var="info" />
<fmt:message bundle="${loc}" key="local.update.info" var="update_info" />
<fmt:message bundle="${loc}" key="local.update.password" var="update_password" />
<fmt:message bundle="${loc}" key="local.delete" var="delete" />
<fmt:message bundle="${loc}" key="local.back" var="back" />

<script>
   $("#csrf").val("${_csrf.token}");
</script>

<ul class="nav flex-column">

	<li><a class="text-decoration-none text-reset"
		data-bs-toggle="collapse" role="button" data-bs-target="#info">${info}</a>
		<hr class="my-3"></li>

	<li><a class="text-decoration-none text-reset"
		data-bs-toggle="collapse" role="button" data-bs-target="#updateInfo">${update_info}</a>
		<hr class="my-3"></li>

	<li><a class="text-decoration-none text-reset"
		data-bs-toggle="collapse" role="button"
		data-bs-target="#updatePassword">${update_password}</a>
		<hr class="my-3"></li>

	<li><a class="text-decoration-none text-reset"
		data-bs-toggle="collapse" role="button" data-bs-target="#delete">${delete}</a>
		<hr class="my-3"></li>
	<li><a class="text-decoration-none text-reset" href="javascript: get('#menu', $.cookie('menuBack'), setCookie('menu',$.cookie('menuBack')))">${back}</a>
</ul>