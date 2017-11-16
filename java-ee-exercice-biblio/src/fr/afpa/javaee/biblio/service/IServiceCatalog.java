package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Book;
import fr.afpa.javaee.biblio.model.Catalog;

public interface IServiceCatalog {
	
	
	public ArrayList<Catalog> getAll();


	public Catalog getOne(int id);

	public Catalog modifyOne(int id);

	public  void addCatalog(Catalog c);

	public void update(String nom, int id);

	public void DeleteCatalog(int id);

	public ArrayList<Book> getBook(int id);
}
