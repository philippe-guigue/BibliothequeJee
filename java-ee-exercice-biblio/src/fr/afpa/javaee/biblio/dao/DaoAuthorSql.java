package fr.afpa.javaee.biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;

public class DaoAuthorSql implements IDaoAuthor {

	private String url = "jdbc:mysql://localhost:3306/bibliotheque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String login = "root";
	private String password = "WINDSURF";
	private Connection connection = null;
	private Statement statement = null;
	// Use only one result set object
	private ResultSet result = null;
	
	//These should be local variables instead
	private Author author;
	private ArrayList<Author> auteurs = new ArrayList<Author>();
	private ArrayList<Book> books = new ArrayList<Book>();

	public DaoAuthorSql() {
		init();
	}

	private void init() {
		try {
			// chargement du driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void DeleteAuthor(int id) {

		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteDeleteBook = "DELETE FROM auteur  WHERE id_auteur = '" + id + "'";
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteDeleteBook);
			ps.executeUpdate(requeteDeleteBook);
			auteurs.remove(author);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public ArrayList<Author> getAll() {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetAll = "SELECT personne.id_personne,auteur.id_Auteur,\r\n"
					+ "personne.Nom,personne.Prenom \r\n" + "FROM auteur,\r\n" + "personne "
					+ "WHERE personne.id_personne = auteur.id_personne";

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetAll);
			ResultSet rsRequeteGetAll = ps.executeQuery();
			auteurs.clear();
			while (rsRequeteGetAll.next()) {

				author = new Author(rsRequeteGetAll.getString("personne.Nom"),
						rsRequeteGetAll.getString("personne.Prenom"), rsRequeteGetAll.getInt("auteur.id_auteur"));
				auteurs.add(author);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auteurs;

	}

	@Override
	public ArrayList<Book>getBook(int id) {
		author = null;
		
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetOne = "SELECT auteur.id_Auteur,livre.isbn,livre.titreLivre,personne.nom,personne.prenom\r\n"
					+ "FROM auteur," + "livre," + "personne\r\n"
					+ "WHERE auteur.id_Auteur = livre.id_Auteur\r\n" 
					+ "AND auteur.id_personne = personne.id_personne\r\n"
					+ "AND auteur.id_Auteur = " + id + "" ;

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetOne);
			ResultSet rsRequeteGetBook = ps.executeQuery();

			while (rsRequeteGetBook.next()) {

				
				int isbn = rsRequeteGetBook.getInt("isbn");
				String title= rsRequeteGetBook.getString("titreLivre");
				

				 Book book = new Book(rsRequeteGetBook.getInt("livre.isbn"),
						rsRequeteGetBook.getString("livre.titreLivre"));
				 books.add(book);
				author = new Author(isbn,title);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(books);
		return books;

	}

	@Override
	public Author modifyOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getIdPersonne() {
		int id = 0;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();

			String requeteGetId = "select  max(id_personne) as nbre  from personne";

			System.out.println(requeteGetId);

			result = statement.executeQuery(requeteGetId);
			while (result.next()) {
				String idTemp = result.getString("nbre");
				id = Integer.valueOf(idTemp).intValue();
			}
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getIdPersonne();

	}

	public int getIdAuteur() {
		int id = 0;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();

			String requeteGetIdAuteur = "select  max(id_auteur) as nbre  from auteur";

			// System.out.println(requeteGetIdAuteur);

			result = statement.executeQuery(requeteGetIdAuteur);
			while (result.next()) {
				String idTemp = result.getString("nbre");
				id = Integer.valueOf(idTemp).intValue();
			}
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getIdAuteur();

	}

	@Override
	public void addAuthor(Author a) {
		int id = getIdPersonne() + 1;
		int idA = getIdAuteur() + 1;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// ajout d'un auteur dans la bibliotheque
			String requeteAddPersonne = "INSERT INTO personne " + "VALUES ('" + id + "', '" + a.getNom() + "','"
					+ a.getPrenom() + "');";
			System.out.println(requeteAddPersonne);
			String requeteAddAuthor = "INSERT INTO auteur " + "VALUES ('" + idA + "', '" + id + "');";
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteAddPersonne);
			ps = connection.prepareStatement(requeteAddAuthor);

			ps.executeUpdate(requeteAddPersonne);
			ps.executeUpdate(requeteAddAuthor);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String prenom, String nom, int id) {

		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// modif un auteur dans la bibliotheque
			String requeteUpdateP = "UPDATE personne SET Nom = '" + nom + "' , prenom = '" + prenom + "'"
					+ " WHERE id_personne = (select id_Personne FROM " + " auteur WHERE id_Auteur = " + id + ");";
			System.out.println(requeteUpdateP);

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteUpdateP);

			// ResultSet rsRequeteAddBook = ps.executeQuery();
			ps.executeUpdate(requeteUpdateP);

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public Author getOne(int id) {
		author = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetOne = "SELECT auteur.id_Auteur,\r\n"
					+ "personne.Nom,personne.Prenom \r\n"
					+ "FROM Auteur,\r\n" + ".personne\r\n"
					+ "WHERE auteur.id_personne = personne.id_personne\r\n"
					+ " AND id_auteur = " + id + "";

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetOne);
			ResultSet rsRequeteGetOne = ps.executeQuery();
			auteurs.clear();
			if (rsRequeteGetOne.next()) {


				author = new Author(rsRequeteGetOne.getString("personne.Nom"), rsRequeteGetOne.getString("personne.Prenom"),
						rsRequeteGetOne.getInt("auteur.id_auteur"));
				
				auteurs.add(author);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
System.out.println(author);
		return author;

	}
}