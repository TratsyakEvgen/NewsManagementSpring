<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<fmt:message bundle="${loc}" key="local.registration.date"
	var="registration_date" />
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.delete" var="delete" />

<script>
   $("#csrf").val("${_csrf.token}");
</script>

<div class="row" id="accordion">
	<div class="collapse collapse-horizontal show" id="info"
		data-bs-parent="#accordion">
		<div class="row">
			<div class="col-2">
				<h5>${first_name}</h5>
			</div>
			<div class="col-10">
				<h5>${user.name}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${last_name}</h5>
			</div>
			<div class="col-10">
				<h5>${user.surname}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${email}</h5>
			</div>
			<div class="col-10">
				<h5>${user.email}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${registration_date}</h5>
			</div>
			<div class="col-10">
				<h5>
					<fmt:formatDate type="date" value="${user.date}" />
				</h5>
			</div>
		</div>
	</div>


	<div class="collapse collapse-horizontal" id="updateInfo"
		data-bs-parent="#accordion">
		<form id="update">
			<label class="form-label">${first_name}</label>
			<div class="row p-1">
				<input type="text" name="name" class="form-control"
					value="${user.name}">
			</div>

			<label class="form-label">${last_name}</label>
			<div class="row p-1">
				<input type="text" name="surname" class="form-control"
					value="${user.surname}">
			</div>

			<label class="form-label">${email}</label>
			<div class="row p-1">
				<input type="email" name="email" class="form-control"
					value="${user.email}">
			</div>

			<div class="row">
				<div class="col">
					<input type="button" class="btn btn-dark btn-outline-light"
						onclick="javascript: post('#update', '#main', 'userDitails/auth/update')"
						value="${update}">
				</div>
			</div>
		</form>
	</div>


	<div class="collapse collapse-horizontal" id="updatePassword"
		data-bs-parent="#accordion">
		<form id="pass">
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

			<div class="row">
				<div class="col">
					<input type="button" class="btn btn-dark btn-outline-light"
						onclick="javascript: login('#pass','#error','users/auth/updatePassword')"
						value="${update}">
				</div>
			</div>
		</form>
	</div>



	<div class="collapse collapse-horizontal" id="delete"
		data-bs-parent="#accordion">
		<form id="delete">
			<div class="row">
				<div class="col">
					<input type="button" class="btn btn-dark btn-outline-light"
						value="${delete}"
						onclick="javascript: login('#delete','#error','users/auth/delete')">
				</div>
			</div>
		</form>
	</div>
</div>
