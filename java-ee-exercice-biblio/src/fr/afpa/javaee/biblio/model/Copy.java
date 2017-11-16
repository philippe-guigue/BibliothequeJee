package fr.afpa.javaee.biblio.model;

public class Copy {

	private int id = 0;
	private int Isbn;
	private int estDispo;
	private int NumAbonne;
	private int subscriber;
	private String nom;
	private String prenom;
	private String title;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		id++;
	}

	public int getIsbn() {
		return Isbn;
	}

	public void setIsbn(int isbn) {
		Isbn = isbn;
	}

	public int getEstDispo() {
		return estDispo;
	}

	public void setEstDispo(int estDispo) {
		this.estDispo = estDispo;
	}

	public int getNumAbonne() {
		return NumAbonne;
	}

	public void setNumAbonne(int numAbonne) {
		NumAbonne = numAbonne;
	}


	public Copy() {
		// TODO Auto-generated constructor stub
	}

	public Copy(int id, int estDispo, int isbn, String title, String nom, String prenom) {
		this.id = id;
		this.Isbn = isbn;
		this.estDispo = estDispo;
		this.nom = nom;
		this.prenom = prenom;
		this.setTitle(title);
	}

	public Copy(int isbn, String title) {
		this.id = isbn;
		this.setTitle(title);
	}

	public Copy(int isbn, int dispo, int id) {
		this.id = id;
		this.Isbn = isbn;
		this.estDispo=dispo;
	
	}

	public Copy(int id, int dispo,int isbn, int idSub) {
		this.id = id;
		this.Isbn = isbn;
		this.estDispo = dispo;
		this.NumAbonne=idSub;
	}

	public int getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(int subscriber) {
		this.subscriber = subscriber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return id + " - " + getTitle();

	}
}
