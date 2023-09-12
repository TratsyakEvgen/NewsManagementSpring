<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<script>
   $("#csrf").val("${_csrf.token}");
</script>

<c:forEach var="news" items="${listNews}">
	<a class="text-decoration-none text-reset"
		<security:authorize access="isAuthenticated()">
		href="javascript: get('#main', 'news/auth/newsView?id=${news.id}', setCookie('main','news/auth/newsView?id=${news.id}'))"
		</security:authorize>>
		${news.contents.get(0).title} </a>
	<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
		value="${news.dateTime}" />
	<hr class="my-3">
</c:forEach>