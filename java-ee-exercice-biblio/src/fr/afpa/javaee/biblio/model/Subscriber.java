package fr.afpa.javaee.biblio.model;

public class Subscriber extends Personne {
	private int id = 0;
	private String nom;
	private String prenom;
	private String titre;

	public Subscriber(String prenom, String nom, int id_personne, int id) {
		super(prenom, nom, id_personne);
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Subscriber(int id, String prenom, String nom, int id_personne) {
		super(prenom, nom, id_personne);
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Subscriber(String nom, String prenom, int id) {
		super(prenom, nom, id);
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Subscriber(String prenom, String nom) {
		super(prenom, nom);
		this.prenom = prenom;
		this.nom = nom;
	}

	public Subscriber(int id) {
		super(id);
		this.id = id;
	}

	public Subscriber(int isbn, String title) {
		super(isbn,title);
		this.id=isbn;
		this.setTitre(title);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		id++;
	}

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

	@Override
	public String toString() {
		return id + " - " + getPrenom() + "  " + getNom();
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

}
