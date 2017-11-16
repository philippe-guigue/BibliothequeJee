<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage
	int id= Integer.valueOf(request.getParameter("id"));
	int isbn= Integer.valueOf(request.getParameter("Isbn"));
	int estDispo = Integer.valueOf(request.getParameter("estDispo"));
	int abonne = Integer.valueOf(request.getParameter("NumAbonne"));
	//String book = (String) request.getParameter("book");
%>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/copy.css"/>
<title>Exemplaire- Modification</title>
</head>
<body>
	<h1>Exemplaire - Modification</h1>
	<hr>
	<form action="UpdateCopy" method="get">
		<table>
		<tr>
				<td>Id</td>
				<td><input name="txtId" value="${id}"  type="text"
					size="20"></td>
			</tr>

			<tr>
				<td>Dispo</td>
				<td><input name="txtDispo" value="${estDispo}" type="text"
					size="20"></td>
			</tr>
			
			<tr>
				<td>Livre</td>
				<td><input name="txtBook" value="${Isbn}" type="text"
					size="3">
				<select name="listB">
						<c:forEach var="book" items="${books}">
						<option value="${book.isbn} ${book.title}">${book.isbn} - ${book.title}</option>
							
						</c:forEach>

				</select></td>
			</tr>
			<tr>
				<td>Abonne</td>
				<td><input name="txtAbonne" value="${NumAbonne}" type="text"
					size="3">
				<select name="listS">
						<c:forEach var="subscriber" items="${subs}">
						<option value="${subscriber.id}  ${subscriber.nom} ${subscriber.prenom}">${subscriber.id} - ${subscriber.nom} ${subscriber.prenom}</option>
						
						</c:forEach>

				</select></td>
			</tr>

		
				<tr>
					<td><input type="submit" value="Modifier"></td>
					<td><a href="<c:url value="/copy/listcopy"/>"><input type="button" value="Annuler"></a></td>
					
				</tr>
			</table>
	</form>
</body>
</html>