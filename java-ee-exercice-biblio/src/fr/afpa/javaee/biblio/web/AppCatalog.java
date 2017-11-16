package fr.afpa.javaee.biblio.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.javaee.biblio.dao.DaoAuthorSql;
import fr.afpa.javaee.biblio.dao.DaoBookSql;
import fr.afpa.javaee.biblio.dao.DaoCatalogSql;
import fr.afpa.javaee.biblio.model.Book;
import fr.afpa.javaee.biblio.model.Catalog;
import fr.afpa.javaee.biblio.service.IServiceAuthor;
import fr.afpa.javaee.biblio.service.IServiceBook;
import fr.afpa.javaee.biblio.service.IServiceCatalog;
import fr.afpa.javaee.biblio.service.ServiceAuthor;
import fr.afpa.javaee.biblio.service.ServiceBook;
import fr.afpa.javaee.biblio.service.ServiceCatalog;

public class AppCatalog extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IServiceBook servicebook;
	IServiceCatalog servicecatalog;


	public void init() throws ServletException {
		DaoBookSql daoBook = new DaoBookSql();
		servicebook = new ServiceBook(daoBook);

		DaoCatalogSql daoCatalog = new DaoCatalogSql();
		servicecatalog = new ServiceCatalog(daoCatalog);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// on récupère l'action à executer
		String action = request.getPathInfo();

		if (action == null || action.equals("/listcatalog")) {
			listCatalog(request, response);
		} else if (action.equals("/add")) {
			doAdd(request, response);
		} else if (action.equals("/addCatalog")) {
			addCatalog(request, response);
		} else if (action.equals("/deletecatalog")) {
			deleteCatalog(request, response);
		} else if (action.equals("/update")) {
			doUpdate(request, response);
		} else if (action.equals("/UpdateCatalog")) {
			UpdateCatalog(request, response);
		} else if (action.equals("/detail")) {
			doDetail(request, response);
		} else if (action.equals("/search")) {
			listCatalogSearch(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// on passe la main au GET
		doGet(request, response);
	}

	public void listCatalog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Catalog> catalogs = new ArrayList<Catalog>();

		// on récupère la liste de catalogue avec la couche service
		catalogs = servicecatalog.getAll();
		// System.out.println(books);
		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.setAttribute("catalogs", catalogs);
		// on demande l'affichage de la vue list.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/catalog/listcatalog.jsp").forward(request, response);
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/WEB-INF/views/catalog/add.jsp").forward(request, response);

	}

	public void addCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		for (id = 0; id <= 10; id++);
		String title = (String) request.getParameter("txtTitle");
		// System.out.println(title);
		
		Catalog c = new Catalog(id, title);

		request.setAttribute("id", id);
		request.setAttribute("titre", title);
	
		// on récupère la personne avec la couche service
		servicecatalog.addCatalog(c);

		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.getAttribute("catalog");
		// on demande l'affichage de la vue add.jsp
		listCatalog(request, response);

	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		
		request.setAttribute("id", id);
		request.setAttribute("title", title);
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/catalog/update.jsp").forward(request, response);
	}

	public void UpdateCatalog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("txtId"));
		request.setAttribute("id", id);
		//System.out.println(id);
		String title = request.getParameter("txtTitle");
		

		// on modifie le livre avec la couche service
		servicecatalog.update(title,id);

		listCatalog(request, response);

	}

	public void deleteCatalog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		

		request.setAttribute("id", id);
		request.setAttribute("title", title);
		

		// on supprime le livre avec la couche service
		servicecatalog.DeleteCatalog(id);

		listCatalog(request, response);
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
	public void listCatalogSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
		String catalogue = (String) request.getParameter("recherche");
		// System.out.println(livre);

		String[] souschaine = catalogue.trim().split(" ");

		int id = Integer.valueOf(souschaine[0]).intValue();
		//System.out.println(id);

		Catalog catalog = servicecatalog.getOne(id);
		//System.out.println(catalog);

		catalogs.add(catalog);
		request.setAttribute("catalogs", catalogs);
		// on demande l'affichage de la vue list.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/catalog/listcatalog.jsp").forward(request, response);
	}

	public void doDetail(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("title");
		

		request.setAttribute("id", id);
		request.setAttribute("title", name);
	

		ArrayList<Book> books = servicecatalog.getBook(id);

		request.setAttribute("books", books);

		getServletContext().getRequestDispatcher("/WEB-INF/views/catalog/detail.jsp").forward(request, response);
		books.clear();

	}


}
