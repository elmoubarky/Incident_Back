package org.unhcr.sid.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unhcr.sid.entities.Tracking;
import org.unhcr.sid.services.TrackingService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.Data;

@CrossOrigin("*")
@RestController
public class TrackingController {

	@Autowired
	private TrackingService trackingService;

	// methode permettant de creer et retourner un objet de type suivi materiel
	@PostMapping("/createTracking")
	public Tracking createTracking(@RequestBody TrackingForm trackingForm) {
		return trackingService.saveTracking(trackingForm.getReference(), trackingForm.getDestination(),
				trackingForm.getHeureDepart(), trackingForm.getType(), trackingForm.getQualiteSignal(),
				trackingForm.getHeureArrive(), trackingForm.getDureeTrajet(), trackingForm.getUserName());
	}

	// methode permettant de modifier la tache lors de la resolution elle retourne
	// un objet de type Cartouche
	@PostMapping("/updateTracking")
	public Tracking updateTracking(@RequestBody TrackingUpdateForm trackingUpdateForm) {
		System.out.println("---trackingUpdateForm.getIdTracking() "+trackingUpdateForm.getIdTracking());
		return trackingService.updateTracking(trackingUpdateForm.getIdTracking(), trackingUpdateForm.getReference(),
				trackingUpdateForm.getDestination(), trackingUpdateForm.getHeureDepart(), trackingUpdateForm.getType(),
				trackingUpdateForm.getQualiteSignal(), trackingUpdateForm.getHeureArrive(),
				trackingUpdateForm.getHeureArrive(), trackingUpdateForm.getUserName());
	}
	
	@GetMapping(path="/TrackingById/{id}")
	public Tracking getTrack(@PathVariable("id") int id) {
		Tracking op = trackingService.loadTrackingById(id);
		System.out.println("Operation trouve "+op);
		return op;
	}
	
	//methode pour extraction des information sur le suivi
		@GetMapping("/listTracking")
		public List<Tracking> list(){
			System.out.println("liste des tracking "+trackingService.listTracking());
			return trackingService.listTracking();
		}
		
		@PostMapping(path="/rechTracking")
		public List<Tracking> rechTracking(@RequestBody TrackingSearch trackingSearch){
			return trackingService.RechTracking(trackingSearch.getOptionValue(),
					trackingSearch.getSearchValue());
		}

	
	//methode pour extraction des information sur le suivi
	@GetMapping("/exportSuiviMaterielCsv")
	public void exportCSV(HttpServletResponse response) throws Exception{
		//declarer le nom du fichier qui sera compose de la date du jour concatene a suivi_incident
		SimpleDateFormat sdf = new SimpleDateFormat();
		String datejour = sdf.format(new Date());
		String filename = "suivi_incident_"+datejour;
		
		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\""+filename+"\"");
		
		
		//create a csv writer
		StatefulBeanToCsv<Tracking> writer = new StatefulBeanToCsvBuilder<Tracking>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
		
		//write all users to csv file
        writer.write(trackingService.listTracking());
				
	}
	
	
	}
@Data
class TrackingSearch {
	private String optionValue;
	private String searchValue;
}

	@Data
	class TrackingForm {
		private String reference;
		private String destination;
		private String heureDepart;
		private String type;
		private String qualiteSignal;
		private String heureArrive;
		private String dateEnreg;
		private String dureeTrajet;
		private String userName;
	}

@Data
class TrackingUpdateForm {
	private int idTracking;
	private String reference;
	private String destination;
	private String heureDepart;
	private String type;
	private String qualiteSignal;
	private String heureArrive;
	private String dateEnreg;
	private String dureeTrajet;
	private String userName;
}
