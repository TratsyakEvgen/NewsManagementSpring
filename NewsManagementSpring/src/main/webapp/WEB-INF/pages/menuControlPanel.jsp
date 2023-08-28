<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.users" var="users" />
<fmt:message bundle="${loc}" key="local.file.system.images"
	var="file_system_images" />
<fmt:message bundle="${loc}" key="local.file.system.documents"
	var="file_system_documents" />
<fmt:message bundle="${loc}" key="local.gallery" var="gallery" />
<fmt:message bundle="${loc}" key="local.news.management"
	var="news_management" />
<fmt:message bundle="${loc}" key="local.back" var="back" />


<div class="offcanvas-body d-md-flex flex-column p-1 pt-lg-3" id="menu">
	<ul class="nav flex-column">

		<li><a class="text-decoration-none text-reset"
			href="controller?command=go_to_list_users">${users}</a>
			<hr class="my-3"></li>
		<li><a class="text-decoration-none text-reset"
			href="javascript: get('main', '#main', 'news/newsManagement')">${news_management}</a>
			<hr class="my-3"></li>
		<li><a class="text-decoration-none text-reset"
			href="javascript: get('main', '#main', 'images/get')">${gallery}</a>
			<hr class="my-3"></li>
		<li><a class="text-decoration-none text-reset"
			href="javascript: get('main', '#main', 'files/get?dir=images')">${file_system_images}</a>
			<hr class="my-3"></li>
		<li><a class="text-decoration-none text-reset"
			href="javascript: get('main', '#main', 'files/get?dir=html')">${file_system_documents}</a>
			<hr class="my-3"></li>
		<li><a class="text-decoration-none text-reset"
			href="controller?command=go_to_base_page">${back}</a></li>
	</ul>
</div>