<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.lastet.news" var="lastet_news" />

<div class="container" id="main">
	<div class="row text-center">
		<h1>${lastet_news}</h1>
	</div>

	<c:set var="listNews" value="${listNews}"></c:set>
	<div class="row justify-content-center">
		<div class="col-10">

			<div id="carousel" class="carousel slide carousel-fade"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<p>
							<a <c:set var="news" value="${listNews.get(0)}"/>
								href="javascript: get('main', '#main', 'news/newsView?id=${news.id}')">
								<c:set var="images" value="${news.images}" /> <c:if
									test="${images.isEmpty()}">
									<img src="images/gray.png" class="d-block w-100"
										style="height: 500px;">
								</c:if> <c:if test="${!images.isEmpty()}">
									<img src="${images.get(0).link}" class="d-block w-100"
										style="height: 500px;">
								</c:if>
							</a>
						</p>
						<div class="carousel-caption d-noned md-block ">
							<h1>${news.contents.get(0).title}</h1>
						</div>

					</div>

					<c:if test="${listNews.size() > 0}">
						<c:forEach var="news" items="${listNews}" begin="1">
							<div class="carousel-item">
								<p>
									<a
										href="javascript: get('main', '#main', 'news/newsView?id=${news.id}')">
										<c:set var="images" value="${news.images}" /> <c:if
											test="${images.isEmpty()}">
											<img src="images/gray.png" class="d-block w-100"
												style="height: 500px;">
										</c:if> <c:if test="${!images.isEmpty()}">
											<img src="${images.get(0).link}" class="d-block w-100"
												style="sheight: 500px;">
										</c:if>
									</a>
								</p>
								<div class="carousel-caption d-noned md-block ">
									<h1>${news.contents.get(0).title}</h1>
								</div>
							</div>
						</c:forEach>
					</c:if>
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
</div>

