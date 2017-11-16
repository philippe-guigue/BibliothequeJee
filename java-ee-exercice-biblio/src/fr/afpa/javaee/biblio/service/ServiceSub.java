package fr.afpa.javaee.biblio.service;

import java.util.ArrayList;

import fr.afpa.javaee.biblio.dao.IDaoSub;
import fr.afpa.javaee.biblio.model.Copy;
import fr.afpa.javaee.biblio.model.Subscriber;

public class ServiceSub implements IServiceSub {
	private IDaoSub daoS;

	public ServiceSub(IDaoSub dao) {
		this.daoS = dao;
	}

	@Override
	public ArrayList<Subscriber> getAll() {
		return daoS.getAll();
	}

	@Override
	public void DeleteSub(int id) {
		daoS.DeleteSub(id);

	}

	@Override
	public ArrayList<Copy> getBook(int id) {

		return daoS.getBook(id);
	}

	@Override
	public Subscriber modifyOne(int id) {

		return daoS.modifyOne(id);
	}

	@Override
	public void addSub(Subscriber s) {
		daoS.addSub(s);

	}

	@Override
	public void update(String prenom, String nom, int id) {
		daoS.update(prenom, nom, id);

	}

	@Override
	public Subscriber getOne(int id) {
		return daoS.getOne(id);
	}

}