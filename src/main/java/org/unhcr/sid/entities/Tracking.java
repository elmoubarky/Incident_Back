package org.unhcr.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Tracking implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@CsvBindByPosition(position = 0)
	private int idTracking;
	@CsvBindByPosition(position = 1)
	private String reference;
	@CsvBindByPosition(position = 2)
	private String destination;
	@CsvBindByPosition(position = 3)
	private String heureDepart;
	@CsvBindByPosition(position = 4)
	private String type;
	@CsvBindByPosition(position = 5)
	private String qualiteSignal;
	@CsvBindByPosition(position = 6)
	private String heureArrive;
	@CsvBindByPosition(position = 7)
	private String dureeTrajet;
	@CsvBindByPosition(position = 8)
	private String userName;
	@CsvBindByPosition(position = 9)
	private String dateEnreg;

}
