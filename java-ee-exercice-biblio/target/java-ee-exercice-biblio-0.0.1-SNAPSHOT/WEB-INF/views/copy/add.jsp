<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	// on récupère les valeurs nécessaire à l'affichage

	String estDispo = (String) request.getAttribute("estDispo");
	String isbn = (String) request.getAttribute("isbn");
	String numAbonne = (String) request.getAttribute("numAbonne");
	
%>
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/copy.css"/>
<title>Exemplaire - Ajout</title>
</head>
<body>
	<h1>Exemplaire - Ajout</h1>
	<hr>
	<form action="addCopy" method="get">
		<table>
		
			<tr>
				<td>Disponibilité</td>
				<td><select name="dispo">
						
						<option value="0 - pas dispo">Non dispo</option>
							<option value="1 - dispo">Dispo</option>
								<option value="2 - en réparation">Réparation</option>
				</select>
				</td>
			</tr>
				<tr>
				<td>Livre</td>
				<td><select name="listB">
						<c:forEach var="book" items="${books}">
						<option value="${book.isbn} ${book.title} ">${book.isbn} - ${book.title}</option>
							
						</c:forEach>

				</select></td>
			</tr>
			<tr>
				<td>Abonné</td>
				<td><select name="listA">
						<c:forEach var="sub" items="${subs}">
						<option value="${sub.id} ${sub.nom} ${sub.prenom}">${sub.id} - ${sub.nom}- ${sub.prenom}</option>
							
						</c:forEach>

				</select></td>
			</tr>
			

		
				<tr>
					<td><input type="submit" value="Ajouter"></td>
					<td><input type="reset" value="Effacer"></td>
					<td><a href="<c:url value="/copy/listcopy"/>"><input type="button" value="Annuler"></a></td>
				</tr>
			</table>
	</form>
</body>
</html>