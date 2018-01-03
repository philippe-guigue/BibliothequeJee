<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage
	int id= Integer.valueOf(request.getParameter("id"));
	String name = (String) request.getParameter("name");
	String surname = (String) request.getParameter("surname");

%>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/subscriber.css"/>
<title>Abonné - Modification</title>
</head>
<body>
	<h1>Abonné - Modification</h1>
	<hr>
	<form action="UpdateSub" method="get">
		<table>
		<tr>
				<td>Id</td>
				<td><input name="txtId" value="${id}"  type="text"
					size="20"></td>
			</tr>

			<tr>
				<td>Nom Auteur</td>
				<td><input name="txtNom" value="${name}" type="text"
					size="20"></td>
			</tr>
			<tr>
				<td>Prenom Auteur</td>
				<td><input name="txtPrenom" value="${surname}" type="text"
					size="20"></td>
			</tr>
			
				<tr>
					<td><input type="submit" value="Modifier"></td>
					<td><a href="<c:url value="/subscriber/listsub"/>"><input type="button" value="Annuler"></a></td>
					
				</tr>
			</table>
	</form>
</body>
</html>