<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.lastet.news" var="lastet_news" />


<script>
	$("#csrf").val("${_csrf.token}");
</script>

<div class="row text-center">
	<h1>${lastet_news}</h1>
</div>

<c:set var="listNews" value="${listNews}"></c:set>
<div class="row justify-content-center">
	<div class="col-10">

		<div id="carousel" class="carousel slide carousel-fade"
			data-bs-ride="carousel">
			<div class="carousel-inner">

				<c:forEach var="news" items="${listNews}" varStatus="loop">
					<div
						class="carousel-item
						<c:if test="${loop.index == 0}">active</c:if>">
						<p>
							<a
								<security:authorize access="isAuthenticated()">
									href="javascript: get('#main', 'news/auth/newsView?id=${news.id}', setCookie('main','news/auth/newsView?id=${news.id}'))"
									</security:authorize>>
								<c:set var="images" value="${news.images}" /> <img
								src="${images.get(0).link}" class="d-block w-100"
								style="sheight: 500px;">
							</a>
						</p>
						<div class="carousel-caption d-noned md-block ">
							<h1>${news.contents.get(0).title}</h1>
						</div>
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



