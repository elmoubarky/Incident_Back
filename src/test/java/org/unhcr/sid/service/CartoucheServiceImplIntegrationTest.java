package org.unhcr.sid.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.unhcr.sid.dao.CartoucheRepository;
import org.unhcr.sid.entities.Cartouche;
import org.unhcr.sid.services.CartoucheService;
import org.unhcr.sid.services.impl.CartoucheServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CartoucheServiceImplIntegrationTest {

	@TestConfiguration
	static class CartoucheServiceImplTestContextConfiguration {

		@Bean
		public CartoucheService cartoucheService() {
			return new CartoucheServiceImpl();
		}
	}

	@Autowired
	private CartoucheService cartoucheService;

	@MockBean
	private CartoucheRepository cartoucheRepository;

	@Before
	public void setUp() {
		Cartouche cart = new Cartouche();
		cart.setDateAppro("12-11-2019");
		//cart.setIdCartouche(0);
		cart.setQteInitiale(22);
		cart.setQteRestante(12);
		cart.setStaff("fidele");
		cart.setType("HP");
		cart.setTypeImprim("LASER JET");
		cart.setUserName("franck");
		

		List<Cartouche> list = new ArrayList<>();
		list.add(cart);

		Mockito.when(cartoucheRepository.findByDateAppro(cart.getDateAppro())).thenReturn(cart);

		Mockito.when(cartoucheRepository.findByIdCartouche(cart.getIdCartouche())).thenReturn(cart);

		Mockito.when(cartoucheRepository.findByType(cart.getType())).thenReturn(cart);

		Mockito.when(cartoucheRepository.findByTypeImprim(cart.getTypeImprim())).thenReturn(cart);

		////// list

		Mockito.when(cartoucheRepository.findByDateApproContains(cart.getDateAppro())).thenReturn(list);
		
		Mockito.when(cartoucheRepository.findByTypeContains(cart.getType())).thenReturn(list);
		
		Mockito.when(cartoucheRepository.findByTypeImprimContains(cart.getTypeImprim())).thenReturn(list);

		/////// save
		Mockito.when(cartoucheRepository.save(cart)).thenReturn(cart);

		////// UPDATE
		Mockito.when(cartoucheRepository.saveAndFlush(cart)).thenReturn(cart);

	}

	@Test
	public void whenValidType_thenCartoucheShouldBeFound() {
		String type = "HP";
		Cartouche found = cartoucheService.loadCartoucheByType(type);

		assertThat(found.getType()).isEqualTo(type);
	}

	@Test
	public void whenValidDateAppro_thenCartoucheShouldBeFound() {
		String dateappro = "12-11-2019";
		Cartouche found = cartoucheService.loadCartoucheByDateAppro(dateappro);

		assertThat(found.getDateAppro()).isEqualTo(dateappro);
	}
	
	@Test
	public void whenValidIdCartouche_thenCartoucheShouldBeFound() {
		int id = 0;
		Cartouche found = cartoucheService.loadCartoucheById(id);

		assertThat(found.getIdCartouche()).isEqualTo(id);
	}
	
	@Test
	public void whenValidTypeImprim_thenCartoucheShouldBeFound() {
		String type = "LASER JET";
		Cartouche found = cartoucheService.loadCartoucheByTypeImpr(type);

		assertThat(found.getTypeImprim()).isEqualTo(type);
	}


	
	//////////////////////////////////////////////////////////
	///////////////// LIST////////////////////////////////////
	////////////////////////////////////////////////////////

	@Test
	public void whenValidTypeList_thenCartoucheShouldBeFound() {
		String value = "HP";
		String option = "Type";
		int size = 1;
		List<Cartouche> found = cartoucheService.RechCartouche(option, value);

		assertThat(found.size()).isEqualTo(size);
	}
	
	@Test
	public void whenValidTypeImprimList_thenCartoucheShouldBeFound() {
		String value = "LASER JET";
		String option = "Type Imprim";
		int size = 1;
		List<Cartouche> found = cartoucheService.RechCartouche(option, value);

		assertThat(found.size()).isEqualTo(size);
	}
	
	@Test
	public void whenValidDateApproList_thenCartoucheShouldBeFound() {
		String value = "12-11-2019";
		String option = "Date Appro";
		int size = 1;
		List<Cartouche> found = cartoucheService.RechCartouche(option, value);

		assertThat(found.size()).isEqualTo(size);
	}

	/////////// SAVE

	@Test
	public void whenValidCartoucheSave_thenCartoucheShouldBeFound() {

		Cartouche cart = new Cartouche();
		cart.setDateAppro("12-11-2019");
		//cart.setIdCartouche(0);
		cart.setQteInitiale(22);
		cart.setQteRestante(12);
		cart.setStaff("fidele");
		cart.setType("HP");
		cart.setTypeImprim("LASER JET");
		cart.setUserName("franck");
		
		Cartouche found = cartoucheService.saveCartouche("HP", 22, 12, "12-11-2019", 
				"fidele", "LASER JET",
				"franck");

		assertThat(found).isEqualTo(cart);
	}

///////////UPDATE

	@Test
	public void whenValidCartoucheUpdate_thenCartoucheShouldBeFound() {

		Cartouche cart = new Cartouche();
		cart.setDateAppro("13-11-2019");
		cart.setIdCartouche(0);
		cart.setQteInitiale(22);
		cart.setQteRestante(12);
		cart.setStaff("fidele");
		cart.setType("HP");
		cart.setTypeImprim("LASER JET");
		cart.setUserName("franck");
		

		Cartouche found = cartoucheService.updateCartouche(0, "HP", 22, 12,
				"13-11-2019", "fidele", "LASER JET", "franck");

		assertThat(found).isEqualTo(cart);
	}

}
