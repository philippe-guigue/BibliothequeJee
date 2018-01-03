<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/author.css" />
<title>MVC-author</title>
</head>
<body>
	<h1>Liste des auteurs</h1>

	<a href="<c:url value="/"/>"><input type="button"
		value="Retour page d'acceuil"></a>

	<a href="<c:url value="/book/listbook"/>"><input type="button"
		value="menu livre"></a>

	<a href="<c:url value="/copy/listcopy"/>"><input type="button"
		value="menu exemplaire"></a>

	<a href="<c:url value="/subscriber/listsub"/>"><input type="button"
		value="menu abonné"></a>
		
	<a href="<c:url value="/catalog/listcatalog"/>"><input type="button"
		value="menu catalogue"></a>
	<hr>
	<div id="searchbar">
		<form action="search" class="formulaire" >
			<input class="champ" type="search" name="recherche" list="author_list" autocomplete="off"
				placeholder="Saisir nom/prenom/id auteur" required><input type="reset" value="Effacer" id="reset" name="reset" />  
				<datalist id="author_list">
						<c:forEach var="author" items="${authors}">
						<option value="${author.id} ${author.nom} ${author.prenom}">${author.id} - ${author.nom} ${author.prenom}</option>
						</c:forEach>
						</datalist><input class="bouton" type="submit" value=""/>
				
		</form>
		
	</div>


	<hr>

	<a href="<c:url value="/author/add"/>"><input type="button"
		value="Ajout"></a>
	<a href="<c:url value="/author/listauthor"/>"><input type="button" value="Retour"></a>

	<table border="1">
		<tr>
			<th>Id</th>
			<th>Nom Auteur</th>
			<th>Prenom Auteur</th>

		</tr>

		<c:forEach var="auteur" items="${authors}">
			<tr>
				<td><c:out value="${auteur.id}" /></td>
				<td><c:out value="${auteur.nom}" /></td>
				<td><c:out value="${auteur.prenom}" /></td>
				<!--  <td><a href="<c:url value="/details?id=${livre.isbn}"/>">Details</a></td>-->
				<td><a
					href="<c:url value="/author/update?id=${auteur.id}&name=${auteur.nom}&surname=${auteur.prenom}"/>">Modifier</a></td>
				<td><a
					href="<c:url value="/author/deleteAuthor?id=${auteur.id}&name=${auteur.nom}&surname=${auteur.prenom}"/>">Supprimer</a></td>
				<td><a
					href="<c:url value="/author/detail?id=${auteur.id}&name=${auteur.nom}&surname=${auteur.prenom}"/>">Details</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
<!--
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/recherche.js"></script>
-->
</body>
</html>