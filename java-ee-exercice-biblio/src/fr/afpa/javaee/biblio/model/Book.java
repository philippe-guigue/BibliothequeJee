package fr.afpa.javaee.biblio.model;

public class Book {

	
	private int isbn;
	private String title;
	private String subtitle;
	private int author;
	private int catalogue;
	private String nomAuteur;
	private String prenomAuteur;
	private String nomCatalogue;
	
	
	public String getPrenomAuteur() {
		return prenomAuteur;
	}
	public void setPrenomAuteur(String prenomAuteur) {
		this.prenomAuteur = prenomAuteur;
	}
	
	public String getNomAuteur() {
		return nomAuteur;
	}
	public void setNomAuteur(String nomAuteur) {
		this.nomAuteur = nomAuteur;
	}
	public String getNomCatalogue() {
		return nomCatalogue;
	}
	public void setNomCatalogue(String nomCatalogue) {
		this.nomCatalogue = nomCatalogue;
	}
	@Override
	public String toString() {
		return  isbn + " - "  + getTitle() + " " + getSubtitle() + " " + getNomAuteur() + " "+ getPrenomAuteur() + " "+ getNomCatalogue() ;
	}
	/**
	 * @return the isbn
	 */
	public int getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @return the author
	 */
	public int getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(int author) {
		this.author = author;
	}
	/**
	 * @return the catalogue
	 */
	public int getCatalogue() {
		return catalogue;
	}
	/**
	 * @param catalogue the catalogue to set
	 */
	public void setCatalogue(int catalogue) {
		this.catalogue = catalogue;
	}
	/**
	 * @param isbn
	 * @param title
	 * @param subtitle
	 * @param author
	 * @param catalogue
	 */
	public Book(int isbn, String title, int id_author, String subtitle , int id_catalogue) {
		this.isbn = isbn;
		this.title = title;
		this.subtitle = subtitle;
		this.author = id_author;
		this.catalogue = id_catalogue;
	}
	public Book(int isbn, String title, String subtitle){
		this.isbn = isbn;
		this.title = title;
		this.subtitle = subtitle;

	}

	
	public Book( String title, int id_author, String subtitle , int id_catalogue) {
		
		this.title = title;
		this.subtitle = subtitle;
		this.author = id_author;
		this.catalogue = id_catalogue;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
	public Book(int isbn,String title) {
		this.isbn = isbn;
		this.title= title;
	}
	public Book(int isbn) {
		this.isbn = isbn;
	}

	public Book( int isbn,String title,String subtitle, String nomAuteur,String prenomAuteur,String nomCatalogue) {
		this.isbn = isbn;
		this.title = title;
		this.subtitle= subtitle;
		this.nomAuteur = nomAuteur;
		this.prenomAuteur = prenomAuteur;
		this.nomCatalogue = nomCatalogue;
	
	}

	
	
	
	
	
	
}
	
	