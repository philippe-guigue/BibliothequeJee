package fr.afpa.javaee.biblio.dao;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Copy;
import fr.afpa.javaee.biblio.model.Subscriber;

public interface IDaoSub {
	
	public void DeleteSub(int id);
    
	public ArrayList<Subscriber> getAll();
	
	public ArrayList<Copy> getBook(int id);
	
	public Subscriber modifyOne(int id);
	
	public void addSub(Subscriber s);
	
	public Subscriber getOne(int id);
	
	public void update(String nom, String prenom,int id);


}
