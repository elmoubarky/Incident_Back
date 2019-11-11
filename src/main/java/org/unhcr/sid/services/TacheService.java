package org.unhcr.sid.services;


import java.util.List;

import org.unhcr.sid.entities.Tache;

public interface TacheService {
	
	public Tache saveTache(String nature, String type, String equipement, String priorite, String assigne, String username);
	public Tache loadTacheByNature(String nature);
	public Tache loadTacheByNumeroTache(String numero);
	public Tache loadTacheByDateCreation(String dateCreation);
	public Tache loadTacheByDateCloture(String dateCloture);
	public Tache loadTacheByAssigne(String assigne);
	public Tache loadTacheByType(String type);
	public List<Tache> listTache();
	public Tache loadTacheByEquipement(String equipement);
	public Tache addResolutionToTache(String numerotache, String staff, String resolvedMethode, String username);
	public Tache updateTache(String numerotache, String nature, String type, String priorite, String assigne, String username);
	public List<Tache> RechTache(String value, String search);
	public List<Tache> RechTacheNumeroStatus(String numero, String status);
	public List<Tache> RechTacheStatus(String status);
	public List<Tache> getAllTacheByDate(String date1, String date2);

}
