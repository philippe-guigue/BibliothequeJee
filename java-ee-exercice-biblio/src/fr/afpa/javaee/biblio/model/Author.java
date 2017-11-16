package fr.afpa.javaee.biblio.model;

public class Author extends Personne {

	private String prenom;
	private String nom;
	private int id = 0;
	private String titre;

	/**
	 * @param prenom
	 * @param nom
	 * @param id_personne
	 */

	public Author(String prenom, String nom, String titre,int id_personne, int id) {
		super(prenom, nom, id_personne);
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.setTitre(titre);
		
	}

	public Author(int id, String prenom, String nom, int id_personne) {
		super(prenom, nom, id_personne);
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;

	}

	public Author(String nom, String prenom, int id) {
		super(prenom, nom, id);
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Author(String prenom, String nom) {
		super(prenom, nom);
		this.prenom = prenom;
		this.nom = nom;
	}

	public Author(int id) {
		super(id);
		this.id = id;
	}

	public Author(int isbn, String title, String prenom, String nom) {
		super(prenom, nom, isbn);
		this.id = isbn;
		this.prenom = prenom;
		this.nom = nom;
	}


	public Author(int isbn, String title) {
		super(isbn,title);
		this.id=isbn;
		this.setTitre(title);
	}

	public Author(String nom, int id) {
		super(id,nom);
		this.nom=nom;
		this.id=id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id + " - " + getTitre();
	}



	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;

	}

	public void setId(int id) {
		this.id = id;
		id++;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}