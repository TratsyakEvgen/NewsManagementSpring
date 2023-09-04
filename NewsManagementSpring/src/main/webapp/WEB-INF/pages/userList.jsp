<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.active" var="active" />
<fmt:message bundle="${loc}" key="local.deleted" var="deleted" />
<fmt:message bundle="${loc}" key="local.first.name" var="first_name" />
<fmt:message bundle="${loc}" key="local.last.name" var="last_name" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.registration.date"
	var="registration_date" />
<fmt:message bundle="${loc}" key="local.id" var="id" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.select" var="select" />
<fmt:message bundle="${loc}" key="local.user" var="user_local" />
<fmt:message bundle="${loc}" key="local.admin" var="admin" />
<fmt:message bundle="${loc}" key="local.deleted" var="deleted" />



<div class="row table-responsive">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">${id}</th>
				<th scope="col">${first_name}</th>
				<th scope="col">${last_name}</th>
				<th scope="col">${email}</th>
				<th scope="col">${registration_date}</th>
				<th scope="col">${role}</th>
				<th scope="col">${status}</th>

				<c:if test="${selection}">
					<th scope="col">${select}</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}" varStatus="loop">
				<form id="${loop.index}"></form>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" form="${loop.index}" />
				<input type="hidden" name="id" value="${user.id}"
					form="${loop.index}" />


				<tr>
					<th scope="row">${user.id}</th>
					<td>${user.ditails.name}</td>
					<td>${user.ditails.surname}</td>
					<td>${user.ditails.email}</td>
					<td><fmt:formatDate type="date" value="${user.ditails.date}" /></td>
					<c:if test="${!selection}">

						<td><select class="form-select" name="role"
							onchange="javascript: postNoUpdate('#${loop.index}', 'users/userUpdateRoleOrStatus')"
							form="${loop.index}">
								<option value="user"
									<c:if test="${user.role.role == 'user'}">selected</c:if>>${user_local}</option>
								<option value="admin"
									<c:if test="${user.role.role == 'admin'}">selected</c:if>>${admin}</option>
						</select></td>

						<td><select class="form-select" name="status"
							onchange="javascript: postNoUpdate('#${loop.index}', 'users/userUpdateRoleOrStatus')"
							form="${loop.index}">
								<option value="true" <c:if test="${user.status}">selected</c:if>>${active}</option>
								<option value="false"
									<c:if test="${!user.status}">selected</c:if>>${deleted}</option>
						</select></td>

					</c:if>
				</tr>

			</c:forEach>

		</tbody>

	</table>
</div>

