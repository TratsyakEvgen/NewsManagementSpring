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
<script>
	function initCookie() {
		if ($.cookie('menu') == undefined) {
			$.cookie('menu', 'news/newsline');
			$.cookie('menuBack', 'news/newsline');
		}

		if ($.cookie('header') == undefined) {
			$.cookie('header', 'header');
			$.cookie('headerBack', 'header');
		}

		if ($.cookie('main') == undefined) {
			$.cookie('main', 'news/newsCarousel');
			$.cookie('mainBack', 'news/newsCarousel');
		}

		if ($.cookie('locale') == undefined) {
			$.cookie('locale', 'en');
		}

	}

	initCookie();

	function get(cookieName, element, url) {
		$.ajax({
			url : url,
			headers : {
				'Accept-Language' : $.cookie("locale")
			},
			type : "GET",
			statusCode : {
				200 : function(data) {
					if ($.cookie(cookieName) != url) {
						$.cookie(cookieName + 'Back', $.cookie(cookieName));
					}
					$.cookie(cookieName, url);
					$(element).html(data);
				},
				418 : function(data) {
					$("#error").html(data.responseText);
				},
			}
		});
	}

	function multipart(form, element, url) {
		var form = $(form)[0];
		var data = new FormData(form);

		$.ajax({
			type : "POST",
			headers : {
				'Accept-Language' : $.cookie("locale")
			},
			enctype : 'multipart/form-data',
			url : url,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			statusCode : {
				200 : function(data) {
					$(element).html(data);
				},
				418 : function(data) {
					$("#error").html(data.responseText);
				},
			}
		});
	}

	function login() {
		$.ajax({
			url : 'login',
			headers : {
				'Accept-Language' : $.cookie("locale")
			},
			data : $("#login").serialize(),
			type : "POST",
			statusCode : {
				200 : function(data) {
					$.cookie('main', $.cookie('mainBack'));
					updateAll();
				},
				418 : function(data) {
					$("#error").html(data.responseText);
				},
			}
		});
	}

	function post(form, element, url) {
		$.ajax({
			url : url,
			headers : {
				'Accept-Language' : $.cookie("locale")
			},
			data : $(form).serialize(),
			type : "POST",
			statusCode : {
				200 : function(data) {
					$(element).html(data);
				},
				418 : function(data) {
					$("#error").html(data.responseText);
				},
			}
		});
	}

	function updateAll() {
		get('main', '#main', $.cookie('main'));
		get('menu', '#menu', $.cookie('menu'));
		get('header', '#header', $.cookie('header'));
	}

	$(document).ready(function() {
		updateAll();
	});
</script>
<title>${news_management}</title>
</head>


<body class="d-flex flex-column h-100">
	<header class="navbar bg-dark sticky-top navbar-expand-lg"
		data-bs-theme="dark" id="header">
		
	</header>

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
						id="menu">
						
					</div>
				</div>
			</div>

			<main class="col-md-8 ms-sm-auto col-lg-9 px-md-4">
				<div class="container h-100" id="main">
				</div>
			</main>
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