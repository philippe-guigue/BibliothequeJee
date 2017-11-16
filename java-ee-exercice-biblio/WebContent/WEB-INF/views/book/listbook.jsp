<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/book.css" />
<title>MVC-Biblio</title>
</head>
<body>
	<h1>Liste des livres</h1>


	<a href="<c:url value="/"/>"><input type="button"
		value="Retour page d'accueil"></a>

	<a href="<c:url value="/author/listauthor"/>"><input type="button"
		value="Menu auteur"></a>

	<a href="<c:url value="/copy/listcopy"/>"><input type="button"
		value="menu exemplaire"></a>

	<a href="<c:url value="/subscriber/listsub"/>"><input type="button"
		value="menu abonné"></a>
	<a href="<c:url value="/catalog/listcatalog"/>"><input
		type="button" value="menu catalogue"></a>
	<hr>
	<div id="searchbar">
		<form action="search" class="formulaire">
			<input class="champ" type="search" name="recherche" list="book_list"
				autocomplete="off" placeholder="Saisir titre/Isbn" required><input
				type="reset" value="Effacer" id="reset" name="reset" />
			<datalist id="book_list">
				<c:forEach var="livres" items="${books}">
					<option value="${livres.isbn} ${livres.title} ">${livres.isbn}
						- ${livres.title}</option>
				</c:forEach>
			</datalist>
			<input class="bouton" type="submit" value="" />

		</form>

	</div>


	<hr>

	<a href="<c:url value="/book/add"/>"><input type="button"
		value="Ajout"></a>


	<a href="<c:url value="/book/listbook"/>"><input type="button"
		value="Retour"></a>

	<table id="list" border="1">
		<tr>
			<th>Isbn</th>
			<th>Titre livre</th>
			<th>Sous-titre</th>
			<th>Auteur Nom</th>
			<th>Auteur Prenom</th>
			<th>Catalogue</th>

		</tr>

		<c:forEach var="livre" items="${books}">
			<tr>
				<td><c:out value="${livre.isbn}" /></td>
				<td><c:out value="${livre.title}" /></td>
				<td><c:out value="${livre.subtitle}" /></td>
				<td><c:out value="${livre.nomAuteur}" /></td>
				<td><c:out value="${livre.prenomAuteur}" /></td>
				<td><c:out value="${livre.nomCatalogue}" />
					<button id="button">
						<img src="../img/fight.jpg" value="">
					</button></td>
				<!--  <td><a href="<c:url value="/details?id=${livre.isbn}"/>">Details</a></td>-->
				<td><a
					href="<c:url value="/book/update?id=${livre.isbn}&title=${livre.title}&subtitle=${livre.subtitle}&catalog=${livre.nomCatalogue}&authorName=${livre.nomAuteur}&authorSurname=${livre.prenomAuteur}"/>">Modifier</a></td>
				<td><a
					href="<c:url value="/book/deleteBook?id=${livre.isbn}&title=${livre.title}&subtitle=${livre.subtitle}&catalog=${livre.nomCatalogue}&authorName=${livre.nomAuteur}&authorSurname=${livre.prenomAuteur}"/>">Supprimer</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>



</body>
</html>