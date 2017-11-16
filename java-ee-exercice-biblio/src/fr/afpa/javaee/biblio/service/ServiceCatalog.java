package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.dao.IDaoCatalog;
import fr.afpa.javaee.biblio.model.Book;
import fr.afpa.javaee.biblio.model.Catalog;

public class ServiceCatalog implements IServiceCatalog {
	private IDaoCatalog daoC;
	
	
	public ServiceCatalog(IDaoCatalog dao) {
		this.daoC = dao;
	}


	@Override
	public ArrayList<Catalog> getAll() {
		// TODO Auto-generated method stub
		return daoC.getAll();
	}


	@Override
	public Catalog getOne(int id) {
		return daoC.getOne(id);
	}


	@Override
	public Catalog modifyOne(int id) {
		// TODO Auto-generated method stub
		return daoC.modifyOne(id);
	}


	@Override
	public void addCatalog(Catalog c) {
		daoC.addCatalog(c);
		
	}


	

	@Override
	public void DeleteCatalog(int id) {
		daoC.DeleteCatalog(id);
		
	}


	@Override
	public void update(String nom,int id) {
		daoC.update(nom,id);
		
	}


	@Override
	public ArrayList<Book> getBook(int id) {
		return daoC.getBook(id);
	}

	
}