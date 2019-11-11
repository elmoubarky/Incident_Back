package org.unhcr.sid.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unhcr.sid.dao.CartoucheRepository;
import org.unhcr.sid.entities.Cartouche;
import org.unhcr.sid.services.CartoucheService;

@Service
@Transactional
public class CartoucheServiceImpl implements CartoucheService{
	
	@Autowired
	private CartoucheRepository cartoucheRepository;

	@Override
	public Cartouche saveCartouche(String type, int qteInitiale, int qteRestante, String dateAppro, String staff,
			String typeImprim, String username) {
		
		Cartouche cart  = new Cartouche();
		cart.setDateAppro(dateAppro);
		cart.setQteInitiale(qteInitiale);
		cart.setQteRestante(qteRestante);
		cart.setStaff(staff);
		cart.setType(type);
		cart.setTypeImprim(typeImprim);
		cart.setUserName(username);
		cartoucheRepository.save(cart);
		return cart;
	}

	@Override
	public Cartouche loadCartoucheByType(String type) {
		// TODO Auto-generated method stub
		return cartoucheRepository.findByType(type);
	}

	@Override
	public Cartouche loadCartoucheByTypeImpr(String typeImprim) {
		// TODO Auto-generated method stub
		return cartoucheRepository.findByTypeImprim(typeImprim);
	}

	@Override
	public Cartouche loadCartoucheByDateAppro(String dateAppro) {
		// TODO Auto-generated method stub
		return cartoucheRepository.findByDateAppro(dateAppro);
	}

	@Override
	public Cartouche updateCartouche(int idCartouche, String type, int qteInitiale, int qteRestante, String dateAppro,
			String staff, String typeImprim, String username) {
		// TODO Auto-generated method stub
		Cartouche cart = cartoucheRepository.findByIdCartouche(idCartouche);
		if(cart==null)throw new RuntimeException("Cartouche not exists");
		cart.setDateAppro(dateAppro);
		cart.setQteInitiale(qteInitiale);
		cart.setQteRestante(qteRestante);
		cart.setStaff(staff);
		cart.setType(type);
		cart.setTypeImprim(typeImprim);
		cart.setUserName(username);
		cartoucheRepository.saveAndFlush(cart);
		return cart;
	}

	@Override
	public Cartouche loadCartoucheById(int id) {
		// TODO Auto-generated method stub
		return cartoucheRepository.findByIdCartouche(id);
	}

	@Override
	public List<Cartouche> listCartouche() {
		// TODO Auto-generated method stub
		return cartoucheRepository.findAll();
	}

	@Override
	public List<Cartouche> RechCartouche(String option, String search) {
		// TODO Auto-generated method stub
		List<Cartouche> lis = new ArrayList<>();
		if(option==null) throw new RuntimeException("Value search are null"); 
		if(option.equals("Type")) {
			lis = cartoucheRepository.findByTypeContains(search);
			System.out.println("retour findByTypeContains "+lis);
		}else if(option.equals("Date Appro")) {
			lis = cartoucheRepository.findByDateApproContains(search);
			System.out.println("retour findByDateApproContains "+lis);
		}else if(option.equals("Type Imprim")) {
			lis = cartoucheRepository.findByTypeImprimContains(search);
			System.out.println("retour findByTypeImprimContains "+lis);
		}
		return lis;
	}

}
