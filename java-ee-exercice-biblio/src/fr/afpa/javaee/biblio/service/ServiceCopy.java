package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.dao.IDaoCopy;
import fr.afpa.javaee.biblio.model.Copy;

public class ServiceCopy implements IServiceCopy {
	private IDaoCopy daoC;
	
	public ServiceCopy(IDaoCopy daoC) {
		this.daoC = daoC;	
}

	@Override
	public void DeleteCopy(int id) {
		daoC.DeleteCopy(id);
		
	}

	@Override
	public ArrayList<Copy> getAll() {
		// TODO Auto-generated method stub
		return daoC.getAll();
	}

	@Override
	public Copy getOne(int Id) {
		// TODO Auto-generated method stub
		return daoC.getOne(Id);
	}

	@Override
	public Copy modifyOne(int id) {
		// TODO Auto-generated method stub
		return daoC.modifyOne(id);
	}

	@Override
	public void addCopy(Copy c) {
		daoC.addCopy(c);
		
	}

	@Override
	public void update(int id, int dispo, int isbn, int numAbonne) {
		daoC.update(id, dispo, isbn,numAbonne);
		
	}

}