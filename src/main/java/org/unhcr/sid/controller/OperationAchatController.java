package org.unhcr.sid.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.unhcr.sid.entities.OperationAchat;
import org.unhcr.sid.services.OperationAchatService;

import lombok.Data;;

@CrossOrigin("*")
@RestController
public class OperationAchatController {

	@Autowired
	private OperationAchatService operationAchatService;
	
	@GetMapping("/listOperationAchats")
	public List<OperationAchat> listOpers(){
		return operationAchatService.getAllOpers();
	}

	// methode permettant de creer et retourner un objet de type Cartouche
	@PostMapping("/createOperationAchat")
	public OperationAchat createOperationAchat(@RequestBody OperationForm operationForm) {
		return operationAchatService.saveOperatonAchat(operationForm.getType(), operationForm.getReqId(),
				operationForm.getPoNb(), operationForm.getMontant(), operationForm.getDetail(),
				operationForm.getStatut(), operationForm.getUsername());
	}

	// methode permettant de modifier la tache lors de la resolution elle retourne
	// un objet de type Cartouche
	@PostMapping("/updateOperationAchat")
	public OperationAchat updateOperationAchat(@RequestBody OperationUpdateForm operationUpdateForm) {
		return operationAchatService.updateOperatonAchat(operationUpdateForm.getIdOperation(),
				operationUpdateForm.getType(), operationUpdateForm.getReqId(), operationUpdateForm.getPoNb(),
				operationUpdateForm.getMontant(), operationUpdateForm.getDetail(), operationUpdateForm.getStatut()
				, operationUpdateForm.getUsername());
	}
	
	@GetMapping(path="/OperAchatById/{id}")
	public OperationAchat getOper(@PathVariable("id") int id) {
		OperationAchat op = operationAchatService.loadOperatonAchatById(id);
		System.out.println("Operation trouve "+op);
		return op;
	}
	
	@GetMapping(path="/OperAttachement/{id}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getAttachement(@PathVariable("id") int id) throws Exception{
        OperationAchat op=operationAchatService.loadOperatonAchatById(id);
        System.out.println("id "+id);
        System.out.println("OperationAchat a "+op);
        File file = new File(System.getProperty("user.home")+"/unhcr/operation/achat/");
        if(!file.exists()) {
        	if(file.mkdirs()) {
        		System.out.println("repertoires creer");
        	}else {
        		System.out.println("erreur creation");
        	}
        }
       
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/unhcr/operation/achat/"+op.getAttachement()));
    }
	
	@PostMapping(path="/OperAchatMontant")
	public String totalMontOperAchat(@RequestBody OperationSearchDate operationSearchDate){
		int montant = 0;
		
		List<OperationAchat> lis = new ArrayList<>();
		String date1 = operationSearchDate.getDate1();
		String date2 = operationSearchDate.getDate2();
		
		if(date1==null & date2==null) {
			montant= 0;
		}else if(date1==null) {
			date1=date2;
		}else if(date2==null){
			date2=date1;
		}
		
		lis = operationAchatService.getAllOpersByDate(date1, date2);
		System.out.println("Liste getAllOpersByDate "+lis);
		int taille = lis.size();
		System.out.println("Liste taille "+taille);
		OperationAchat op = new OperationAchat();
		for(int i=0; i<taille; i++) {
			op = lis.get(i);
			System.out.println("Operation AChat "+op);
			String mont = op.getMontant();
			System.out.println("Montant recupere "+mont);
			montant = montant+Integer.valueOf(mont);
			System.out.println("Montant calcule "+montant);
		}
		
		return ""+montant;
	}
	
	@PostMapping(path="/rechOperAchat")
	public List<OperationAchat> rechOperAchat(@RequestBody OperationSearch operationSearch){
		return operationAchatService.RechOpers(operationSearch.getOptionValue(),
				operationSearch.getSearchValue());
	}
	
	@PostMapping(path="/rechOperAchatDate")
	public List<OperationAchat> rechOperAchatDate(@RequestBody OperationSearchDate operationSearchDate){
		return operationAchatService.getAllOpersByDate(operationSearchDate.getDate1(),
				operationSearchDate.getDate2());	
	}
	
	
	@PostMapping(path="/uploadAttachement/{id}")
	public void uploadOper(MultipartFile file, @PathVariable int id) throws Exception{
		OperationAchat oper = operationAchatService.loadOperatonAchatById(id);
		//decoder le qrcode
		System.out.println("nom du fichier "+file.getOriginalFilename());
		oper.setAttachement(file.getOriginalFilename());
		
		File fil = new File(System.getProperty("user.home")+"/unhcr/operation/achat/");
        if(!fil.exists()) {
        	if(fil.mkdirs()) {
        		System.out.println("repertoires creer");
        	}else {
        		System.out.println("erreur creation");
        	}
        }
		Files.write(Paths.get(System.getProperty("user.home")+"/unhcr/operation/achat/"+file.getOriginalFilename()),
				file.getBytes());
		operationAchatService.updateOperationAttachement(id, file.getOriginalFilename());
		
	}

}
@Data
class OperationSearch {
	private String optionValue;
	private String searchValue;
}

@Data
class OperationSearchDate {
	private String date1;
	private String date2;
}


@Data
class OperationForm {
	private String type;
	private String reqId;
	private String poNb;
	private String montant;
	private String detail;
	private String statut;
	//private String attachement;
	private String username;
}

@Data
class OperationUpdateForm {
	private int idOperation;
	private String type;
	private String reqId;
	private String poNb;
	private String montant;
	private String detail;
	private String statut;
	//private String attachement;
	private String username;
}
