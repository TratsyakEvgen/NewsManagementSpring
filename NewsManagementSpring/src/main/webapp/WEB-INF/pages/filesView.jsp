<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.upload" var="upload" />
<fmt:message bundle="${loc}" key="local.update" var="update" />
<fmt:message bundle="${loc}" key="local.delete" var="delete" />
<fmt:message bundle="${loc}" key="local.file" var="file" />


<div class="row">
	<div class="col">
		<form id="uploadFile">
			<input type="hidden" name="dir" value="${dir}"> <input
				type="file" name="file" /> <input type="button"
				class="btn btn-dark btn-outline-light" value="${upload}"
				onclick="javascript: multipart('#uploadFile', '#main', 'files/upload')">
		</form>
	</div>
</div>

<div class="row table-responsive">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">${file}</th>
				<th scope="col">${update}</th>
				<th scope="col">${delete}</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="file" items="${files}" varStatus="loop">
				<tr>
					<th scope="row"><a href="${file}">${file}</a></th>
					<td>
						<form id="update_${loop.index}">
							<input type="hidden" name="dir" value="${dir}"> <input
								type="hidden" name="link" value="${file}"> <input
								type="file" name="file" /> <input type="button"
								class="btn btn-dark btn-outline-light" value="${update}"
								onclick="javascript: multipart('#update_${loop.index}', '#main', 'files/update')">
						</form>
					</td>
					<td>
						<form id="delete_${loop.index}">
							<input type="hidden" name="dir" value="${dir}"> <input
								type="hidden" name="link" value="${file}"> <input
								type="button" class="btn btn-dark btn-outline-light"
								value="${delete}"
								onclick="javascript: post('#delete_${loop.index}', '#main', 'files/delete')">
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>



