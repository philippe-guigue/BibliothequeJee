package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.dao.IDaoAuthor;
import fr.afpa.javaee.biblio.model.Author;
import fr.afpa.javaee.biblio.model.Book;

public class ServiceAuthor implements IServiceAuthor {
	private IDaoAuthor daoA;

	public ServiceAuthor(IDaoAuthor dao) {
		this.daoA = dao;
	}

	@Override
	public ArrayList<Author> getAll() {
		return daoA.getAll();
	}

	@Override
	public void DeleteAuthor(int id) {
		daoA.DeleteAuthor(id);

	}

	@Override
	public  ArrayList<Book> getBook(int id) {
		return daoA.getBook(id);
	}

	@Override
	public Author modifyOne(int id) {
		return daoA.modifyOne(id);
	}

	@Override
	public void addAuthor(Author a) {
		daoA.addAuthor(a);

	}

	@Override
	public void update(String prenom, String nom, int id) {
		daoA.update(prenom, nom, id);
	}

	@Override
	public Author getOne(int id) {
		return daoA.getOne(id);
	}


}
