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
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/book.css"/>
<title>Catalogue - Modification</title>
</head>
<body>
	<h1>Catalogue - Modification</h1>
	<hr>
	<form action="UpdateCatalog" method="get">
		<table>
		<tr>
				<td>ISBN</td>
				<td><input name="txtId" value="${id}"  type="text"
					size="20"></td>
			</tr>

			<tr>
				<td>Titre Catalogue</td>
				<td><input name="txtTitle" value="${title}" type="text"
					size="20"></td>
			</tr>
			

		
				<tr>
					<td><input type="submit" value="Modifier"></td>
					<td><a href="<c:url value="/catalog/listcatalog"/>"><input type="button" value="Annuler"></a></td>
					
				</tr>
			</table>
	</form>
</body>
</html>