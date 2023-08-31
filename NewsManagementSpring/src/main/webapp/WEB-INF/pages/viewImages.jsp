<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.id" var="id" />
<fmt:message bundle="${loc}" key="local.link" var="link" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.active" var="active" />
<fmt:message bundle="${loc}" key="local.deleted" var="deleted" />
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.save" var="save" />
<fmt:message bundle="${loc}" key="local.add" var="add" />



<div class="row">
	<div class="col">
		<button class="btn btn-dark btn-outline-light" type="button"
			data-bs-toggle="collapse" data-bs-target="#add">${add}</button>
	</div>
	<form class="collapse" id="add">
		<div class="container">
			<div class="row gy-2">
				<div class="col-12">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <label class="form-label">${link}:</label>
					<input type="text" name="link" class="form-control">
				</div>
				<div class="col">
					<input type="button" class="btn btn-dark btn-outline-light "
						value="${save}"
						onclick="javascript: post('#add', '#main', 'images/add')">
				</div>
			</div>
		</div>
	</form>
	<hr class="my-3">
</div>

<c:forEach var="image" items="${images}">
	<div class="row">
		<div class="col-6">
			<img src="${image.link}" class="img-fluid">
		</div>
		<div class="col-6 text-break">
			<h5>${id}:${image.id}</h5>
			<h5>${link}:${image.link}</h5>
			<h5>${status}:
				<c:if test="${image.status}">${active}</c:if>
				<c:if test="${!image.status}">${deleted}</c:if>
			</h5>
			<button class="btn btn-dark btn-outline-light" type="button"
				data-bs-toggle="collapse" data-bs-target="#${image.id}">
				${update}</button>
			<div class="collapse" id="${image.id}">
				<form id="update_${image.id}">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="id"
						value="${image.id}"> <label class="form-label">${link}:</label>
					<input type="text" name="link" class="form-control"
						value="${image.link}"> <label class="form-label">${status}:</label>
					<select class="form-select" name="status">
						<option value="true" <c:if test="${image.status}">selected</c:if>>
							${active}</option>
						<option value="false"
							<c:if test="${!image.status}">selected</c:if>>
							${deleted}</option>
					</select> <label class="form-label">${save}:</label> <input type="button"
						class="btn btn-dark btn-outline-light form-control"
						value="${save}"
						onclick="javascript: post('#update_${image.id}', '#main', 'images/update')">
				</form>
			</div>
		</div>
	</div>
	<hr class="my-3">


</c:forEach>

