<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage
	String nom = (String) request.getAttribute("nom");
	String prenom = (String) request.getAttribute("prenom");
	
%>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/author.css"/>
<title>Auteur - Ajout</title>
</head>
<body>
	<h1>Auteur - Ajout</h1>
	<hr>
	<form action="addAuthor" method="get">
		<table>
	
			<tr>
				<td>Nom Auteur</td>
				<td><input name="txtNom" value="" type="text"
					size="20"></td>
			</tr>
			<tr>
				<td>Prenom Auteur</td>
				<td><input name="txtPrenom" value="" type="text"
					size="20"></td>
			</tr>
			
				<tr>
					<td><input type="submit" value="Ajouter"></td>
					<td><input type="reset" value="Effacer"></td>
					<td><a href="<c:url value="/author/listauthor"/>"><input type="button" value="Annuler"></a></td>
				</tr>
			</table>
	</form>
</body>
</html>