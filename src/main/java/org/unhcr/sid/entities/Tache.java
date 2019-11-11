package org.unhcr.sid.entities;

import java.io.Serializable;
import javax.persistence.Entity;
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
public class Tache implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String numeroTache;
	private String dateCreation;
	private String nature;
	private String type;
	private String staff;
	private String status;
	private String dateCloture;
	private String resolvedMethode;
	private String equipement;
	private String qrcode;
	private String priorite;
	private String assigne;
	private String username;
	

}
