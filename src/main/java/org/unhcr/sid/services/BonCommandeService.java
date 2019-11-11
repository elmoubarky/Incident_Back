package org.unhcr.sid.services;


import org.unhcr.sid.entities.Bon_commande;;

public interface BonCommandeService {
	
	public Bon_commande saveBcAdmin(String numbc, String datebc, String fournisseur, String designation, 
			String qte, String pu, String montant, String cost_center, String prog, String account, String visa_date,
			String visa_initial, String certif_date, String certif_initial, String transf_date, String transf_initial
			, String username);
	public Bon_commande saveBcEtat(String numbc, String datebc, String fournisseur, String designation, 
			String qte, String pu, String montant, String cost_center, String prog, String account, String visa_date,
			String visa_initial, String certif_date, String certif_initial, String transf_date, String transf_initial,
			String numfacture, String dateReception, String persResource, String reglEff, 
			String observation, String username);
	public Bon_commande loadBonCommandeByNumBc(String numbc);
	public Bon_commande updateBcAdmin(int idBc, String numbc, String datebc, String fournisseur, String designation, 
			String qte, String pu, String montant, String cost_center, String prog, String account, String visa_date,
			String visa_initial, String certif_date, String certif_initial, String transf_date, 
			String transf_initial, String username);
	public Bon_commande updateBcEtat(int idBc, String numbc, String datebc, String fournisseur, String designation, String qte,
			String pu, String montant, String cost_center, String prog, String account, String visa_date,
			String visa_initial, String certif_date, String certif_initial, String transf_date, String transf_initial,
			String numfacture, String dateReception, String persResource, String reglEff,
			String observation, String username);
	public Bon_commande updateBcReception(int idBc, String numbc, String dateReception, 
			String persResource, String transf_date, String username);
	public Bon_commande updateBcFinance(int idBc, String numbc, String reglEff, String observation, String username);

}
