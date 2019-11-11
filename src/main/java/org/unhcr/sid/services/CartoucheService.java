package org.unhcr.sid.services;

import java.util.List;

import org.unhcr.sid.entities.Cartouche;

public interface CartoucheService {
	
	public Cartouche saveCartouche(String type, int qteInitiale, int qteRestante, String dateAppro, 
			String staff, String typeImprim, String username);
	public Cartouche loadCartoucheByType(String type);
	public Cartouche loadCartoucheById(int id);
	public Cartouche loadCartoucheByTypeImpr(String typeImprim);
	public Cartouche loadCartoucheByDateAppro(String dateAppro);
	public Cartouche updateCartouche(int idCartouche, String type, int qteInitiale, int qteRestante, String dateAppro, 
			String staff, String typeImprim, String username);
	public List<Cartouche> listCartouche();
	public List<Cartouche> RechCartouche(String value, String search);

}
