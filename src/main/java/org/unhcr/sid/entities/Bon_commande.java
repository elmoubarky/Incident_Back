package org.unhcr.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Bon_commande implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBc;
	private String dateCommande;
	private String numBc;
	private String fournisseur;
	private String designation;
	private String account;
	private String qte;
	private String pu;
	private String montant;
	private String costCenter;
	private String prog;
	private String certif_date;
	private String certif_initial;
	private String visa_date;
	private String visa_initial;
	private String trans_date;
	private String trans_initial;
	private String numfacture;
	private String date_recept;
	private String personne_ress;
	private String reglement_effect;
	private String observations;
	private String userName;
	

}
