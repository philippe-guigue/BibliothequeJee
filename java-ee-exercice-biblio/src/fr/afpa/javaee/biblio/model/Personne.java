package fr.afpa.javaee.biblio.model;

public class Personne {
	private String prenom;
	private String nom;
	private int id_personne;
	private int id_auteur;
	private int id;
	/**
	 * @param prenom
	 * @param nom
	 * @param id_personne
	 */
	public Personne(String prenom, String nom, int id_personne) {
		this.prenom = prenom;
		this.nom = nom;
		this.id_personne = id_personne;
	}
	public Personne(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
	}
	public Personne(int id_personne) {
		this.id_personne = id_personne;
	}
	public Personne(String prenom, String nom, int id_personne, int id_auteur) {
		this.prenom = prenom;
		this.nom = nom;
		this.id_personne = id_personne;
		this.setId_auteur(id_auteur);
	}
	public Personne(int isbn, String titre) {
		this.id=isbn;
		this.nom=titre;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the id_personne
	 */
	public int getId_personne() {
		return id_personne;
	}
	/**
	 * @param id_personne the id_personne to set
	 */
	public void setId_personne(int id_personne) {
		this.id_personne = id_personne;
	}
	public int getId_auteur() {
		return id_auteur;
	}
	public void setId_auteur(int id_auteur) {
		this.id_auteur = id_auteur;
	}
	
	
	
}
