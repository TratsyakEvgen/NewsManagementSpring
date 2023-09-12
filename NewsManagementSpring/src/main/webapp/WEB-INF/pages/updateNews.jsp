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
<fmt:message bundle="${loc}" key="local.delete" var="delete" />
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
<fmt:message bundle="${loc}" key="local.add" var="add" />
<fmt:message bundle="${loc}" key="local.select" var="select" />
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.save" var="save" />
<fmt:message bundle="${loc}" key="local.content" var="content" />
<fmt:message bundle="${loc}" key="local.back" var="back" />



<script>
	$("#csrf").val("${_csrf.token}");
</script>

<div class="d-flex flex-row  mb-3">
	<a
		href="javascript: get('#main', $.cookie('mainBack'), setCookie('main',$.cookie('mainBack')))"
		class="btn btn-dark btn-outline-light">${back}</a>
</div>
<div class="row table-responsive">
	<table class="table table-hover table-bordered">
		<tbody>
			<tr>
				<td>${id}</td>
				<td>${news.id}</td>
			</tr>

			<tr>
				<td>${news_date}</td>
				<td><fmt:formatDate type="both" dateStyle="long"
						timeStyle="long" value="${news.dateTime}" /></td>
			</tr>

			<tr>
				<td>${images}</td>
				<td><c:forEach var="image" items="${news.images}">
						<div>${id}:${image.id}</div>
						<div>${link}:
							<a href="${image.link}">${image.link}</a>
						</div>
						<div>${status}:
							<c:if test="${image.status}">${active}</c:if>
							<c:if test="${!image.status}">${deleted}</c:if>
						</div>
						<form id="deleteImage">
							<input type="hidden" name="idImage" value="${image.id}">
							<input type="hidden" name="idNews" value="${news.id}">
							<div class="col">
								<input type="button" class="btn btn-dark btn-outline-light"
									onclick="javascript: post('#deleteImage', '#main', 'news/admin/deleteImage')"
									value="${delete}">
							</div>
						</form>
						<hr class="my-3">
					</c:forEach>

					<div class="col">
						<form id="addImage">
							<input type="hidden" name="idNews" value="${news.id}"> <input
								type="hidden" id="idImage" name="idImage" value="">
						</form>
						<input type="button" class="btn btn-dark btn-outline-light"
							data-bs-toggle="modal" data-bs-target="#exampleModal"
							onclick="javascript: get('select', '#select', 'images/admin/get?select=true')"
							value="${add}">
					</div></td>
			</tr>

			<tr>
				<td>${author}</td>
				<c:set var="user" value="${news.userDitails}" />
				<td>
					<div>${id}:${user.id}</div>
					<div>${first_name}:${user.name}</div>
					<div>${last_name}:${user.surname}</div>
					<div>${email}:${user.email}</div>
					<div>${registration_date}:
						<fmt:formatDate type="date" value="${user.date}" />
					</div>
					<form id="addUser">
						<input type="hidden" name="idNews" value="${news.id}"> <input
							type="hidden" id="idUser" name="idUser" value="">
					</form>
					<div class="col">
						<input type="button" class="btn btn-dark btn-outline-light"
							data-bs-toggle="modal" data-bs-target="#exampleModal"
							onclick="javascript: get('select', '#select', 'users/admin/userList?select=true')"
							value="${select}">
					</div>

				</td>
			</tr>

			<tr>
				<td>${content}</td>
				<td><c:forEach var="content" items="${news.contents}">
						<div>${id}:${content.id}</div>

						<div>${language}:

							<c:if test="${content.locale.locale == 'en'}">${en}</c:if>
							<c:if test="${content.locale.locale == 'ru'}">${ru}</c:if>

						</div>

						<div>${news_title}:
							<a
								href="href=controller?command=go_to_view_news_as_admin&id=${content.id}">
								${content.title} </a>
						</div>

						<div>${link}:
							<a href="${content.link}">${content.link}</a>
						</div>
						<div class="d-flex flex-row  mb-3">
							<button class="btn btn-dark btn-outline-light" type="button"
								data-bs-toggle="collapse" data-bs-target="#${content.id}">
								${update}</button>


							<form id="deleteContent_${content.id}">
								<input type="hidden" name="idContent" value="${content.id}">
								<input type="hidden" name="idNews" value="${news.id}"> <input
									type="button" class="btn btn-dark btn-outline-light"
									onclick="javascript: post('#deleteContent_${content.id}', '#main', 'contents/admin/deleteContent')"
									value="${delete}">

							</form>
						</div>
						<div class="collapse" id="${content.id}">
							<form id="updateContent_${content.id}">
								<input type="hidden" name="id" value="${content.id}"> <input
									type="hidden" name="idNews" value="${news.id}"> <label
									class="form-label">${language}:</label> <select
									class="form-select" name="locale">
									<option value="ru"
										<c:if test="${content.locale.locale == 'ru'}">selected</c:if>>${ru}</option>
									<option value="en"
										<c:if test="${content.locale.locale == 'en'}">selected</c:if>>${en}</option>
								</select> <label class="form-label">${news_title}:</label> <input
									type="text" name="title" class="form-control"
									value="${content.title}"> <label class="form-label">${link}:</label>
								<input type="text" name="link" class="form-control"
									value="${content.link}"> <input type="button"
									class="btn btn-dark btn-outline-light"
									onclick="javascript: post('#updateContent_${content.id}', '#main', 'contents/admin/updateContent')"
									value="${save}">

							</form>
						</div>
						<hr class="my-3">
					</c:forEach>

					<button class="btn btn-dark btn-outline-light" type="button"
						data-bs-toggle="collapse" data-bs-target="#0">${add}</button>
					<div class="collapse" id="0">
						<form id="addContent">
							<input type="hidden" name="idNews" value="${news.id}"> <label
								class="form-label">${language}:</label> <select
								class="form-select" name="locale">
								<option value="ru">${ru}</option>
								<option value="en" selected>${en}</option>
							</select> <label class="form-label">${news_title}:</label> <input
								type="text" name="title" class="form-control"> <label
								class="form-label">${link}:</label> <input type="text"
								name="link" class="form-control"> <input type="button"
								class="btn btn-dark btn-outline-light"
								onclick="javascript: post('#addContent', '#main', 'contents/admin/addContent')"
								value="${save}">

						</form>
					</div></td>

			</tr>
			<tr>
				<td>${status}</td>
				<td>
					<div class="d-flex flex-row  mb-3">
						<div class="d-flex flex-row  mb-3">
							<form id="status">
								<input type="hidden" name="idNews" value="${news.id}"> <select
									class="form-select" name="status"
									onchange="javascript: postNoUpdate('#status', 'news/admin/updateStatus')">
									<option value="true"
										<c:if test="${news.status}">selected</c:if>>${active}</option>
									<option value="false"
										<c:if test="${!news.status}">selected</c:if>>${deleted}</option>
								</select>
							</form>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>






