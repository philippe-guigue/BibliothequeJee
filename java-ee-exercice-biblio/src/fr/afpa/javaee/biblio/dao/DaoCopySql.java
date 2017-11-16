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
import fr.afpa.javaee.biblio.model.Copy;
import fr.afpa.javaee.biblio.model.Subscriber;

public class DaoCopySql implements IDaoCopy {
	private String url = "jdbc:mysql://localhost:3306/bibliotheque";
	private String login = "root";
	private String password = "WINDSURF";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet requeteGetOne = null;
	private ResultSet requeteGetAll = null;
	private ResultSet requeteAddBook = null;
	private Copy copy = null;
	private Author auteur;
	private int isbn;
	private Book book;
	private Subscriber sub;
	private int estDispo;
	private ArrayList<Copy> copies = new ArrayList<Copy>();

	public DaoCopySql() {
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
	public void DeleteCopy(int id) {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteDeleteCopy = "DELETE FROM exemplaire  WHERE id_exemplaire = " + id + "";
			System.out.println(requeteDeleteCopy);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteDeleteCopy);
			ps.executeUpdate(requeteDeleteCopy);
			copies.remove(copy);
		} catch (SQLException e) {
			e.printStackTrace();
			

		}
	}

	@Override
	public ArrayList<Copy> getAll() {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetAll = "SELECT * FROM exemplaire ";

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetAll);
			ResultSet rsRequeteGetAll = ps.executeQuery();
			copies.clear();
			while (rsRequeteGetAll.next()) {

				copy = new Copy(rsRequeteGetAll.getInt("exemplaire.id_exemplaire"),
						rsRequeteGetAll.getInt("exemplaire.estDisponible"), rsRequeteGetAll.getInt("exemplaire.ISBN"),
						rsRequeteGetAll.getInt("exemplaire.NumAbonne"));
				copies.add(copy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return copies;
	}

	@Override
	public Copy getOne(int id) {
		copy = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetOne = "SELECT id_exemplaire,estDisponible,exemplaire.ISBN,livre.ISBN,abonne.NumAbonne,\r\n"
					+ "personne.Nom,personne.Prenom, livre.titreLivre\r\n"
					+ "FROM .livre,\r\n" + ".exemplaire,.Abonne,\r\n" + ".personne\r\n" 
					+ "WHERE exemplaire.ISBN = livre.ISBN " 
					+ "AND .exemplaire.NumAbonne = abonne.NumAbonne\r\n"
					+ "AND .abonne.id_personne = personne.id_personne" 
					+ " AND id_exemplaire = "+ id+ "";
				
			System.out.println(requeteGetOne);
			
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetOne);
			ResultSet rsRequeteGetOne = ps.executeQuery();
			copies.clear();
			if (rsRequeteGetOne.next()) {

				int estDispo = rsRequeteGetOne.getInt("estDisponible");
				

				book = new Book(rsRequeteGetOne.getInt("livre.ISBN"), rsRequeteGetOne.getString("livre.titreLivre"));
				sub = new Subscriber(rsRequeteGetOne.getString("personne.prenom"),rsRequeteGetOne.getString("personne.nom"), rsRequeteGetOne.getInt(id));
				copy = new Copy (id,estDispo,book.getIsbn(),sub.getId());
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(copy);
		return copy;

	}

	@Override
	public Copy modifyOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCopy(Copy c) {
		copy = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// ajout du livre dans la bibliotheque
			String requeteAddCopy = "INSERT INTO exemplaire " + "VALUES (" + c.getId() + ",'" + c.getEstDispo() + "'," + c.getIsbn() + "," + c.getNumAbonne() + ");";
			System.out.println(requeteAddCopy);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteAddCopy);
			// ResultSet rsRequeteAddBook = ps.executeQuery();
			ps.executeUpdate(requeteAddCopy);


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void update(int id, int dispo, int isbn, int numAbonne) {
		copy= null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// modif un livre dans la bibliotheque
			String requeteUpdate = "UPDATE exemplaire " + "SET id_exemplaire = " + id + ", estDisponible = '" + dispo 
					+ "' , ISBN = '" + isbn + "' , NumAbonne = " + numAbonne
					+ "  " + " WHERE id_exemplaire = '" + id + "';";
			System.out.println(requeteUpdate);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteUpdate);
			// ResultSet rsRequeteAddBook = ps.executeQuery();
			ps.executeUpdate(requeteUpdate);

			copy = new Copy(id, dispo, isbn, numAbonne);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
