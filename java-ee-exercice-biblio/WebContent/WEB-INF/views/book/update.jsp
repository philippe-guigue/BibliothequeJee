<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage
	int isbn= Integer.valueOf(request.getParameter("id"));
	String title = (String) request.getParameter("title");
	String subtitle = (String) request.getParameter("subtitle");
	String authorName = (String) request.getParameter("authorName");
	String authorSurname = (String) request.getParameter("authorSurname");
	String catalog = (String) request.getParameter("catalog");
%>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/book.css"/>
<title>Livre - Modification</title>
</head>
<body>
	<h1>Livre - Modification</h1>
	<hr>
	<form action="UpdateBook" method="get">
		<table>
		<tr>
				<td>ISBN</td>
				<td><input name="txtIsbn" value="${isbn}"  type="text"
					size="20"></td>
			</tr>

			<tr>
				<td>Titre Livre</td>
				<td><input name="txtTitle" value="${title}" type="text"
					size="20"></td>
			</tr>
			<tr>
				<td>Sous-titre</td>
				<td><input name="txtSubTitle" value="${subtitle}" type="text"
					size="20"></td>
			</tr>
			<tr>
				<td>Catalogue</td>
				<td><input name="txtCatalog" value="${catalog}" type="text"
					size="20">
				<select name="listC">
						<c:forEach var="catalog" items="${catalogs}">
						<option value="${catalog.id} ${catalog.title}">${catalog.id} - ${catalog.title}</option>
							
						</c:forEach>

				</select></td>
			</tr>
			<tr>
				<td>Auteur</td>
				<td><input name="txtAuteur" value="${authorName} ${authorSurname}" type="text"
					size="20">
				<select name="listA">
						<c:forEach var="author" items="${authors}">
						<option value="${author.id}  ${author.nom} ${author.prenom}">${author.id} - ${author.nom} ${author.prenom}</option>
						
						</c:forEach>

				</select></td>
			</tr>

		
				<tr>
					<td><input type="submit" value="Modifier"></td>
					<td><a href="<c:url value="/book/listbook"/>"><input type="button" value="Annuler"></a></td>
					
				</tr>
			</table>
	</form>
</body>
</html>