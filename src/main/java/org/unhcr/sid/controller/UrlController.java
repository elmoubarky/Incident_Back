package org.unhcr.sid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unhcr.sid.entities.Url;
import org.unhcr.sid.services.UrlService;

import lombok.Data;;

@CrossOrigin("*")
@RestController
public class UrlController {
	
	@Autowired
	private UrlService urlService;
	
	// methode permettant de creer et retourner un objet de type suivi materiel
		@PostMapping("/createUrl")
		public Url createUrl(@RequestBody UrlForm urlForm) {
			return urlService.saveUrl(urlForm.getName(), urlForm.getPort(), urlForm.getUrl(),
					urlForm.getDescription());
			
		}
		
		// methode permettant de modifier la tache lors de la resolution elle retourne
		// un objet de type Cartouche
		@PostMapping("/updateUrl")
		public Url updateUrl(@RequestBody UrlUpdateForm urlUpdateForm) {
			System.out.println("---urlUpdateForm.getIdUrl() "+urlUpdateForm.getIdUrl());
			return urlService.updateUrl(urlUpdateForm.getIdUrl(), urlUpdateForm.getName(),
					urlUpdateForm.getPort(), urlUpdateForm.getUrl(), urlUpdateForm.getDescription());
		}
		
		@GetMapping(path="/UrlById/{id}")
		public Url getUrl(@PathVariable("id") int id) {
			Url op = urlService.loadUrlById(id);
			System.out.println("Url trouve "+op);
			return op;
		}
		
		@GetMapping(path="/UrlByName/{name}")
		public Url getUrlName(@PathVariable("name") String name) {
			Url op = urlService.loadUrlByName(name);
			System.out.println("Url trouve "+op);
			return op;
		}
		
		//methode pour extraction des information sur le suivi
				@GetMapping("/listUrl")
				public List<Url> list(){
					System.out.println("liste des Urls "+urlService.getAll());
					return urlService.getAll();
				}
		
}

@Data
class UrlForm {
	private String name;
	private String url;
	private String port;
	private String description;
}

@Data
class UrlUpdateForm {
	private int idUrl;
	private String name;
	private String url;
	private String port;
	private String description;
}
