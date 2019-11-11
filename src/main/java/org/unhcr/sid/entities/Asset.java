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
public class Asset implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAsset;
	private String modele;
	private String type;
	private String bc;
	private String serialId;
	//represente username
	private String username;
	private String custodian;
	private String dateAchat;
	private String dateEnreg;
	private String dateMaintenance;
	private String status;
	private String bureau;
	private String observation;
	private String qrCode;
	

}
