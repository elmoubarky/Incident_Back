package org.unhcr.sid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unhcr.sid.entities.Cartouche;
import org.unhcr.sid.services.CartoucheService;

import lombok.Data;;

@CrossOrigin("*")
@RestController
public class CartoucheController {
	
	@Autowired
	private CartoucheService cartoucheService;
	
	// methode permettant de creer et retourner un objet de type Cartouche
		@PostMapping("/createCartouche")
		public Cartouche createCartouche(@RequestBody CartoucheForm cartoucheForm) {
			return cartoucheService.saveCartouche(cartoucheForm.getType(), cartoucheForm.getQteInitiale(),
					cartoucheForm.getQteRestante(), cartoucheForm.getDateAppro(), cartoucheForm.getStaff(),
					cartoucheForm.getTypeImprim(), cartoucheForm.getUsername());
		}
		
		// methode permettant de modifier la tache lors de la resolution elle retourne
				// un objet de type Cartouche
				@PostMapping("/updateCartouche")
				public Cartouche updateCartouche(@RequestBody CartoucheUpdateForm updateCartoucheForm) {
					return cartoucheService.updateCartouche(updateCartoucheForm.getIdCartouche(), 
							updateCartoucheForm.getType(), updateCartoucheForm.getQteInitiale(),
							updateCartoucheForm.getQteRestante(), updateCartoucheForm.getDateAppro(),
							updateCartoucheForm.getStaff(), updateCartoucheForm.getTypeImprim(),
							updateCartoucheForm.getUsername());
				}
				
				@GetMapping(path="/CartoucheById/{id}")
				public Cartouche getTrack(@PathVariable("id") int id) {
					Cartouche op = cartoucheService.loadCartoucheById(id);
					System.out.println("Operation trouve "+op);
					return op;
				}
				
				@PostMapping(path="/rechCartouche")
				public List<Cartouche> rechTracking(@RequestBody CartoucheSearch cartoucheSearch){
					return cartoucheService.RechCartouche(cartoucheSearch.getOptionValue(),
							cartoucheSearch.getSearchValue());
				}
				
				//methode pour extraction des information sur le suivi
				@GetMapping("/listCartouche")
				public List<Cartouche> list(){
					System.out.println("liste des tracking "+cartoucheService.listCartouche());
					return cartoucheService.listCartouche();
				}

}

@Data
class CartoucheSearch {
	private String optionValue;
	private String searchValue;
}

@Data
class CartoucheForm {
	private String type;
	private int qteInitiale;
	private int qteRestante;
	private String dateAppro;
	private String staff;
	private String typeImprim;
	private String username;
}

@Data
class CartoucheUpdateForm {
	private int idCartouche;
	private String type;
	private int qteInitiale;
	private int qteRestante;
	private String dateAppro;
	private String staff;
	private String typeImprim;
	private String username;
}
