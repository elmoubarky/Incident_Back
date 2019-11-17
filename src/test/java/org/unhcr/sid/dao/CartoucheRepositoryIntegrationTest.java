package org.unhcr.sid.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.unhcr.sid.entities.Cartouche;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CartoucheRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private CartoucheRepository cartoucheRepository;
    
    @Test
    public void whenFindByType_thenReturnCartouche() {
    	
    	// given
    	Cartouche cart = new Cartouche();
    	cart.setType("DELL");
    	cart.setDateAppro("10-10-2019");
    	cart.setQteRestante(10);
    	cart.setQteInitiale(25);
    	cart.setStaff("kris");
    	cart.setTypeImprim("UT 345");
    	cart.setUserName("fidele");
    	//entityManager.persist(a);
    	//entityManager.flush();
    	cartoucheRepository.save(cart);
    	
    	// when
    	Cartouche found = cartoucheRepository.findByType(cart.getType());
    	
    	// then
    	assertThat(found.getType())
    	         .isEqualTo(cart.getType());	
    }
    
    @Test
    public void whenFindByDateAppro_thenReturnCartouche() {
    	
    	// given
    	Cartouche cart = new Cartouche();
    	cart.setType("TOSHIBA");
    	cart.setDateAppro("11-10-2019");
    	cart.setQteRestante(10);
    	cart.setQteInitiale(25);
    	cart.setStaff("kris");
    	cart.setTypeImprim("TT 345");
    	cart.setUserName("fidele");
    	//entityManager.persist(a);
    	//entityManager.flush();
    	cartoucheRepository.save(cart);
    	
    	// when
    	Cartouche found = cartoucheRepository.findByDateAppro(cart.getDateAppro());
    	
    	// then
    	assertThat(found.getDateAppro())
    	         .isEqualTo(cart.getDateAppro());	
    }
    
    @Test
    public void whenFindByTypeImprim_thenReturnCartouche() {
    	
    	// given
    	Cartouche cart = new Cartouche();
    	cart.setType("TOSHIBA");
    	cart.setDateAppro("12-10-2019");
    	cart.setQteRestante(10);
    	cart.setQteInitiale(25);
    	cart.setStaff("kris");
    	cart.setTypeImprim("FF 345");
    	cart.setUserName("fidele");
    	//entityManager.persist(a);
    	//entityManager.flush();
    	cartoucheRepository.save(cart);
    	
    	// when
    	Cartouche found = cartoucheRepository.findByTypeImprim(cart.getTypeImprim());
    	
    	// then
    	assertThat(found.getTypeImprim())
    	         .isEqualTo(cart.getTypeImprim());
    }
    
    
    @Test
    public void whenFindByTypeContains_thenReturnListCartouches() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Cartouche> list = cartoucheRepository.findByTypeContains("HP");
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenFindByTypeImprimContains_thenReturnListCartouches() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Cartouche> list = cartoucheRepository.findByTypeImprimContains("LASER JET");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    @Test
    public void whenFindByDateApproContains_thenReturnListCartouches() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Cartouche> list = cartoucheRepository.findByDateApproContains("11-11-2019");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    

}
