<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.done" var="done" />
<fmt:message bundle="${loc}" key="local.news.management"
	var="news_management" />


<!DOCTYPE html>
<html class="h-100">

<head>
<meta charset="utf-8">
<link rel="stylesheet" href="styles/bootstrap.css">
<script type="text/javascript" src="script/jquery.min.js"></script>
<script type="text/javascript" src="script/jquery.cookie.js"></script>
<script type="text/javascript" src="script/visualState.js"></script>
<title>${news_management}</title>
</head>


<body class="d-flex flex-column h-100">

	<input type="hidden" id="csrf" value="${_csrf.token}" />

	<header class="navbar bg-dark sticky-top navbar-expand-lg"
		data-bs-theme="dark" id="header"> </header>

	<div class="toast-container position-fixed top-0 end-0 p-5" id="error">

	</div>




	<div class="container-fluid flex-shrink-0 h-100">
		<div class="row h-100">
			<div class="sidebar border border-right col-md-4 col-lg-3 p-0">
				<div class="offcanvas-md offcanvas-end" tabindex="-1"
					id="sidebarMenu" aria-labelledby="sidebarMenuLabel">
					<div class="offcanvas-header">
						<button type="button" class="btn-close"
							data-bs-dismiss="offcanvas" data-bs-target="#sidebarMenu"></button>
					</div>
					<div class="offcanvas-body d-md-flex flex-column p-1 pt-lg-3"
						id="menu"></div>
				</div>
			</div>

			<main class="col-md-8 ms-sm-auto col-lg-9 px-md-4">
				<div class="container h-100" id="main"></div>
			</main>
		</div>
	</div>





	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body" id="select"></div>
			</div>
		</div>
	</div>


	<footer class="fixed-bottom bg-dark text-center text-white">
		2023 Copyright: <a class="text-decoration-none text-reset"
			href="https://Sharaga.com/">Sharaga.com</a>
	</footer>




	<script src="script/popper.min.js"></script>
	<script src="script/bootstrap.js"></script>
</body>
</html>