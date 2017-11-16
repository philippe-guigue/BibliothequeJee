package fr.afpa.javaee.biblio.model;

public class Catalog {
	private int id_catalogue;
	private String description;

	
	
	
	
	/**
	 * @param id
	 * @param title
	 */
	public Catalog(int id, String title) {
		this.id_catalogue = id;
		this.description = title;
		this.id_catalogue=+id;
	}
	public Catalog(String title) {
		this.description =title;
	}
	public Catalog(int id) {
		this.id_catalogue = id;
		
		this.id_catalogue=+id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id_catalogue + " - " + description + " ";
	}

	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId_catalogue() {
		return id_catalogue;
	}
	public void setId_catalogue(int id_catalogue) {
		this.id_catalogue = id_catalogue;
	}
	
	
	
}