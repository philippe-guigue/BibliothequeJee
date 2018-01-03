<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/book.css"/>
<title>MVC-Biblio</title>
</head>
<body>
	<h1>Liste des catalogues</h1>
	
	
	<a href="<c:url value="/"/>"><input type="button" value="Retour page d'accueil"></a>
	
	<a href="<c:url value="/author/listauthor"/>"><input type="button" value="Menu auteur"></a>
	
	<a href="<c:url value="/copy/listcopy"/>"><input type="button" value="menu exemplaire"></a>

	<a href="<c:url value="/subscriber/listsub"/>"><input type="button" value="menu abonné"></a>
	
	<a href="<c:url value="/book/listbook"/>"><input type="button" value="menu livre"></a>
	<hr>
	<div id="searchbar">
		<form action="search" class="formulaire" >
			<input class="champ" type="search" name="recherche" list="catalog_list" autocomplete="off"
				placeholder="Saisir nom/Id Catalogue" required><input type="reset" value="Effacer" id="reset" name="reset" /> 
				<datalist id="catalog_list">
						<c:forEach var="catalog" items="${catalogs}">
						<option value="${catalog.id_catalogue} ${catalog.description} ">${catalog.id_catalogue} - ${catalog.description}</option>
						</c:forEach>
						</datalist><input class="bouton" type="submit" value=""/>
				
		</form>
		
	</div>


	<hr>
	
	<a href="<c:url value="/catalog/add"/>"><input type="button" value="Ajout"></a>

	
	<a href="<c:url value="/catalog/listcatalog"/>"><input type="button" value="Retour"></a>
	
	<table id= "list" border="1">
		<tr>
			<th>Id</th>
			<th>Nom Catalogue</th>
			
			
		</tr>

		<c:forEach var="catalog" items="${catalogs}">
			<tr>
				<td><c:out value="${catalog.id_catalogue}" /></td>
				<td><c:out value="${catalog.description}" /></td>
				
				<td><a href="<c:url value="/catalog/update?id=${catalog.id_catalogue}&title=${catalog.description}"/>">Modifier</a></td>
				<td><a
					href="<c:url value="/catalog/deleteCatalog?id=${catalog.id_catalogue}&title=${catalog.description}"/>">Supprimer</a></td>
			<td><a
					href="<c:url value="/catalog/detail?id=${catalog.id_catalogue}&title=${catalog.description}"/>">Details</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	
	
	
</body>
</html>