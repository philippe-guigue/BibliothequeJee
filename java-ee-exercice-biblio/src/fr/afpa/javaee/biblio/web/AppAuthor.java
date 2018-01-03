package fr.afpa.javaee.biblio.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.javaee.biblio.dao.DaoAuthorSql;
import fr.afpa.javaee.biblio.dao.DaoBookSql;
import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;
import fr.afpa.javaee.biblio.service.IServiceAuthor;
import fr.afpa.javaee.biblio.service.IServiceBook;
import fr.afpa.javaee.biblio.service.ServiceAuthor;
import fr.afpa.javaee.biblio.service.ServiceBook;

public class AppAuthor extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IServiceAuthor serviceauthor;
	IServiceBook servicebook;

	public void init() throws ServletException {

		DaoAuthorSql daoAuthor = new DaoAuthorSql();
		serviceauthor = new ServiceAuthor(daoAuthor);

		DaoBookSql daoBook = new DaoBookSql();
		servicebook = new ServiceBook(daoBook);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// on récupère l'action à executer
		String action = request.getPathInfo();

		if (action == null || action.equals("/listauthor")) {
			listAuteur(request, response);
		} else if (action.equals("/add")) {
			doAdd(request, response);
		} else if (action.equals("/addAuthor")) {
			addAuthor(request, response);
		} else if (action.equals("/deleteAuthor")) {
			deleteAuthor(request, response);
		} else if (action.equals("/update")) {
			doUpdate(request, response);
		} else if (action.equals("/UpdateAuthor")) {
			UpdateAuthor(request, response);
		} else if (action.equals("/detail")) {
			doDetail(request, response);
		} else if (action.equals("/search")) {
			listAuthorSearch(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// on passe la main au GET
		doGet(request, response);
	}

	public void listAuteur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Author> authors = new ArrayList<Author>();

		// on récupère la liste des auteurs avec la couche service
		authors = serviceauthor.getAll();
		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.setAttribute("authors", authors);
		// on demande l'affichage de la vue add.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/author/listauthor.jsp").forward(request, response);
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/WEB-INF/views/author/add.jsp").forward(request, response);

	}

	public void addAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		for (id = 0; id <= 10; id++)
			;
		String nom = (String) request.getParameter("txtNom");
		// System.out.println(title);
		String prenom = (String) request.getParameter("txtPrenom");
		// System.out.println(subtitle);

		Author a = new Author(prenom, nom, id);

		request.setAttribute("id", id);
		request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);

		// on récupère la personne avec la couche service
		serviceauthor.addAuthor(a);

		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.getAttribute("auteur");
		// on demande l'affichage de la vue add.jsp
		listAuteur(request, response);

	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.valueOf(request.getParameter("id"));
		String nom = request.getParameter("name");
		String prenom = request.getParameter("surname");

		request.setAttribute("id", id);
		request.setAttribute("name", nom);
		request.setAttribute("surname", prenom);

		getServletContext().getRequestDispatcher("/WEB-INF/views/author/update.jsp").forward(request, response);
	}

	public void UpdateAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("txtId"));
		request.setAttribute("id", id);
		// System.out.println(id);
		String nom = request.getParameter("txtNom");
		String prenom = request.getParameter("txtPrenom");

		// on modifie auteur avec la couche service
		serviceauthor.update(nom, prenom, id);

		listAuteur(request, response);

	}

	public void deleteAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		String nom = request.getParameter("name");
		String prenom = request.getParameter("surname");

		request.setAttribute("id", id);
		request.setAttribute("name", nom);
		request.setAttribute("surname", prenom);

		// System.out.println(isbn);

		// on supprime le livre avec la couche service
		serviceauthor.DeleteAuthor(id);

		listAuteur(request, response);
	}

	public void doDetail(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");

		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("surname", surname);

		ArrayList<Book> books = serviceauthor.getBook(id);

		request.setAttribute("books", books);

		getServletContext().getRequestDispatcher("/WEB-INF/views/author/detail.jsp").forward(request, response);
		books.clear();

	}

	public void listAuthorSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Author> authors = new ArrayList<Author>();
		String auteur = (String) request.getParameter("recherche");
		// System.out.println(auteur);
		
		String[] souschaine = auteur.trim().split(" ");

		int id = Integer.valueOf(souschaine[0]).intValue();
		//System.out.println(id);
		
		Author author = serviceauthor.getOne(id);

		//System.out.println(author);


		authors.add(author);
		
		request.setAttribute("authors", authors);
		// on demande l'affichage de la vue list.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/author/listauthor.jsp").forward(request, response);
	}

}
