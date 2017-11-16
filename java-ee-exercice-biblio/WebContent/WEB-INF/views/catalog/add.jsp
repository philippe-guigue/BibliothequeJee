<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage
	int id= (Integer) request.getAttribute("id");
	String description = (String) request.getAttribute("description");

%>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/book.css"/>
<title>Catalogue - Ajout</title>
</head>
<body>
	<h1>Catalogue - Ajout</h1>
	<hr>
	<form action="addCatalog" method="get">
		<table>
		

			<tr>
				<td>Description</td>
				<td><input name="txtTitle" value="" type="text"
					size="20"></td>
			</tr>
		
				<tr>
					<td><input type="submit" value="Ajouter"></td>
					<td><input type="reset" value="Effacer"></td>
					<td><a href="<c:url value="/catalog/listcatalog"/>"><input type="button" value="Annuler"></a></td>
				</tr>
			</table>
	</form>
</body>
</html>