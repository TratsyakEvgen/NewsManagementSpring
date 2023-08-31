<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="localization.local" var="loc" />


<c:forEach var="news" items="${listNews}">
	<a class="text-decoration-none text-reset"
		href="javascript: get('main', '#main', 'news/newsView?id=${news.id}')">
		${news.contents.get(0).title} </a>
	<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
		value="${news.dateTime}" />
	<hr class="my-3">
</c:forEach>