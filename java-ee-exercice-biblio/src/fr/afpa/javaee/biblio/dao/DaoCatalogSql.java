package fr.afpa.javaee.biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Book;
import fr.afpa.javaee.biblio.model.Catalog;
import fr.afpa.javaee.biblio.model.Copy;
import fr.afpa.javaee.biblio.model.Subscriber;

public class DaoCatalogSql implements IDaoCatalog {
	private String url = "jdbc:mysql://localhost:3306/bibliotheque";
	private String login = "root";
	private String password = "WINDSURF";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet result = null;
	private Catalog catalog = null;
	private ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
	private ArrayList<Book> books = new ArrayList<Book>();
	private int id;

	
	
	public DaoCatalogSql() {
		init();
	}

	private void init() {
		try {
			// chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void DeleteCatalog(int id) {
		
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();

			String requeteDeleteCatalog = "DELETE FROM catalogue  WHERE id_catalogue = '" + id + "'";
			//System.out.println(requeteDeleteCatalog);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteDeleteCatalog);	
			ps.executeUpdate(requeteDeleteCatalog);
			catalogs.remove(catalog);
			

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public ArrayList<Catalog> getAll() {
	
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetAll = "SELECT id_catalogue,description\r\n" 
			+ "FROM catalogue ";
			//System.out.println(requeteGetAll);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetAll);
			ResultSet rsRequeteGetAll = ps.executeQuery();
			catalogs.clear();
			while (rsRequeteGetAll.next()) {

				catalog = new Catalog(rsRequeteGetAll.getInt("catalogue.id_catalogue"),
						rsRequeteGetAll.getString("catalogue.description"));
				catalogs.add(catalog);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(catalog);
		return catalogs;

	}

	@Override
	public Catalog modifyOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getIdCatalog() {
		
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// ajout du livre dans la bibliotheque
			String requeteGetId = "select  id_catalogue as nbre  from catalogue";

			//System.out.println(requeteGetId);

			result = statement.executeQuery(requeteGetId);
			while (result.next()) {
				String idTemp = result.getString("nbre");
				id = Integer.valueOf(idTemp).intValue();
			}
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getIdCatalog();

	}

	@Override
	public void addCatalog(Catalog c) {
		catalog = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// ajout du catalogue dans la bibliotheque
			String requeteAddCatalog = "INSERT INTO catalogue" + "(id_catalogue,description) "
					+ "VALUES (" + c.getId_catalogue() + ",'" + c.getDescription() + "');";
			//System.out.println(requeteAddCatalog);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteAddCatalog);
			// ResultSet rsRequeteAddBook = ps.executeQuery();
			ps.executeUpdate(requeteAddCatalog);

			catalog = new Catalog(c.getId_catalogue(), c.getDescription());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String nom, int id) {
		catalog = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// modif un catalogue dans la bibliotheque
			String requeteUpdate = "UPDATE catalogue SET  description = '" + nom + "'" + " WHERE id_catalogue = " + id
					+ ";";
			//System.out.println(requeteUpdate);

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteUpdate);
			ps.executeUpdate(requeteUpdate);

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public Catalog getOne(int id) {
		catalog=null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetOne = "SELECT id_catalogue,description \r\n"
					+ "FROM catalogue\r\n"
					+ "WHERE id_catalogue = " + id + "";

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetOne);
			ResultSet rsRequeteGetOne = ps.executeQuery();
			catalogs.clear();
			if(rsRequeteGetOne.next()) {

			

				catalog = new Catalog(rsRequeteGetOne.getInt("catalogue.id_catalogue"), rsRequeteGetOne.getString("catalogue.description"));
				catalogs.add(catalog);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return catalog;

	}
	@Override
	public ArrayList<Book>getBook(int id) {
		catalog = null;
		
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetOne = "SELECT livre.isbn,livre.titreLivre\r\n"
					+ "FROM catalogue," + "livre\r\n"
					+ "WHERE catalogue.id_catalogue = livre.id_catalogue\r\n" 
					+ "AND catalogue.id_catalogue = " + id + "" ;

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetOne);
			ResultSet rsRequeteGetBook = ps.executeQuery();

			while (rsRequeteGetBook.next()) {

				
				int isbn = rsRequeteGetBook.getInt("isbn");
				String title= rsRequeteGetBook.getString("titreLivre");
				
				

				 Book book = new Book(rsRequeteGetBook.getInt("livre.isbn"),
						rsRequeteGetBook.getString("livre.titreLivre"));
				 books.add(book);
				catalog = new Catalog(id,title);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(books);
		return books;
	}

}