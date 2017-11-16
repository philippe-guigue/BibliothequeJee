package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.model.Copy;
import fr.afpa.javaee.biblio.model.Subscriber;

public interface IServiceSub {
	public ArrayList<Subscriber> getAll();

	public void DeleteSub(int id);

	public ArrayList<Copy> getBook(int id);

	public Subscriber modifyOne(int id);

	public Subscriber getOne(int id);
	public void addSub(Subscriber s);

	public void update(String prenom, String nom, int newid);

}
