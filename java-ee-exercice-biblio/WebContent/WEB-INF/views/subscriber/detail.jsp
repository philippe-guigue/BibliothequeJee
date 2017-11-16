<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage
	int id = Integer.valueOf(request.getParameter("id"));
	String name = (String) request.getParameter("name");
	String surname = (String) request.getParameter("surname");
	String book = (String) request.getParameter("book");
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/subscriber.css" />
<title>Abonne - Liste livre</title>
</head>
<body>
	<h1>Abonne - Liste livre</h1>
		<a href="<c:url value="/subscriber/listsub"/>"><input type="button" value="menu abonné"></a>
	
	<hr>




	<table>
		<tr>
			<td>Id</td>
			<td><input name="txtId" value="${id}" type="text" size="20"></td>
		</tr>

		<tr>
			<td>Nom Abonne</td>
			<td><input name="txtNom" value="${name}" type="text" size="20"></td>
		</tr>
		<tr>
			<td>Prenom Abonne</td>
			<td><input name="txtPrenom" value="${surname}" type="text"
				size="20"></td>
		</tr>
		<tr>
			<td>Livre</td>
			<td><select id="listB" multiple>
					<c:forEach var="book" items="${books}">
						<option value="${book.isbn} ${book.title}">${book.isbn}-
							${book.title}</option>

					</c:forEach>

			</select></td>
		</tr>

	</table>
	
	

</body>
</html>