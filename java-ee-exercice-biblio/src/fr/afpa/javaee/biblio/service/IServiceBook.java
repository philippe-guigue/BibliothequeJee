package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Book;

public interface IServiceBook {
	public void DeleteBook (int isbn);
       
	public ArrayList<Book> getAll();
	
	public Book getOne(int Isbn);
	
	public void modifyOne(Book b);
	
	public void addBook(Book b);


	public void update(String title, int author, String subtitle, int isbn, int catalog);

	

	

	
	
}
