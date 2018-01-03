<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/CSS/subscriber.css"/>
<title>MVC-subscriber</title>
</head>
<body>
	<h1>Liste des abonnés</h1>
	

	<a href="<c:url value="/"/>"><input type="button" value="Retour page d'acceuil"></a>
	
	<a href="<c:url value="/book/listbook"/>"><input type="button" value="menu livre"></a>

	<a href="<c:url value="/copy/listcopy"/>"><input type="button" value="menu exemplaire"></a>

	<a href="<c:url value="/author/listauthor"/>"><input type="button" value="menu auteur"></a>
	
	<a href="<c:url value="/catalog/listcatalog"/>"><input type="button" value="menu catalogue"></a>
	<hr>
	<div id="searchbar">
		<form action="search" class="formulaire" >
			<input class="champ" type="search" name="recherche" list="sub_list" autocomplete="off"
				placeholder="Saisir nom/prenom/id abonne" required><input type="reset" value="Effacer" id="reset" name="reset" />  
				<datalist id="sub_list">
						<c:forEach var="abonne" items="${subs}">
						<option value="${abonne.id} ${abonne.nom} ${abonne.prenom}">${abonne.id} - ${abonne.nom} ${abonne.prenom}</option>
						</c:forEach>
						</datalist><input class="bouton" type="submit" value=""/>
				
		</form>
		
	</div>

	<hr>
	<a href="<c:url value="/subscriber/add"/>"><input type="button"
		value="Ajout"></a>
	<a href="<c:url value="/subscriber/listsub"/>"><input type="button" value="Retour"></a>
	<table id="listAB" border="1">
		<tr>
			<th>Id</th>
			<th>Nom Abonné</th>
			<th>Prenom Abonné</th>
			
		</tr>

		<c:forEach var="abonne" items="${subs}">
			<tr>
				<td><c:out value="${abonne.id}" /></td>
				<td><c:out value="${abonne.nom}" /></td>
				<td><c:out value="${abonne.prenom}" /></td>
				<!--  <td><a href="<c:url value="/details?id=${livre.isbn}"/>">Details</a></td>-->
				<td><a
					href="<c:url value="/subscriber/update?id=${abonne.id}&name=${abonne.nom}&surname=${abonne.prenom}"/>">Modifier</a></td>
				<td><a
					href="<c:url value="/subscriber/deleteSub?id=${abonne.id}&name=${abonne.nom}&surname=${abonne.prenom}"/>">Supprimer</a></td>
				<td><a
					href="<c:url value="/subscriber/detail?id=${abonne.id}&name=${abonne.nom}&surname=${abonne.prenom}"/>">Details</a></td>
			</tr>
		</c:forEach>
	</table>

	
</body>
</html>