package fr.afpa.javaee.biblio.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.javaee.biblio.dao.DaoSubSql;
import fr.afpa.javaee.biblio.model.Copy;
import fr.afpa.javaee.biblio.model.Subscriber;
import fr.afpa.javaee.biblio.service.IServiceSub;
import fr.afpa.javaee.biblio.service.ServiceSub;

public class AppSub extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IServiceSub servicesub;

	public void init() throws ServletException {

		DaoSubSql daoSub = new DaoSubSql();
		servicesub = new ServiceSub(daoSub);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// on récupère l'action à executer
		String action = request.getPathInfo();

		if (action == null || action.equals("/listsub")) {
			listSub(request, response);
		} else if (action.equals("/add")) {
			doAdd(request, response);
		} else if (action.equals("/addSub")) {
			addSub(request, response);
		} else if (action.equals("/deleteSub")) {
			deleteSub(request, response);
		} else if (action.equals("/update")) {
			doUpdate(request, response);
		} else if (action.equals("/UpdateSub")) {
			UpdateSub(request, response);
		} else if (action.equals("/detail")) {
			doDetail(request, response);
		}else if (action.equals("/search")) {
			listSubSearch(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// on passe la main au GET
		doGet(request, response);
	}

	public void listSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Subscriber> subs = new ArrayList<Subscriber>();

		// on récupère la liste des auteurs avec la couche service
		subs = servicesub.getAll();
		// System.out.println(authors);
		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.setAttribute("subs", subs);
		// on demande l'affichage de la vue add.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/subscriber/listsub.jsp").forward(request, response);
	}
	// getServletContext().getRequestDispatcher("/WEB-INF/views/add.jsp").forward(request,
	// response);

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/WEB-INF/views/subscriber/add.jsp").forward(request, response);

	}

	public void addSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		for (id = 0; id <= 10; id++)
			;
		String nom = (String) request.getParameter("txtNom");
		// System.out.println(title);
		String prenom = (String) request.getParameter("txtPrenom");
		// System.out.println(subtitle);

		Subscriber s = new Subscriber(prenom, nom, id);

		request.setAttribute("id", id);
		request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);

		// on récupère la personne avec la couche service
		servicesub.addSub(s);

		// on insère le résultat dans la requête, de façon à pouvoir l'exploiter dans la
		// JSP
		request.getAttribute("auteur");
		// on demande l'affichage de la vue add.jsp
		listSub(request, response);

	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.valueOf(request.getParameter("id"));
		String nom = request.getParameter("name");
		String prenom = request.getParameter("surname");

		request.setAttribute("id", id);
		request.setAttribute("name", nom);
		request.setAttribute("surname", prenom);

		getServletContext().getRequestDispatcher("/WEB-INF/views/subscriber/update.jsp").forward(request, response);
	}

	public void UpdateSub(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("txtId"));
		request.setAttribute("id", id);
		System.out.println(id);
		String nom = request.getParameter("txtNom");
		String prenom = request.getParameter("txtPrenom");

		// on modifie auteur avec la couche service
		servicesub.update(nom, prenom, id);

		listSub(request, response);

	}

	public void deleteSub(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		String nom = request.getParameter("name");
		String prenom = request.getParameter("surname");

		request.setAttribute("id", id);
		request.setAttribute("name", nom);
		request.setAttribute("surname", prenom);

		// System.out.println(isbn);

		// on supprime le livre avec la couche service
		servicesub.DeleteSub(id);

		listSub(request, response);
	}

	public void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		

		
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("surname", surname);
		
		ArrayList<Copy> copies = servicesub.getBook(id);
	
		request.setAttribute("books", copies);
	
		getServletContext().getRequestDispatcher("/WEB-INF/views/subscriber/detail.jsp").forward(request, response);
		copies.clear();

	}
	
	public void listSubSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
		String abonne = (String) request.getParameter("recherche");
	
		String[] souschaine = abonne.trim().split(" ");

		int id = Integer.valueOf(souschaine[0]).intValue();

		Subscriber sub = servicesub.getOne(id);
		
		System.out.println(sub);
	
		subs.add(sub);

		request.setAttribute("subs", subs);
		// on demande l'affichage de la vue list.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/views/subscriber/listsub.jsp").forward(request, response);
	}
}
