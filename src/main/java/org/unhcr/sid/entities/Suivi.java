package org.unhcr.sid.entities;

import java.io.Serializable;
import java.util.Date;

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
public class Suivi implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSuivi;
	private String pt8;
	private String pt8_add;
	private String type;
	private Date dateSuivi;
	private String beneficiaire;
	private String section_benef;
	private String periode_de;
	private String periode_a;
	private String objet;
	private String lieu;
	private String cost_center;
	private String budget; 
	private String nbre_nuite;
	private String taux_change;
	private String cout_xof;
	private String cout_ussd;
	private String taux_ussd;
	private String taux_xof;
	private String montant_ussd;
	private String montant_xof;
	private Date visa_date;
	private String visa_initial;
	private Date certif_date;
	private String certif_initial;
	private Date avance_paye_date;
	private String avance_paye_pv;
	private String avance_paye_xof;
	private String avance_paye_ussd;
	private String avance_paye_traite_par;
	private String total_ussd;
	private String total_xof;
	private String solde_apayer_ussd;
	private String solde_apayer_xof;
	private Date tc_date;
	private String tc_deposit;
	private String tc_finalise;
	private String userName;

}
