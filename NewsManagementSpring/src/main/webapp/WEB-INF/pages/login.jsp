<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.enter.login" var="enter_login" />
<fmt:message bundle="${loc}" key="local.enter.password"
	var="enter_password" />
<fmt:message bundle="${loc}" key="local.registration" var="registration" />
<fmt:message bundle="${loc}" key="local.sign.in" var="sign_in" />



<div
	class="container d-flex h-100 justify-content-center align-items-center">
	<div class="row text-bg-dark rounded p-3">
		<div class="col d-flex flex-column">

			<form id="login">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <label class="form-label">${login}</label>
				<div class="row p-1">
					<input type="text" name="username" class="form-control"
						placeholder="${enter_login}">
				</div>
				<label class="form-label">${password}</label>
				<div class="row p-1">
					<input type="password" name="password" class="form-control"
						placeholder="${enter_password}">
				</div>
				<div class="row d-flex justify-content-center p-1">
					<input type="button" class="btn btn-dark btn-outline-light"
						value="${sign_in}" onclick="javascript: login()">
				</div>
			</form>
			<div class="row d-flex justify-content-center p-1">
				<a class="btn btn-dark btn-outline-light"
					href="javascript: get('main', '#main', 'registration')">${registration}</a>
			</div>
		</div>
	</div>
</div>
