<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>



<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.lang" var="lang" />
<fmt:message bundle="${loc}" key="local.ru" var="ru" />
<fmt:message bundle="${loc}" key="local.en" var="en" />
<fmt:message bundle="${loc}" key="local.sign.in" var="sign_in" />
<fmt:message bundle="${loc}" key="local.sign.out" var="sign_out" />
<fmt:message bundle="${loc}" key="local.news.management"
	var="news_management" />
<fmt:message bundle="${loc}" key="local.main" var="main" />
<fmt:message bundle="${loc}" key="local.profile" var="profile" />
<fmt:message bundle="${loc}" key="local.control.panel"
	var="control_panel" />

<c:set var="user" value="${sessionScope.user}"></c:set>


<div class="container-fluid">
	<div class="mb-2 me-2">
		<a class="navbar-brand" type="button" data-bs-toggle="dropdown">${news_management}</a>
		<ul class="dropdown-menu">
			<li><a class="dropdown-item"
				href="controller?command=go_to_base_page">${main}</a></li>
			<security:authorize access="isAuthenticated()">
				<security:authorize access="hasRole('admin')">
					<li><a class="dropdown-item"
						href="javascript: get('menu', '#menu', 'menuControlPanel')">${control_panel}</a></li>
				</security:authorize>
				<li><a href="controller?command=go_to_account"
					class="dropdown-item">${profile}</a></li>
				<li><a href="controller?command=do_sign_out"
					class="dropdown-item">${sign_out}</a></li>
			</security:authorize>
		</ul>
	</div>
	<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
		data-bs-target="#navbarContent">
		<span class="dropdown-toggle"></span>
	</button>

	<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
		data-bs-target="#sidebarMenu">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarContent">
		<div class="dropdown mb-2 me-2 ms-auto text-white">
			${language}
			<button class="btn btn-dark btn-outline-light dropdown-toggle"
				type="button" data-bs-toggle="dropdown" aria-expanded="false">${lang}</button>
			<ul class="dropdown-menu">
				<li><a class="dropdown-item"
					href="javascript:$.cookie('locale', 'en'); updateAll()">${en}</a></li>
				<li><a class="dropdown-item"
					href="javascript:$.cookie('locale', 'ru'); updateAll()">${ru}</a></li>
			</ul>
		</div>




		<security:authorize access="isAnonymous()">
			<a href="javascript: get('main', '#main', 'login')"
				class="btn btn-dark btn-outline-light mb-2 me-2">${sign_in}</a>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<div class="d-flex mb-2 me-2">
				<a href="controller?command=do_sign_out"
					class="btn btn-dark btn-outline-light">${sign_out}</a>
			</div>
		</security:authorize>

	</div>
</div>






