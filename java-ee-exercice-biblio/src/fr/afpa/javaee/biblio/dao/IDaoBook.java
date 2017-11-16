package fr.afpa.javaee.biblio.dao;



import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;

public interface IDaoBook {
	//public Book DeleteBook (int isbn);
    
	public ArrayList<Book> getAll();
	
	public Book getOne(int isbn);
	
	public Book modifyOne(int isbn);
	
	public void addBook(Book b);
	
	public void update(String title, int author, String subtitle, int isbn, int catalog);


	void deleteBook(int isbn);



	
}
