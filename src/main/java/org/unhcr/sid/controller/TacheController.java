package org.unhcr.sid.controller;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unhcr.sid.entities.Tache;
import org.unhcr.sid.helpers.ZXingHelper;
import org.unhcr.sid.services.TacheService;

import lombok.Data;

@CrossOrigin("*")
@RestController
public class TacheController {

	@Autowired
	private TacheService tacheService;
	
	
	@GetMapping("listTaches")
	public List<Tache> lists(){
		return tacheService.listTache();
	}
	
	@GetMapping(path="/TachesByNumero/{numero}")
	public Tache getTrack(@PathVariable("numero") String numero) {
		Tache op = tacheService.loadTacheByNumeroTache(numero);
		System.out.println("Tache trouve "+op);
		return op;
	}
	
	@GetMapping("listTachesStatus")
	public List<Tache> RechTacheStatus(){
		String status = "EN COURS";
		return tacheService.RechTacheStatus(status);
	}
	
	@GetMapping("RechNumeroStatus/{numero}")
	public List<Tache> RechTacheNumeroStatus(@PathVariable("numero") String numero){
		String status = "EN COURS";
		return tacheService.RechTacheNumeroStatus(numero, status);
	}

	// methode permettant de creer et retourner un objet de type Tache
	@PostMapping("/createTache")
	public Tache createTache(@RequestBody TacheForm tacheForm) {
		return tacheService.saveTache(tacheForm.getNature(), tacheForm.getType(), 
				tacheForm.getEquipement(), tacheForm.getPriorite(),
				tacheForm.getAssigne(), tacheForm.getUsername());
	}

	// methode permettant de modifier la tache lors de la resolution elle retourne
	// un objet de type Tache
	@PostMapping("/resolvedTache")
	public Tache resolvedTache(@RequestBody ResolvedForm resolvedForm) {
		System.out.println("-----------resolvedMethode-----------" + resolvedForm.getResolvedMethode());
		System.out.println("-----------resolvedStaff-----------" + resolvedForm.getStaff());
		System.out.println("-----------resolvedNumero-----------" + resolvedForm.getNumeroTache());
		return tacheService.addResolutionToTache(resolvedForm.getNumeroTache(), resolvedForm.getStaff(),
				resolvedForm.getResolvedMethode(), resolvedForm.getUsername());
	}
	
	@PostMapping(path="/rechTache")
	public List<Tache> rechTracking(@RequestBody TacheSearch tacheSearch){
		return tacheService.RechTache(tacheSearch.getOptionValue(),
				tacheSearch.getSearchValue());
	}

	
	// methode permettant de modifier la tache lors de la resolution elle retourne
		// un objet de type Tache
		@PostMapping("/updateTache")
		public Tache updateTache(@RequestBody UpdateTacheForm updateTacheForm) {
			System.out.println("-----------resolvedMethode-----------" + updateTacheForm.getType());
			System.out.println("-----------resolvedStaff-----------" + updateTacheForm.getNature());
			System.out.println("-----------resolvedNumero-----------" + updateTacheForm.getNumeroTache());
			return tacheService.updateTache(updateTacheForm.getNumeroTache(), updateTacheForm.getNature(),
					updateTacheForm.getType(), updateTacheForm.getPriorite(),
					updateTacheForm.getAssigne(), updateTacheForm.getUsername());
		}
		
		@PostMapping(path="/TacheStatsLabel")
		public String totalMontOperAchat(@RequestBody TacheSearchDate tacheSearchDate){
			
			String retour = null;
			String corps = null;
			
			List<Tache> lis = new ArrayList<>();
			String date1 = tacheSearchDate.getDate1();
			String date2 = tacheSearchDate.getDate2();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String annee = null;
			
			if(date1==null & date2==null) {
				retour= "[]";
			}else if(date1==null) {
				date1=date2;
			}else if(date2==null){
				date2=date1;
			}
			
			lis = tacheService.getAllTacheByDate(date1, date2);
			System.out.println("Liste getAllTacheByDate "+lis);
			int taille = lis.size();
			System.out.println("Liste taille "+taille);
			Tache tache = new Tache();
			for(int i=0; i<taille; i++) {
				tache = lis.get(i);
				annee = sdf.format(tache.getDateCreation());
				System.out.println(" annee recuperee "+annee);
				
				
			}
			
			
			
			
			return retour;
			
			
		}
		
		

	// generer le qrcode pour la tache
	@GetMapping("taches/qrcode/{numero}")
	public void qrcode(@PathVariable("numero") String numero, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getQRCodeImage(numero, 200, 200));
		outputStream.flush();
		outputStream.close();
		
	}
	@GetMapping(path="/tachesQrcode/{numero}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("numero") String numero) throws Exception{
        Tache t=tacheService.loadTacheByNumeroTache(numero);
        System.out.println("tache t "+t);
        File file = new File(System.getProperty("user.home")+"/unhcr/qrcode/");
        if(!file.exists()) {
        	if(file.mkdirs()) {
        		System.out.println("repertoires creer");
        	}else {
        		System.out.println("erreur creation");
        	}
        }
        
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/unhcr/qrcode/"+numero+".png"));
    }

}
@Data
class TacheSearch {
	private String optionValue;
	private String searchValue;
}

@Data
class TacheForm {
	private String type;
	private String nature;
	private String equipement;
	private String priorite;
	private String assigne;
	private String username;
}

@Data
class ResolvedForm {
	private String numeroTache;
	private String staff;
	private String resolvedMethode;
	private String username;

}

@Data
class TacheSearchDate {
	private String date1;
	private String date2;
}

@Data
class UpdateTacheForm {
	private String numeroTache;
	private String type;
	private String nature;
	private String priorite;
	private String assigne;
	private String username;

}
