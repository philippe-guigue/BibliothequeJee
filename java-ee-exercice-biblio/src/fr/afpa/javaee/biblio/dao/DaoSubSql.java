package fr.afpa.javaee.biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Copy;
import fr.afpa.javaee.biblio.model.Subscriber;

public class DaoSubSql implements IDaoSub {
	
	private String url = "jdbc:mysql://localhost:3306/bibliotheque";
	private String login = "root";
	private String password = "WINDSURF";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet result = null;
	private ResultSet rsRequeteGetAll = null;
	private ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
	private Subscriber subscriber;
	private ArrayList<Copy> copies = new ArrayList<Copy>();

	public DaoSubSql() {
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
	public ArrayList<Subscriber> getAll() {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetAll = "SELECT abonne.id_personne,abonne.NumAbonne,\r\n"
					+ "personne.Nom,personne.Prenom \r\n" + "FROM abonne,\r\n" + "personne "
					+ "WHERE personne.id_personne = abonne.id_personne";

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetAll);
			ResultSet rsRequeteGetAll = ps.executeQuery();
			subs.clear();
			while (rsRequeteGetAll.next()) {

				subscriber = new Subscriber(rsRequeteGetAll.getString("personne.Prenom"),
						rsRequeteGetAll.getString("personne.nom"), rsRequeteGetAll.getInt("abonne.NumAbonne"));

				subs.add(subscriber);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subs;
	}

	@Override
	public ArrayList<Copy>getBook(int id) {
		subscriber = null;
		
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetOne = "SELECT abonne.NumAbonne,exemplaire.id_exemplaire,livre.isbn,livre.titreLivre\r\n"
					+ "FROM abonne," + "exemplaire," +  "livre\r\n"
					+ "WHERE abonne.NumAbonne = exemplaire.NumAbonne\r\n" 
					+ "AND exemplaire.isbn = livre.isbn\r\n"
					+ "AND abonne.NumAbonne = " + id + "" ;

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetOne);
			ResultSet rsRequeteGetBook = ps.executeQuery();

			while (rsRequeteGetBook.next()) {

				
				int isbn = rsRequeteGetBook.getInt("isbn");
				String title= rsRequeteGetBook.getString("titreLivre");
				
				

				 Copy copy = new Copy(rsRequeteGetBook.getInt("livre.isbn"),
						rsRequeteGetBook.getString("livre.titreLivre"));
				 copies.add(copy);
				subscriber = new Subscriber(isbn,title);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(copies);
		return copies;
	}

	@Override
	public Subscriber modifyOne(int id) {
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

	public int getIdSub() {
		int id = 0;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
	
			String requeteGetIdSub = "select  max(NumAbonne) as nbre  from abonne";

			System.out.println(requeteGetIdSub);

			result = statement.executeQuery(requeteGetIdSub);
			while (result.next()) {
				String idTemp = result.getString("nbre");
				id = Integer.valueOf(idTemp).intValue();
			}
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getIdSub();
	}

	@Override
	public void addSub(Subscriber s) {
		int id = getIdPersonne() + 1;
		int idS = getIdSub() + 1;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// ajout d'un abonne dans la bibliotheque
			String requeteAddPersonne = "INSERT INTO personne " + "VALUES ('" + id + "', '" + s.getNom() + "','"
					+ s.getPrenom() + "');";
			System.out.println(requeteAddPersonne);
			String requeteAddSub = "INSERT INTO abonne " + "VALUES ('" + idS + "', '" + id + "');";
			System.out.println(requeteAddSub);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteAddPersonne);
			ps = connection.prepareStatement(requeteAddSub);

		
			ps.executeUpdate(requeteAddPersonne);
			ps.executeUpdate(requeteAddSub);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String nom, String prenom, int id) {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			// modif un auteur dans la bibliotheque
			String requeteUpdateS = "UPDATE personne SET Nom = '" + nom + "' , prenom = '" + prenom + "'"
					+ " WHERE id_personne = (select id_Personne FROM  abonne WHERE NumAbonne = " + id + ");";
			System.out.println(requeteUpdateS);

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteUpdateS);

			// ResultSet rsRequeteAddBook = ps.executeQuery();
			ps.executeUpdate(requeteUpdateS);

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void DeleteSub(int id) {
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteDeleteSub = "DELETE FROM abonne  WHERE NumAbonne = '" + id + "'";
			System.out.println(requeteDeleteSub);
			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteDeleteSub);
			ps.executeUpdate(requeteDeleteSub);
			subs.remove(subscriber);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Subscriber getOne(int id) {
		subscriber = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			String requeteGetOne = "SELECT abonne.NumAbonne,\r\n"
					+ "personne.Nom,personne.Prenom \r\n"
					+ "FROM abonne,\r\n" + "personne\r\n"
					+ "WHERE abonne.id_personne = personne.id_personne\r\n"
					+ " AND NumAbonne = " + id + "";

			java.sql.PreparedStatement ps;
			ps = connection.prepareStatement(requeteGetOne);
			ResultSet rsRequeteGetOne = ps.executeQuery();
			subs.clear();
			if (rsRequeteGetOne.next()) {


				subscriber = new Subscriber(rsRequeteGetOne.getString("personne.Nom"), rsRequeteGetOne.getString("personne.Prenom"),
						rsRequeteGetOne.getInt("abonne.NumAbonne"));
				
				subs.add(subscriber);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
System.out.println(subscriber);
		return subscriber;

	}

}