<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.id" var="id" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.link" var="link" />
<fmt:message bundle="${loc}" key="local.news.date" var="news_date" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.active" var="active" />
<fmt:message bundle="${loc}" key="local.deleted" var="deleted" />
<fmt:message bundle="${loc}" key="local.images" var="images" />
<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.ru" var="ru" />
<fmt:message bundle="${loc}" key="local.en" var="en" />
<fmt:message bundle="${loc}" key="local.news.author" var="author" />
<fmt:message bundle="${loc}" key="local.first.name" var="first_name" />
<fmt:message bundle="${loc}" key="local.last.name" var="last_name" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.registration.date"
	var="registration_date" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.user" var="user" />
<fmt:message bundle="${loc}" key="local.admin" var="admin" />
<fmt:message bundle="${loc}" key="local.add" var="add" />
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.back" var="back" />


<script>
	$("#csrf").val("${_csrf.token}");
</script>
<div class="d-flex flex-row  mb-3">
	<a
		href="javascript: get('#main', $.cookie('mainBack'), setCookie('main',$.cookie('mainBack')))"
		class="btn btn-dark btn-outline-light">${back}</a>
	<div class="col">
		<input type="button" class="btn btn-dark btn-outline-light"
			onclick="javascript: get('main', 'news/admin/create', setCookie('main','news/admin/create'))"
			value="${add}">
	</div>
</div>

<div class="row table-responsive">




	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th scope="col">${id}</th>
				<th scope="col">${news_date}</th>
				<th scope="col">${images}</th>
				<th scope="col">${author}</th>
				<th scope="col">${status}</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="news" items="${listNews}">
				<tr>
					<th scope="row">${news.id}</th>
					<td><fmt:formatDate type="both" dateStyle="long"
							timeStyle="long" value="${news.dateTime}" /></td>
					<td><c:forEach var="image" items="${news.images}">
							<div>${id}:${image.id}</div>
							<div>${link}:
								<a href="${image.link}">${image.link}</a>
							</div>
							<div>${status}:
								<c:if test="${image.status}">${active}</c:if>
								<c:if test="${!image.status}">${deleted}</c:if>
							</div>
							<hr class="my-3">
						</c:forEach></td>
					<c:set var="user" value="${news.userDitails}" />
					<td>
						<div>${id}:${user.id}</div>
						<div>${first_name}:${user.name}</div>
						<div>${last_name}:${user.surname}</div>
						<div>${email}:${user.email}</div>
						<div>${user.date}:
							<fmt:formatDate type="date" value="${user.date}" />
						</div>
					</td>
					<td><c:if test="${news.status}">${active}</c:if> <c:if
							test="${!news.status}">${deleted}</c:if></td>
					<td>
						<div class="col">
							<input type="button" class="btn btn-dark btn-outline-light"
								onclick="javascript: get('#main', 'news/admin/update/${news.id}', setCookie('main','news/admin/update/${news.id}'))"
								value="${update}">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th scope="col">${id}</th>
									<th scope="col">${language}</th>
									<th scope="col">${news_title}</th>
									<th scope="col">${link}</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="content" items="${news.contents}">
									<tr>
										<th scope="row">${content.id}</th>
										<td>${content.locale.locale}</td>
										<td><a
											href="javascript: get('#main', 'news/auth/newsView?id=${news.id}', setCookie('main','news/auth/newsView?id=${news.id}'))">
												${content.title}</a></td>
										<td><a href="${content.link}">${content.link}</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


