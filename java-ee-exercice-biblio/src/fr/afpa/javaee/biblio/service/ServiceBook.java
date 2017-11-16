package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.dao.IDaoBook;
import fr.afpa.javaee.biblio.model.Book;


public class ServiceBook implements IServiceBook {
	private IDaoBook daoB;

public ServiceBook(IDaoBook dao) {
	this.daoB = dao;
}

@Override
public ArrayList<Book> getAll() {
	
	return daoB.getAll();
}

@Override
public Book getOne(int isbn) {
	return daoB.getOne(isbn);
}

@Override
public void modifyOne(Book b) {
	// TODO Auto-generated method stub
	
}


@Override
public void DeleteBook(int isbn) {
	daoB.deleteBook(isbn);
}

@Override
public void addBook(Book b) {
	daoB.addBook(b);
	
}
@Override
public void update(String title, int author, String subtitle, int isbn, int catalog) {
	daoB.update(title, author, subtitle, isbn, catalog);
	
}



}

