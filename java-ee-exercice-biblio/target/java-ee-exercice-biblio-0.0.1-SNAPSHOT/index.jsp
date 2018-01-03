<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/index.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WELCOME TO THE LIBRARY</title>
</head>
<body>

				<h1> WELCOME TO THE LIBRARY <br> OF PHILOU</h1>
	
	<br>

	<a href="<c:url value="/author/listauthor"/>">AUTEURS</a>

	<br>
	<a href="<c:url value="/book/listbook"/>">LIVRES</a>

	<br>
	<a href="<c:url value="/copy/listcopy"/>">EXEMPLAIRES</a>

	<br>
	<a href="<c:url value="/subscriber/listsub"/>">ABONNES</a>

	<br>
	<a href="<c:url value="/catalog/listcatalog"/>">CATALOGUES</a>

  <script src="jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="recherche.js"></script>
</body>
</html>