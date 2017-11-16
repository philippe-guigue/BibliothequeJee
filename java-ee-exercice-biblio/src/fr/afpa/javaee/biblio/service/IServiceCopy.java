package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Copy;

public interface IServiceCopy {
	public void DeleteCopy (int id);
    
	public ArrayList<Copy> getAll();
	
	public Copy getOne(int Id);
	
	public Copy modifyOne(int id);
	
	public void addCopy(Copy c);

	public void update(int id, int dispo, int isbn, int numAbonne);

}
