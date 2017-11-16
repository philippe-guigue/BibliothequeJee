package fr.afpa.javaee.biblio.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.javaee.biblio.dao.DaoAuthorSql;
import fr.afpa.javaee.biblio.dao.DaoBookSql;
import fr.afpa.javaee.biblio.dao.DaoCopySql;
import fr.afpa.javaee.biblio.dao.DaoSubSql;
import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;
import fr.afpa.javaee.biblio.model.Catalog;
import fr.afpa.javaee.biblio.model.Copy;
import fr.afpa.javaee.biblio.model.Subscriber;
import fr.afpa.javaee.biblio.service.IServiceAuthor;
import fr.afpa.javaee.biblio.service.IServiceBook;
import fr.afpa.javaee.biblio.service.IServiceCopy;
import fr.afpa.javaee.biblio.service.IServiceSub;
import fr.afpa.javaee.biblio.service.ServiceAuthor;
import fr.afpa.javaee.biblio.service.ServiceBook;
import fr.afpa.javaee.biblio.service.ServiceCopy;
import fr.afpa.javaee.biblio.service.ServiceSub;

public class AppCopy extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IServiceCopy servicecopy;
	IServiceBook servicebook;
	IServiceSub servicesub;
	IServiceAuthor serviceauthor;

	public void init() throws ServletException {
		DaoBookSql daoBook = new DaoBookSql();
		servicebook = new ServiceBook(daoBook);

		DaoSubSql daoSub = new DaoSubSql();
		servicesub = new ServiceSub(daoSub);

		DaoCopySql daoCopy = new DaoCopySql();
		servicecopy = new ServiceCopy(daoCopy);

		DaoAuthorSql daoAuthor = new DaoAuthorSql();
		serviceauthor = new ServiceAuthor(daoAuthor);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// on récupère l'action à executer
		String action = request.getPathInfo();

		if (action == null || action.equals("/listcopy")) {
			listCopy(request, response);
		} else if (action.equals("/add")) {
			doAdd(request, response);
		} else if (action.equals("/addCopy")) {
			addCopy(request, response);
		} else if (action.equals("/deleteCopy")) {
			deleteCopy(request, response);
		} else if (action.equals("/update")) {
			doUpdate(request, response);
		} else if (action.equals("/UpdateCopy")) {
			UpdateCopy(request, response);
		}else if (action.equals("/search")) {
			listCopySearch(request, response);
	}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// on passe la main au GET
		doGet(request, response);
	}

	public void listCopy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Copy> copies = new ArrayList<Copy>();

		// on récupère la liste des exemplaires avec la couche service
		copies = servicecopy.getAll();

		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.setAttribute("copies", copies);
		// on demande l'affichage de la vue list.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/copy/listcopy.jsp").forward(request, response);
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		listBook(request, response);
		listSub(request, response);
		getServletContext().getRequestDispatcher("/WEB-INF/views/copy/add.jsp").forward(request, response);

	}

	public void addCopy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		for (id = 0; id <= 10; id++)
			;
		String dispo = (String) request.getParameter("dispo");
		String[] sousChained = dispo.split(" ");
		int disponibilite = Integer.valueOf(sousChained[0]).intValue();
		// System.out.println(dispo);

		String livre = (String) request.getParameter("listB");
		// System.out.println(livre);
		Book bo;
		String[] sousChaineB = livre.split(" ");
		int isbn = Integer.valueOf(sousChaineB[0]).intValue();
		bo = new Book(isbn);

		String abonne = (String) request.getParameter("listA");
		// System.out.println(abonne);
		Subscriber su;
		String[] sousChaineA = abonne.split(" ");
		int idSub = Integer.valueOf(sousChaineA[0]).intValue();
		su = new Subscriber(idSub);

		Copy c = new Copy(id, isbn, disponibilite, idSub);

		request.setAttribute("id", id);
		request.setAttribute("isbn", isbn);
		request.setAttribute("estDispo", disponibilite);
		request.setAttribute("abonne", idSub);

		// on récupère la personne avec la couche service
		servicecopy.addCopy(c);

		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.getAttribute("exemplaire");
		// on demande l'affichage de la vue add.jsp
		listCopy(request, response);

	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.valueOf(request.getParameter("id"));
		String dispo = request.getParameter("estDispo");
		int isbn = Integer.valueOf(request.getParameter("Isbn"));
		int abonne = Integer.valueOf(request.getParameter("NumAbonne"));
		
		request.setAttribute("id", id);
		request.setAttribute("estDispo", dispo);
		request.setAttribute("Isbn", isbn);
		request.setAttribute("NumAbonne", abonne);
		//System.out.println(abonne);

		listBook(request, response);
		listSub(request, response);
		getServletContext().getRequestDispatcher("/WEB-INF/views/copy/update.jsp").forward(request, response);

	}

	public void UpdateCopy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("txtId"));
		request.setAttribute("id", id);
		System.out.println(id);
		int dispo = Integer.valueOf(request.getParameter("txtDispo"));
		String book = (String) request.getParameter("listB");
		// System.out.println(catalogue);
		Book bo;
		String[] sousChaineB = book.split(" ");
		int idBook = Integer.valueOf(sousChaineB[0]).intValue();
		bo = new Book(idBook);
		String sub = (String) request.getParameter("listS");
		System.out.println(sub);
		Subscriber su;
		String[] sousChaineS = sub.split(" ");
		int idSub = Integer.valueOf(sousChaineS[0]).intValue();
		su = new Subscriber(idSub);

		// on modifie le livre avec la couche service
		servicecopy.update(id, dispo, idBook, idSub);

		listCopy(request, response);

	}

	public void deleteCopy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int isbn = Integer.valueOf(request.getParameter("Isbn"));
		int id = Integer.valueOf(request.getParameter("id"));
		String estDsipo = request.getParameter("estDispo");
		int sub = Integer.valueOf(request.getParameter("NumAbonne"));

		request.setAttribute("id", id);
		request.setAttribute("estDispo", estDsipo);
		request.setAttribute("Isbn", isbn);
		request.setAttribute("NumAbonne", sub);

		// System.out.println(isbn);

		// on supprime le livre avec la couche service
		servicecopy.DeleteCopy(id);

		listCopy(request, response);
	}

	public void listBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Book> books = new ArrayList<Book>();

		// on récupère la liste de personnes avec la couche service
		books = servicebook.getAll();
		// System.out.println(books);
		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.setAttribute("books", books);
		// on demande l'affichage de la vue list.jsp
		if (books == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/book/listbook.jsp").forward(request, response);
		}

	}

	public void listSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Subscriber> subs = new ArrayList<Subscriber>();

		// on récupère la liste des auteurs avec la couche service
		subs = servicesub.getAll();
		// System.out.println(subs);
		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.setAttribute("subs", subs);
		// on demande l'affichage de la vue add.jsp
		if (subs == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/subscriber/listsub.jsp").forward(request,
					response);
		}
		// getServletContext().getRequestDispatcher("/WEB-INF/views/add.jsp").forward(request,
		// response);
	}

	public void listCopySearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Copy> copies = new ArrayList<Copy>();
		String copy = (String) request.getParameter("recherche");

		String[] souschaine=copy.trim().split(" ");
	
		int id = Integer.valueOf(souschaine[0]).intValue();
	
		Copy copie =servicecopy.getOne(id);
	
		
		System.out.println(copie);
	
		
		copies.add(copie);
		
		
		request.setAttribute("copies",copies);
		// on demande l'affichage de la vue list.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/copy/listcopy.jsp").forward(request, response);
	}
}
