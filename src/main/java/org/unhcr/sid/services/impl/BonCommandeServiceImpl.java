package org.unhcr.sid.services.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unhcr.sid.dao.BonCommandeRepository;
import org.unhcr.sid.entities.Bon_commande;
import org.unhcr.sid.services.BonCommandeService;

@Service
@Transactional
public class BonCommandeServiceImpl implements BonCommandeService {

	@Autowired
	private BonCommandeRepository bonCommandeRepo;

	@Override
	public Bon_commande saveBcAdmin(String numbc, String datebc, String fournisseur, String designation, String qte,
			String pu, String montant, String cost_center, String prog, String account, String visa_date,
			String visa_initial, String certif_date, String certif_initial, String transf_date, String transf_initial,
			String username) {
		// TODO Auto-generated method stub

		// verifie si le bon de commande existe pas deja
		Bon_commande bc = bonCommandeRepo.findByNumBc(numbc);
		if (bc != null)
			throw new RuntimeException("Bon Commande already exists");
		Bon_commande bon = new Bon_commande();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
			bon.setCertif_date(certif_date);
			System.out.println("certif_date received "+certif_date);
			bon.setCertif_initial(certif_initial);
			bon.setDesignation(designation);
			bon.setFournisseur(fournisseur);
			bon.setMontant(montant);
			bon.setNumBc(numbc);
			bon.setProg(prog);
			bon.setPu(pu);
			bon.setQte(qte);
			bon.setTrans_date(transf_date);
			bon.setTrans_initial(transf_initial);
			bon.setDateCommande(datebc);
			System.out.println("datebc received "+datebc);
			bon.setVisa_date(visa_date);
			System.out.println("visa_date received "+visa_date);
			bon.setVisa_initial(visa_initial);
			bon.setCostCenter(cost_center);
			bon.setAccount(account);
			bon.setUserName(username);
			bonCommandeRepo.save(bon);
			System.out.println("bon de commande " + bon);
			return bon;
	}

	@Override
	public Bon_commande saveBcEtat(String numbc, String datebc, String fournisseur, String designation, String qte,
			String pu, String montant, String cost_center, String prog, String account, String visa_date,
			String visa_initial, String certif_date, String certif_initial, String transf_date, String transf_initial,
			String numfacture, String dateReception, String persResource, String reglEff, String observation,
			String username) {
		// TODO Auto-generated method stub

		// verifie si le bon de commande existe pas deja
		Bon_commande bc = bonCommandeRepo.findByNumBc(numbc);
		if (bc != null)
			throw new RuntimeException("Bon Commande already exists");
		Bon_commande bon = new Bon_commande();
		bon.setCertif_date(certif_date);
		bon.setCertif_initial(certif_initial);
		bon.setDesignation(designation);
		bon.setFournisseur(fournisseur);
		bon.setMontant(montant);
		bon.setNumBc(numbc);
		bon.setProg(prog);
		bon.setPu(pu);
		bon.setQte(qte);
		bon.setTrans_date(transf_date);
		bon.setTrans_initial(transf_initial);
		bon.setDateCommande(datebc);
		bon.setVisa_date(visa_date);
		bon.setVisa_initial(visa_initial);
		bon.setCostCenter(cost_center);
		bon.setAccount(account);
		bon.setDate_recept(dateReception);
		bon.setPersonne_ress(persResource);
		bon.setReglement_effect(reglEff);
		bon.setObservations(observation);
		bon.setUserName(username);
		bonCommandeRepo.save(bon);
		return bon;
	}

	@Override
	public Bon_commande loadBonCommandeByNumBc(String numbc) {
		// TODO Auto-generated method stub
		return bonCommandeRepo.findByNumBc(numbc);
	}

	@Override
	public Bon_commande updateBcAdmin(int idBc, String numbc, String datebc, String fournisseur, String designation,
			String qte, String pu, String montant, String cost_center, String prog, String account, String visa_date,
			String visa_initial, String certif_date, String certif_initial, String transf_date, String transf_initial,
			String username) {
		// TODO Auto-generated method stub
		Bon_commande bon = bonCommandeRepo.findByNumBc(numbc);
		if (bon == null)
			throw new RuntimeException("Bon Commande not exists");
		bon.setCertif_date(certif_date);
		bon.setCertif_initial(certif_initial);
		bon.setDesignation(designation);
		bon.setFournisseur(fournisseur);
		bon.setMontant(montant);
		bon.setNumBc(numbc);
		bon.setProg(prog);
		bon.setPu(pu);
		bon.setQte(qte);
		bon.setTrans_date(transf_date);
		bon.setTrans_initial(transf_initial);
		bon.setDateCommande(datebc);
		bon.setVisa_date(visa_date);
		bon.setVisa_initial(visa_initial);
		bon.setCostCenter(cost_center);
		bon.setAccount(account);
		bon.setUserName(username);
		bonCommandeRepo.saveAndFlush(bon);
		return bon;
	}

	@Override
	public Bon_commande updateBcEtat(int idBc, String numbc, String datebc, String fournisseur, String designation,
			String qte, String pu, String montant, String cost_center, String prog, String account, String visa_date,
			String visa_initial, String certif_date, String certif_initial, String transf_date, String transf_initial,
			String numfacture, String dateReception, String persResource, String reglEff, String observation,
			String username) {
		// TODO Auto-generated method stub
		Bon_commande bon = bonCommandeRepo.findByNumBc(numbc);
		if (bon == null)
			throw new RuntimeException("Bon Commande not exists");
		bon.setCertif_date(certif_date);
		bon.setCertif_initial(certif_initial);
		bon.setDesignation(designation);
		bon.setFournisseur(fournisseur);
		bon.setMontant(montant);
		bon.setNumBc(numbc);
		bon.setProg(prog);
		bon.setPu(pu);
		bon.setQte(qte);
		bon.setTrans_date(transf_date);
		bon.setTrans_initial(transf_initial);
		bon.setDateCommande(datebc);
		bon.setVisa_date(visa_date);
		bon.setVisa_initial(visa_initial);
		bon.setCostCenter(cost_center);
		bon.setAccount(account);
		bon.setDate_recept(dateReception);
		bon.setPersonne_ress(persResource);
		bon.setReglement_effect(reglEff);
		bon.setObservations(observation);
		bon.setUserName(username);
		bonCommandeRepo.saveAndFlush(bon);
		return bon;
	}

	@Override
	public Bon_commande updateBcReception(int idBc, String numbc, String dateReception, String persResource,
			String transf_date, String username) {
		// TODO Auto-generated method stub
		Bon_commande bon = bonCommandeRepo.findByNumBc(numbc);
		if (bon == null)
			throw new RuntimeException("Bon Commande not exists");
		bon.setDate_recept(dateReception);
		bon.setUserName(username);
		bon.setTrans_date(transf_date);
		bon.setPersonne_ress(persResource);
		bonCommandeRepo.saveAndFlush(bon);
		return bon;
	}

	@Override
	public Bon_commande updateBcFinance(int idBc, String numbc, String reglEff, String observation, String username) {
		// TODO Auto-generated method stub
		Bon_commande bon = bonCommandeRepo.findByNumBc(numbc);
		if (bon == null)
			throw new RuntimeException("Bon Commande not exists");
		bon.setUserName(username);
		bon.setObservations(observation);
		bon.setReglement_effect(reglEff);
		bonCommandeRepo.saveAndFlush(bon);
		return bon;
	}

}
