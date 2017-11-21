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
import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;
import fr.afpa.javaee.biblio.model.Catalog;
import fr.afpa.javaee.biblio.service.IServiceAuthor;
import fr.afpa.javaee.biblio.service.IServiceBook;
import fr.afpa.javaee.biblio.service.IServiceCatalog;
import fr.afpa.javaee.biblio.service.ServiceAuthor;
import fr.afpa.javaee.biblio.service.ServiceBook;
import fr.afpa.javaee.biblio.service.ServiceCatalog;

/**
 * @author Philippe Guigue
 * @version 1
 *
 */
public class AppLibrary extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IServiceBook servicebook;
	IServiceCatalog servicecatalog;
	IServiceAuthor serviceauthor;

	public void init() throws ServletException {
		DaoBookSql daoBook = new DaoBookSql();
		servicebook = new ServiceBook(daoBook);

		DaoCatalogSql daoCatalog = new DaoCatalogSql();
		servicecatalog = new ServiceCatalog(daoCatalog);

		DaoAuthorSql daoAuthor = new DaoAuthorSql();
		serviceauthor = new ServiceAuthor(daoAuthor);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// on récupère l'action à executer
		String action = request.getPathInfo();

		if (action == null || action.equals("/listbook")) {
			listBook(request, response);
		} else if (action.equals("/add")) {
			doAdd(request, response);
		} else if (action.equals("/addBook")) {
			addBook(request, response);
		} else if (action.equals("/deleteBook")) {
			deleteBook(request, response);
		} else if (action.equals("/update")) {
			doUpdate(request, response);
		} else if (action.equals("/UpdateBook")) {
			UpdateBook(request, response);
		} else if (action.equals("/search")) {
			listBookSearch(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// on passe la main au GET
		doGet(request, response);
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
		getServletContext().getRequestDispatcher("/WEB-INF/views/book/listbook.jsp").forward(request, response);
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		listCatalog(request, response);
		listAuteur(request, response);
		getServletContext().getRequestDispatcher("/WEB-INF/views/book/add.jsp").forward(request, response);

	}

	public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int isbn = Integer.valueOf(request.getParameter("txtIsbn"));
		String title = (String) request.getParameter("txtTitle");
		// System.out.println(title);
		String subtitle = (String) request.getParameter("txtSubTitle");
		// System.out.println(subtitle);

		String catalogue = (String) request.getParameter("listC");
		// System.out.println(catalogue);
		Catalog ca;
		String[] sousChaineC = catalogue.split(" ");
		int idCatalogue = Integer.valueOf(sousChaineC[0]).intValue();
		ca = new Catalog(idCatalogue);

		String author = (String) request.getParameter("listA");
		System.out.println(author);
		Author au;
		String[] sousChaineA = author.split(" ");
		int idAuteur = Integer.valueOf(sousChaineA[0]).intValue();
		au = new Author(idAuteur);

		Book b = new Book(isbn, title, idCatalogue, subtitle, idAuteur);

		request.setAttribute("isbn", isbn);
		request.setAttribute("titre", title);
		request.setAttribute("sousTitre", subtitle);
		request.setAttribute("catalog", idCatalogue);
		request.setAttribute("Auteur", idAuteur);

		// on récupère la personne avec la couche service
		servicebook.addBook(b);

		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.getAttribute("livre");
		// on demande l'affichage de la vue add.jsp
		listBook(request, response);

	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int isbn = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String catalog = request.getParameter("catalog");
		String authorName = request.getParameter("authorName");
		String authorSurname = request.getParameter("authorSurname");
		request.setAttribute("isbn", isbn);
		request.setAttribute("title", title);
		request.setAttribute("subtitle", subtitle);
		request.setAttribute("catalog", catalog);
		request.setAttribute("authorName", authorName);
		request.setAttribute("authorSurname", authorSurname);
		// System.out.println(author);

		listCatalog(request, response);
		listAuteur(request, response);
		getServletContext().getRequestDispatcher("/WEB-INF/views/book/update.jsp").forward(request, response);
	}

	public void UpdateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int isbn = Integer.valueOf(request.getParameter("txtIsbn"));
		request.setAttribute("isbn", isbn);
		System.out.println(isbn);
		String title = request.getParameter("txtTitle");
		String subtitle = request.getParameter("txtSubTitle");
		String catalog = (String) request.getParameter("listC");
		// System.out.println(catalogue);
		Catalog ca;
		String[] sousChaineC = catalog.split(" ");
		int idCatalogue = Integer.valueOf(sousChaineC[0]).intValue();
		ca = new Catalog(idCatalogue);
		String author = (String) request.getParameter("listA");
		System.out.println(author);
		Author au;
		String[] sousChaineA = author.split(" ");
		int idAuteur = Integer.valueOf(sousChaineA[0]).intValue();
		au = new Author(idAuteur);

		// on modifie le livre avec la couche service
		servicebook.update(title, idAuteur, subtitle, isbn, idCatalogue);

		listBook(request, response);

	}

	public void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int isbn = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		int catalog = Integer.valueOf(request.getParameter("catalog"));
		int author = Integer.valueOf(request.getParameter("author"));

		request.setAttribute("id", isbn);
		request.setAttribute("title", title);
		request.setAttribute("subtitle", subtitle);
		request.setAttribute("catalog", catalog);
		request.setAttribute("author", author);
		// System.out.println(isbn);

		// on supprime le livre avec la couche service
		servicebook.DeleteBook(isbn);

		listBook(request, response);
	}

	@SuppressWarnings("unused")
	public void listCatalog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Catalog> catalogs = new ArrayList<Catalog>();

		// on récupère la liste de personnes avec la couche service
		catalogs = servicecatalog.getAll();
		// System.out.println(catalogs);
		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.setAttribute("catalogs", catalogs);
		// on demande l'affichage de la vue add.jsp
		if (catalogs == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/book/add.jsp").forward(request, response);
		
		}
	}

	public void listAuteur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Author> authors = new ArrayList<Author>();

		// on récupère la liste de personnes avec la couche service
		authors = serviceauthor.getAll();
		// System.out.println(authors);
		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.setAttribute("authors", authors);
		// on demande l'affichage de la vue add.jsp
		if (authors == null) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/book/add.jsp").forward(request, response);
		}
		// getServletContext().getRequestDispatcher("/WEB-INF/views/add.jsp").forward(request,
		// response);
	}

	public void listBookSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Book> books = new ArrayList<Book>();
		String livre = (String) request.getParameter("recherche");
		// System.out.println(livre);

		String[] souschaine = livre.trim().split(" ");

		int isbn = Integer.valueOf(souschaine[0]).intValue();
		System.out.println(isbn);

		Book book = servicebook.getOne(isbn);
		System.out.println(book);
		books.add(book);
		request.setAttribute("books", books);
		// on demande l'affichage de la vue list.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/book/listbook.jsp").forward(request, response);
	}

}