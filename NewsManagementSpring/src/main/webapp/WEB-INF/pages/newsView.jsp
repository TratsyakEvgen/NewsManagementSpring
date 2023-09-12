<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.news.date" var="news_date" />
<fmt:message bundle="${loc}" key="local.news.author" var="author" />
<fmt:message bundle="${loc}" key="local.back" var="back" />
<fmt:message bundle="${loc}" key="local.update" var="update" />


<c:set var="content" value="${news.contents.get(0)}"></c:set>

<script>
	$("#csrf").val("${_csrf.token}");
</script>


<div class="row">
	<div class="d-flex flex-row  mb-3">

		<a
			href="javascript: get('#main', $.cookie('mainBack'), setCookie('main',$.cookie('mainBack')))"
			class="btn btn-dark btn-outline-light">${back}</a>

		<security:authorize access="hasRole('admin')">
			<a class="btn btn-dark btn-outline-light"
				href="javascript: get('#main', 'news/admin/update/${news.id}', setCookie('main','news/admin/update/${news.id}'))">${update}</a>
		</security:authorize>


	</div>
	<div class="col">
		<div class="row">
			<div class="col-2">
				<h5>${news_title}</h5>
			</div>
			<div class="col-10">
				<h5>${content.title}</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${news_date}</h5>
			</div>
			<div class="col-10">
				<h5>
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
						value="${news.dateTime}" />
				</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<h5>${author}</h5>
			</div>
			<div class="col-10">
				<c:set var="author" value="${news.userDitails}"></c:set>
				<h5>${author.surname}${author.name}</h5>
			</div>
		</div>
	</div>
</div>


<c:set var="images" value="${news.images}"></c:set>
<c:if test="${!images.isEmpty()}">
	<div class="row justify-content-center">
		<div class="col-md-10 col-lg-8">
			<div id="carousel" class="carousel slide carousel-fade">
				<div class="carousel-inner">
					<c:forEach var="image" items="${images}" varStatus="loop">
						<div
							class="carousel-item
						<c:if test="${loop.index == 0}">active</c:if>">
							<img src="${image.link}" class="d-block w-100"
								style="height: 500px;">
						</div>
					</c:forEach>
				</div>

				<button class="carousel-control-prev" type="button"
					data-bs-target="#carousel" data-bs-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carousel" data-bs-slide="next">
					<span class="carousel-control-next-icon"></span>
				</button>
			</div>
		</div>
	</div>
</c:if>

<hr class="my-3">

<div class="row">
	<div class="col">
		<iframe src="${content.link}" width="100%" frameborder=0 scrolling=no
			onload="this.style.height=this.contentDocument.body.scrollHeight + 100 +'px';">
		</iframe>
	</div>
</div>
