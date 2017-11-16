package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;

public interface IServiceAuthor {

	public ArrayList<Author> getAll();

	public void DeleteAuthor(int id);

	public  ArrayList<Book> getBook(int id);

	public Author modifyOne(int id);

	public Author getOne(int id);
	
	public void addAuthor(Author a);

	public void update(String prenom, String nom, int id);

}
