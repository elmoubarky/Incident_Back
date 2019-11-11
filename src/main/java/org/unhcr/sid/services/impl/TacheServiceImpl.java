package org.unhcr.sid.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unhcr.sid.dao.TacheRepository;
import org.unhcr.sid.entities.OperationAchat;
import org.unhcr.sid.entities.Tache;
import org.unhcr.sid.services.TacheService;

@Service
@Transactional
public class TacheServiceImpl implements TacheService{
	
	@Autowired
	private TacheRepository tacheRepository;

	@Override
	public Tache saveTache(String nature, String type, String equipement, String priorite,String assigne, String username) {
		// TODO Auto-generated method stub
		Tache tache = new Tache();
		
		//generer le numero de la tache
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String numero  ="HCR-ICO-"+sdf.format(new Date());
		tache.setNature(nature);
		tache.setNumeroTache(numero);
		tache.setStatus("EN COURS");
		SimpleDateFormat sdf2  = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		tache.setDateCreation(sdf2.format(new Date()));
		tache.setType(type);
		tache.setEquipement(equipement);
		tache.setPriorite(priorite);
		tache.setAssigne(assigne);
		tache.setUsername(username);
		tacheRepository.save(tache);
		return tache;
	}

	@Override
	public Tache loadTacheByNature(String nature) {
		// TODO Auto-generated method stub
		return tacheRepository.findByNature(nature);
	}

	@Override
	public Tache loadTacheByDateCreation(String dateCreation) {
		// TODO Auto-generated method stub
		return tacheRepository.findByDateCreation(dateCreation);
	}

	@Override
	public Tache loadTacheByDateCloture(String dateCloture) {
		// TODO Auto-generated method stub
		return tacheRepository.findByDateCloture(dateCloture);
	}

	@Override
	public Tache loadTacheByType(String type) {
		// TODO Auto-generated method stub
		return tacheRepository.findByType(type);
	}

	@Override
	public Tache loadTacheByEquipement(String equipement) {
		// TODO Auto-generated method stub
		return tacheRepository.findByEquipement(equipement);
	}

	@Override
	public Tache addResolutionToTache(String numerotache, String staff, String resolvedMethode, String username) {
		// TODO Auto-generated method stub
		
		//recuperer la tache lie au numero
		System.out.println("numero de la tache "+numerotache);
		Tache tache = tacheRepository.findByNumeroTache(numerotache);
		System.out.println("tache "+tache);
		
		
		//verifier si la tache est null
		if(tache==null)throw new RuntimeException("Tache not exists");
		//modifier la tache
		String nature = tache.getNature();
		String priorite = tache.getPriorite();
		String dateCreation = tache.getDateCreation();
		String type = tache.getType();
		String equipement = tache.getEquipement();
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		tache.setDateCloture(sdf.format(new Date()));
		System.out.println("-----------resolvedMethode-----------"+resolvedMethode);
		tache.setResolvedMethode(resolvedMethode);
		tache.setUsername(username);
		tache.setStatus("RESOLU");
		tache.setStaff(staff);
		tache.setPriorite(priorite);
		tache.setDateCreation(dateCreation);
		tache.setNature(nature);
		tache.setType(type);
		tache.setEquipement(equipement);
	
		
		tacheRepository.save(tache);
		return tache;
		
	}

	@Override
	public Tache loadTacheByNumeroTache(String numero) {
		// TODO Auto-generated method stub
		return tacheRepository.findByNumeroTache(numero);
	}

	@Override
	public Tache updateTache(String numerotache, String nature, String type, String priorite, String assigne, String username) {
		// TODO Auto-generated method stub
		//recuperer la tache lie au numero
				System.out.println("numero de la tache "+numerotache);
				Tache tache = tacheRepository.findByNumeroTache(numerotache);
				System.out.println("tache "+tache);
				
				//verifier si la tache est null
				if(tache==null)throw new RuntimeException("Tache not exists");
				//modifier la tache
				//String status = tache.getStatus();
				//String dateCreation = tache.getDateCreation();
				System.out.println("-----------Nature-----------"+nature);
				System.out.println("-----------Type-----------"+type);
				//tache.setNumeroTache(numerotache);
			//	tache.setStatus(status);
				tache.setNature(nature);
				tache.setType(type);
				//tache.setDateCreation(dateCreation);
				tache.setPriorite(priorite);
				tache.setUsername(username);
				tache.setAssigne(assigne);
				
				tacheRepository.saveAndFlush(tache);
				return tache;
	}

	@Override
	public List<Tache> RechTache(String option, String search) {
		// TODO Auto-generated method stub
		List<Tache> lis = new ArrayList<>();
		System.out.println("option "+option);
		System.out.println("search "+search);
		if(option==null) throw new RuntimeException("Value search are null"); 
		if(option.equals("Numero")) {
			lis = tacheRepository.findByNumeroTacheContains(search);
			System.out.println("retour findByNumeroTacheContains "+lis);
		}else if(option.equals("Type")) {
			lis = tacheRepository.findByTypeContains(search);
			System.out.println("retour findByTypeContains "+lis);
		}else if(option.equals("Staff")) {
			lis = tacheRepository.findByStaffContains(search);
			System.out.println("retour findByTypeContains "+lis);
		}else if(option.equals("Nature")) {
			lis = tacheRepository.findByNatureContains(search);
			System.out.println("retour findByNatureContains "+lis);
		}
		
		return lis;
	}

	@Override
	public List<Tache> listTache() {
		// TODO Auto-generated method stub
		return tacheRepository.findAll();
	}

	@Override
	public List<Tache> RechTacheNumeroStatus(String numero, String status) {
		// TODO Auto-generated method stub
		
		return tacheRepository.findByNumeroTacheContainsAndStatusContains(numero, status);
	}

	@Override
	public List<Tache> RechTacheStatus(String status) {
		// TODO Auto-generated method stub
		return tacheRepository.findByStatusContains(status);
	}

	@Override
	public Tache loadTacheByAssigne(String assigne) {
		// TODO Auto-generated method stub
		return tacheRepository.findByAssigne(assigne);
	}

	@Override
	public List<Tache> getAllTacheByDate(String date1, String date2) {
		// TODO Auto-generated method stub
		System.out.println("date 1 "+date1);
		System.out.println("date 2 "+date2);
		List<Tache> lis = new ArrayList<>();
		lis = tacheRepository.findTacheByDate(date1, date2);
		System.out.println("retour getAllOpersByDate "+lis);
		return lis;
	}

	

}
