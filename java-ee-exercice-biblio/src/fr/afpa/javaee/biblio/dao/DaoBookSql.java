package fr.afpa.javaee.biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;
import fr.afpa.javaee.biblio.model.Catalog;

public class DaoBookSql implements IDaoBook {
	private String url = "jdbc:mysql://localhost:3306/bibliotheque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String login = "root";
	private String password = "WINDSURF";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet requeteGetOne = null;
	private ResultSet requeteGetAll = null;
	private ResultSet requeteAddBook = null;
	private Book book = null;
	private Author auteur;
	private int isbn;
	private Catalog catalog;
	private ArrayList<Book> books = new ArrayList<Book>();
	private ArrayList<Book> b = new ArrayList<Book>();

	public DaoBookSql() {
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

	/*
	 * @Override public Book DeleteBook(int isbn) {
	 * 
	 * return null; }
	 */
	@Override
	public Book getOne(int isbn) {
		book = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetOne = "SELECT titreLivre,souTitreLivre,ISBN,personne.id_personne,auteur.id_Auteur,\r\n"
					+ "personne.Nom,personne.Prenom,catalogue.id_catalogue,catalogue.description \r\n"
					+ "FROM .livre,\r\n" + ".catalogue,.Auteur,\r\n" + ".personne\r\n"
					+ "WHERE .livre.id_catalogue = catalogue.id_catalogue\r\n"
					+ "AND .livre.id_Auteur = auteur.id_Auteur\r\n" + "AND .auteur.id_personne = personne.id_personne"
					+ " AND ISBN = " + isbn + "";

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetOne);
			ResultSet rsRequeteGetOne = ps.executeQuery();
			books.clear();
			if(rsRequeteGetOne.next()) {

			

				book = new Book(rsRequeteGetOne.getInt("livre.isbn"), rsRequeteGetOne.getString("livre.titreLivre"),rsRequeteGetOne.getString("livre.souTitreLivre"),
						rsRequeteGetOne.getString("personne.Nom"), rsRequeteGetOne.getString("personne.Prenom"),
						rsRequeteGetOne.getString("catalogue.description"));
				books.add(book);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
   // System.out.println(book);
		return book;

	}

	@Override
	public void addBook(Book b) {
		book = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// ajout du livre dans la bibliotheque
			String requeteAddBook = "INSERT INTO livre" + "(ISBN,titreLivre,souTitreLivre,id_catalogue,id_Auteur) "
					+ "VALUES (" + b.getIsbn() + ",'" + b.getTitle() + "','" + b.getSubtitle() + "'," + b.getCatalogue()
					+ "," + b.getAuthor() + ");";
			System.out.println(requeteAddBook);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteAddBook);
			// ResultSet rsRequeteAddBook = ps.executeQuery();
			ps.executeUpdate(requeteAddBook);

			book = new Book(b.getIsbn(), b.getTitle(), b.getAuthor(), b.getSubtitle(), b.getCatalogue());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String title, int author, String subtitle, int isbn, int catalog) {
		book = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// modif un livre dans la bibliotheque
			String requeteUpdate = "UPDATE livre " + "SET ISBN = " + isbn + ", titreLivre = '" + title
					+ "' , souTitreLivre = '" + subtitle + "' , id_catalogue = " + catalog + " , id_Auteur = " + author
					+ "  " + " WHERE ISBN = '" + isbn + "';";
			// + " AND id_catalogue = " + catalog + ""
			// + " AND id_Auteur = " + author +";";

			System.out.println(requeteUpdate);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteUpdate);
			// ResultSet rsRequeteAddBook = ps.executeQuery();
			ps.executeUpdate(requeteUpdate);

			book = new Book(isbn, title, catalog, subtitle, author);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteBook(int isbn) {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteDeleteBook = "DELETE FROM livre  WHERE ISBN = " + isbn + "";
			System.out.println(requeteDeleteBook);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteDeleteBook);
			ps.executeUpdate(requeteDeleteBook);
			books.remove(book);
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public ArrayList<Book> getAll() {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetAll = "SELECT titreLivre,souTitreLivre,ISBN,\r\n"
					+ "personne.Nom,personne.Prenom,catalogue.description \r\n"
					+ "FROM .livre,\r\n" + ".catalogue,.Auteur,\r\n" + ".personne\r\n"
					+ "WHERE .livre.id_catalogue = catalogue.id_catalogue\r\n"
					+ "AND .livre.id_Auteur = auteur.id_Auteur\r\n" + "AND .auteur.id_personne = personne.id_personne";
				
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetAll);
			ResultSet rsRequeteGetAll = ps.executeQuery();
			books.clear();
			while (rsRequeteGetAll.next()) {

				book = new Book(rsRequeteGetAll.getInt("livre.isbn"), rsRequeteGetAll.getString("livre.titreLivre"),rsRequeteGetAll.getString("livre.souTitreLivre"),
						rsRequeteGetAll.getString("personne.nom"), rsRequeteGetAll.getString("personne.prenom"),
						rsRequeteGetAll.getString("catalogue.description"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(book);
		return books;

	}

	@Override
	public Book modifyOne(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
