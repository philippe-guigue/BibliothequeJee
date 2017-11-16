package fr.afpa.javaee.biblio.dao;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;


public interface IDaoAuthor {
public void DeleteAuthor (int id);
    
	public ArrayList<Author> getAll();
	
	public ArrayList<Book> getBook(int id);
	
	public Author modifyOne(int id);
	
	public void addAuthor(Author a);
	
	public Author getOne(int id);
	
	public void update(String prenom, String nom, int id);

	
	
}
