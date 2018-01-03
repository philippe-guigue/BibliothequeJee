<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/copy.css"/>
<title>MVC-Biblio</title>
</head>
<body>
	<h1>Liste des exemplaires</h1>
	
	<a href="<c:url value="/"/>"><input type="button" value="Retour page d'acceuil"></a>
	
	<a href="<c:url value="/subscriber/listsub"/>"><input type="button" value="menu abonné"></a>
	
	<a href="<c:url value="/book/listbook"/>"><input type="button" value="menu livre"></a>
	
	<a href="<c:url value="/author/listauthor"/>"><input type="button" value="menu auteur"></a>
	
	<a href="<c:url value="/catalog/listcatalog"/>"><input type="button" value="menu catalogue"></a>
	<hr>
	<div id="searchbar">
		<form action="search" class="formulaire" >
			<input class="champ" type="search" name="recherche" list="copy_list" autocomplete="off"
				placeholder="Saisir id/isbn exemplaire" required><input type="reset" value="Effacer" id="reset" name="reset" />  
				<datalist id="copy_list">
						<c:forEach var="copy" items="${copies}">
						<option value="${copy.id} ${copy.estDispo} ${copy.isbn} ${copy.numAbonne}">${copy.id} - ${copy.estDispo} ${copy.isbn} ${copy.numAbonne}</option>
						</c:forEach>
						</datalist><input class="bouton" type="submit" value=""/>
				
		</form>
		
	</div>
	<hr>
	
	<a href="<c:url value="/copy/add"/>"><input type="button" value="Ajout"></a>
	<a href="<c:url value="/copy/listcopy"/>"><input type="button" value="Retour"></a>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>DISPO</th>
			<th>ISBN</th>
			<th>ABONNE</th>
			<th></th>
		</tr>

		<c:forEach var="copy" items="${copies}">
			<tr>
				<td><c:out value="${copy.id}" /></td>
				<td><c:out value="${copy.estDispo}" /></td>
				<td><c:out value="${copy.isbn}" /></td>
				<td><c:out value="${copy.numAbonne}" /></td>
				<td><a href="<c:url value="/copy/update?id=${copy.id}&estDispo=${copy.estDispo}&Isbn=${copy.isbn}&NumAbonne=${copy.numAbonne}"/>">Modifier</a></td>
				<td><a
					href="<c:url value="/copy/deleteCopy?id=${copy.id}&estDispo=${copy.estDispo}&Isbn=${copy.isbn}&NumAbonne=${copy.numAbonne}"/>">Supprimer</a></td>
			</tr>
		</c:forEach>
	</table>
	
	
</body>
</html>