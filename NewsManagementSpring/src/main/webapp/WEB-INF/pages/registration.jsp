<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.first.name" var="first_name" />
<fmt:message bundle="${loc}" key="local.enetr.first.name"
	var="enter_first_name" />
<fmt:message bundle="${loc}" key="local.last.name" var="last_name" />
<fmt:message bundle="${loc}" key="local.enetr.last.name"
	var="enter_last_name" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.enetr.email" var="enter_email" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.repeat.password"
	var="repeat_password" />
<fmt:message bundle="${loc}" key="local.enter.login" var="enter_login" />
<fmt:message bundle="${loc}" key="local.enter.password"
	var="enter_password" />
<fmt:message bundle="${loc}" key="local.register" var="register" />



<div
	class="container d-flex h-100 justify-content-center align-items-center">
	<div class="row text-bg-dark rounded p-3">
		<div class="col d-flex flex-column">

			<form id="reg" name="regData">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <label class="form-label">${first_name}</label>
				<div class="row p-1">
					<input type="text" name="name" class="form-control"
						placeholder="${enter_first_name}">
				</div>

				<label class="form-label">${last_name}</label>
				<div class="row p-1">
					<input type="text" name="surname" class="form-control"
						placeholder="${enter_last_name}">
				</div>

				<label class="form-label">${email}</label>
				<div class="row p-1">
					<input type="email" name="email" class="form-control"
						placeholder="${enter_email}">
				</div>

				<label class="form-label">${login}</label>
				<div class="row p-1">
					<input type="text" name="username" class="form-control"
						placeholder="${enter_login}">
				</div>

				<label class="form-label">${password}</label>
				<div class="row p-1">
					<input type="password" name="password" class="form-control"
						placeholder="${enter_password}">
				</div>

				<label class="form-label">${repeat_password}</label>
				<div class="row p-1">
					<input type="password" name="repeatPassword" class="form-control"
						placeholder="${repeat_password}">
				</div>

				<div class="row d-flex justify-content-center p-1">
					<input type="button" class="btn btn-dark btn-outline-light"
						value="${register}"
						onclick="javascript: post('#reg', '#error', 'users/registration')">
				</div>
			</form>
		</div>
	</div>
</div>

