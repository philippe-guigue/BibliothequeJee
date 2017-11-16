<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage
	int isbn= (Integer) request.getAttribute("isbn");
	String title = (String) request.getAttribute("titre");
	String subtitle = (String) request.getAttribute("sousTitre");
	String Auteur = (String) request.getAttribute("auhtor");
	String catalog = (String) request.getAttribute("catalog");
%>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/book.css"/>
<title>Livre - Ajout</title>
</head>
<body>
	<h1>Livre - Ajout</h1>
	<hr>
	<form action="addBook" method="get">
		<table>
		<tr>
				<td>ISBN</td>
				<td><input name="txtIsbn" value=""  type="text"
					size="20"></td>
			</tr>

			<tr>
				<td>Titre Livre</td>
				<td><input name="txtTitle" value="" type="text"
					size="20"></td>
			</tr>
			<tr>
				<td>Sous-titre</td>
				<td><input name="txtSubTitle" value="" type="text"
					size="20"></td>
			</tr>
			<tr>
				<td>Catalogue</td>
				<td><select name="listC">
						<c:forEach var="catalog" items="${catalogs}">
						<option value="${catalog.id} ${catalog.title}">${catalog.id} - ${catalog.title}</option>
							
						</c:forEach>

				</select></td>
			</tr>
			<tr>
				<td>Auteur</td>
				<td><select name="listA">
						<c:forEach var="author" items="${authors}">
						<option value="${author.id}  ${author.nom} ${author.prenom}">${author.id} - ${author.nom} ${author.prenom}</option>
						
						</c:forEach>

				</select></td>
			</tr>

		
				<tr>
					<td><input type="submit" value="Ajouter"></td>
					<td><input type="reset" value="Effacer"></td>
					<td><a href="<c:url value="/book/listbook"/>"><input type="button" value="Annuler"></a></td>
				</tr>
			</table>
	</form>
</body>
</html>