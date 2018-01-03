<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage
		int id= Integer.valueOf(request.getParameter("id"));
	String description = (String) request.getParameter("title");
%>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/author.css"/>
<title>Catalogue - Liste livre</title>
</head>
<body>
	<h1>Catalogue - Liste livre</h1>
	<hr>

	<table>
		<tr>
			<td>Id</td>
			<td><input name="txtId" value="${id}" type="text" size="20"></td>
		</tr>

		<tr>
			<td>Titre Catalogue</td>
			<td><input name="txtTitle" value="${title}" type="text" size="20"></td>
		</tr>
			<tr>
			<td>Livre</td>
				<td>
				<select id="listB" multiple>
						<c:forEach var="book" items="${books}">
						<option value="${book.isbn} ${book.title}">${book.isbn} - ${book.title}</option>
							
						</c:forEach>

				</select></td>
			</tr>

	</table>

	<br>
	<a href="<c:url value="/catalog/listcatalog"/>">Menu Catalogue</a>
	
	<br>
	<a href="<c:url value="/copy/listcopy"/>">Menu exemplaire</a>
	
	<br>
	<a href="<c:url value="/book/listbook"/>">Menu Livre</a>
	
</body>
</html>