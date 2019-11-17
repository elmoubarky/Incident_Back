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
import org.unhcr.sid.entities.Tache;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TacheRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private TacheRepository tacheRepository;
    
    @Test
    public void whenFindByNumeroTache_thenReturnTache() {
    	
    	// given
    	Tache tache = new Tache();
    	tache.setAssigne("elisa");
    	tache.setDateCreation("2019-12-01 18:11:133");
    	tache.setEquipement("HCR-ICO-ZERTTT11");
    	tache.setNature("reparation");
    	tache.setNumeroTache("HCR-ICO-20191110124777");
    	tache.setPriorite("MAJEUR");
    	tache.setStatus("EN COURS");
    	tache.setType("stockage");
    	tache.setUsername("moustapha");
    	tacheRepository.save(tache);
    	
    	// when
    	Tache found = tacheRepository.findByNumeroTache(tache.getNumeroTache());
    	
    	// then
    	assertThat(found.getNumeroTache())
    	         .isEqualTo(tache.getNumeroTache());	
    }
    
    @Test
    public void whenFindByType_thenReturnTache() {
    	
    	// given
    	Tache tache = new Tache();
    	tache.setAssigne("franck");
    	tache.setDateCreation("2019-12-01 18:11:134");
    	tache.setEquipement("HCR-ICO-ZERTTT12");
    	tache.setNature("inventaire");
    	tache.setNumeroTache("HCR-ICO-20191110124778");
    	tache.setPriorite("MAJEUR");
    	tache.setStatus("EN COURS");
    	tache.setType("maintien");
    	tache.setUsername("moustapha");
    	tacheRepository.save(tache);
    	
    	// when
    	Tache found = tacheRepository.findByType(tache.getType());
    	
    	// then
    	assertThat(found.getType())
    	         .isEqualTo(tache.getType());	
    }
    
    @Test
    public void whenFindByEquipement_thenReturnTache() {
    	
    	// given
    	Tache tache = new Tache();
    	tache.setAssigne("estou");
    	tache.setDateCreation("2019-12-01 18:11:100");
    	tache.setEquipement("HCR-ICO-ZERTTT123");
    	tache.setNature("test");
    	tache.setNumeroTache("HCR-ICO-20191110124008");
    	tache.setPriorite("MAJEUR");
    	tache.setStatus("EN COURS");
    	tache.setType("course");
    	tache.setUsername("tiger");
    	tacheRepository.save(tache);
    	
    	// when
    	Tache found = tacheRepository.findByEquipement(tache.getEquipement());
    	
    	// then
    	assertThat(found.getEquipement())
    	         .isEqualTo(tache.getEquipement());	
    }
    
    @Test
    public void whenFindByNature_thenReturnTache() {
    	
    	// given
    	Tache tache = new Tache();
    	tache.setAssigne("estou");
    	tache.setDateCreation("2019-12-01 18:21:100");
    	tache.setEquipement("HCR-ICO-ZERTTT523");
    	tache.setNature("implementation");
    	tache.setNumeroTache("HCR-ICO-20191110824008");
    	tache.setPriorite("MAJEUR");
    	tache.setStatus("EN COURS");
    	tache.setType("type 44");
    	tache.setUsername("pouamon");
    	tacheRepository.save(tache);
    	
    	// when
    	Tache found = tacheRepository.findByNature(tache.getNature());
    	
    	// then
    	assertThat(found.getNature())
    	         .isEqualTo(tache.getNature());	
    }
    
    @Test
    public void whenFindByAssigne_thenReturnTache() {
    	
    	// given
    	Tache tache = new Tache();
    	tache.setAssigne("adams");
    	tache.setDateCreation("2019-12-01 16:21:100");
    	tache.setEquipement("HCR-ICO-ZERTTT923");
    	tache.setNature("resolution");
    	tache.setNumeroTache("HCR-ICO-20192110824008");
    	tache.setPriorite("MAJEUR");
    	tache.setStatus("EN COURS");
    	tache.setType("type 34");
    	tache.setUsername("edo");
    	tacheRepository.save(tache);
    	
    	// when
    	Tache found = tacheRepository.findByAssigne(tache.getAssigne());
    	
    	// then
    	assertThat(found.getAssigne())
    	         .isEqualTo(tache.getAssigne());	
    }
    
    @Test
    public void whenFindByDateCreation_thenReturnTache() {
    	
    	// given
    	Tache tache = new Tache();
    	tache.setAssigne("ousmane");
    	tache.setDateCreation("2019-12-01 06:21:800");
    	tache.setEquipement("HCR-ICO-ZERTTV923");
    	tache.setNature("resolution");
    	tache.setNumeroTache("HCR-ICO-20192130824008");
    	tache.setPriorite("MAJEUR");
    	tache.setStatus("EN COURS");
    	tache.setType("type 99");
    	tache.setUsername("edo2");
    	tacheRepository.save(tache);
    	
    	// when
    	Tache found = tacheRepository.findByDateCreation(tache.getDateCreation());
    	
    	// then
    	assertThat(found.getDateCreation())
    	         .isEqualTo(tache.getDateCreation());	
    }
    
    @Test
    public void whenFindByDateCloture_thenReturnTache() {
    	
    	// given
    	Tache tache = new Tache();
    	tache.setAssigne("olive");
    	tache.setDateCreation("2019-12-01 09:21:800");
    	tache.setEquipement("HCR-ICO-ZERTV923");
    	tache.setNature("action");
    	tache.setNumeroTache("HCR-ICO-20182130824008");
    	tache.setPriorite("MAJEUR");
    	tache.setStatus("RESOLU");
    	tache.setType("type 99");
    	tache.setUsername("edo2");
    	tache.setDateCloture("2019-12-11 09:21:800");
    	tacheRepository.save(tache);
    	
    	// when
    	Tache found = tacheRepository.findByDateCloture(tache.getDateCloture());
    	
    	// then
    	assertThat(found.getDateCloture())
    	         .isEqualTo(tache.getDateCloture());	
    }
    
   
    @Test
    public void whenfindByTypeContains_thenReturnListTaches() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Tache> list = tacheRepository.findByTypeContains("informatique");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByAssigneContains_thenReturnListTaches() {
    	//given
    	int size = 3;
    	
    	//when
    	List<Tache> list = tacheRepository.findByAssigneContains("kris");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByNumeroTacheContains_thenReturnListTaches() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Tache> list = tacheRepository.findByNumeroTacheContains("HCR-ICO-20191101180457");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByStaffContains_thenReturnListTaches() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Tache> list = tacheRepository.findByStaffContains("djoman");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByNatureContains_thenReturnListTaches() {
    	//given
    	int size = 3;
    	
    	//when
    	List<Tache> list = tacheRepository.findByNatureContains("installation");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByStatusContains_thenReturnListTaches() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Tache> list = tacheRepository.findByStatusContains("RESOLU");
    	System.out.println("liste status size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByNumeroTacheContainsAndStatusContains_thenReturnListTaches() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Tache> list = tacheRepository.findByNumeroTacheContainsAndStatusContains("HCR-ICO-20191101180457", "EN COURS");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByDateContains_thenReturnListTaches() {
    	//given
    	int size = 5;
    	
    	//when
    	List<Tache> list = tacheRepository.findTacheByDate("2019-11-01", "2019-11-11");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }

}
