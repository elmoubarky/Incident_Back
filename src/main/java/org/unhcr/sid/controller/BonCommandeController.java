package org.unhcr.sid.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unhcr.sid.entities.Bon_commande;
import org.unhcr.sid.services.BonCommandeService;

import lombok.Data;;

@CrossOrigin("*")
@RestController
public class BonCommandeController {
	
	@Autowired
	private BonCommandeService bonCommandeService;
	
	// methode permettant de creer et retourner un objet de type Tache
		@PostMapping("/createBcAdmin")
		public Bon_commande createBcAdmin(@RequestBody BcAdminForm bcAdminForm) {
			return bonCommandeService.saveBcAdmin(bcAdminForm.getNumbc(), bcAdminForm.getDateCommande(),
					bcAdminForm.getFournisseur(), bcAdminForm.getFournisseur(),
					bcAdminForm.getQte(), bcAdminForm.getPu(), bcAdminForm.getMontant(), 
					bcAdminForm.getCostCenter(), bcAdminForm.getProg(), bcAdminForm.getAccount(), 
					bcAdminForm.getVisaDate(), bcAdminForm.getVisaInitial(), bcAdminForm.getCertifDate(),
					bcAdminForm.getCertifInitial(),bcAdminForm.getTransfDate(), 
					bcAdminForm.getTransfInitial(), bcAdminForm.getUsername());
		}

}
@Data
class BcAdminForm {
	private String username;
	private String numbc;
	private String dateCommande;
	private String fournisseur;
	private String designation;
	private String qte;
	private String pu;
	private String montant;
	private String costCenter;
	private String prog;
	private String account;
	private String visaDate;
	private String visaInitial;
	private String certifDate;
	private String certifInitial;
	private String transfDate;
	private String transfInitial;
}
