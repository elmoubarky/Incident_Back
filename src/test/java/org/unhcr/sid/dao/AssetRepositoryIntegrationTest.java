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
import org.unhcr.sid.entities.Asset;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AssetRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private AssetRepository assetRepository;
    
    @Test
    public void whenFindBySerial_thenReturnAsset() {
    	
    	// given
    	Asset a = new Asset();
    	a.setBc("bc 003");
    	a.setBureau("Monaco");
    	a.setSerialId("CH 012346");
    	a.setModele("Modele 1");
    	a.setType("Type 1");
    	a.setCustodian("kris");
    	a.setDateAchat("11-12-2019");
    	a.setObservation("ras");
    	//entityManager.persist(a);
    	//entityManager.flush();
    	assetRepository.save(a);
    	
    	// when
    	Asset found = assetRepository.findBySerialId(a.getSerialId());
    	
    	// then
    	assertThat(found.getSerialId())
    	         .isEqualTo(a.getSerialId());	
    }
    
    @Test
    public void whenFindByBc_thenReturnAsset() {
    	
    	// given
    	Asset a = new Asset();
    	a.setBc("bc 004");
    	a.setBureau("Paris 2");
    	a.setSerialId("CH 012347");
    	a.setModele("Modele 2");
    	a.setType("Type 2");
    	a.setCustodian("moustapha");
    	a.setDateAchat("11-12-2019");
    	a.setObservation("ras");
    	assetRepository.save(a);
    	
    	// when
    	Asset found = assetRepository.findByBc(a.getBc());
    	
    	// then
    	assertThat(found.getBc())
    	         .isEqualTo(a.getBc());	
    }
    
    @Test
    public void whenFindByCustodian_thenReturnAsset() {
    	
    	// given
    	Asset a = new Asset();
    	a.setBc("bc 005");
    	a.setBureau("Marseille");
    	a.setSerialId("CH 012348");
    	a.setModele("Modele 3");
    	a.setType("Type 3");
    	a.setCustodian("Yapi");
    	a.setDateAchat("11-12-2020");
    	a.setObservation("ras");
    	assetRepository.save(a);
    	
    	// when
    	Asset found = assetRepository.findByCustodian(a.getCustodian());
    	
    	// then
    	assertThat(found.getCustodian())
    	         .isEqualTo(a.getCustodian());	
    }
    
    @Test
    public void whenFindByModele_thenReturnAsset() {
    	
    	// given
    	Asset a = new Asset();
    	a.setBc("bc 006");
    	a.setBureau("Lille");
    	a.setSerialId("CH 012349");
    	a.setModele("Modele 4");
    	a.setType("Type 4");
    	a.setCustodian("Fidele");
    	a.setDateAchat("11-12-2020");
    	a.setObservation("ras");
    	assetRepository.save(a);
    	
    	// when
    	Asset found = assetRepository.findByModele(a.getModele());
    	
    	// then
    	assertThat(found.getModele())
    	         .isEqualTo(a.getModele());	
    }
    
    @Test
    public void whenFindByType_thenReturnAsset() {
    	
    	// given
    	Asset a = new Asset();
    	a.setBc("bc 007");
    	a.setBureau("Chelles");
    	a.setSerialId("CH 0123419");
    	a.setModele("Modele 4");
    	a.setType("Type 7");
    	a.setCustodian("Naomie");
    	a.setDateAchat("11-12-2020");
    	a.setObservation("ras");
    	assetRepository.save(a);
    	
    	// when
    	Asset found = assetRepository.findByType(a.getType());
    	
    	// then
    	assertThat(found.getType())
    	         .isEqualTo(a.getType());	
    }
    
    @Test
    public void whenFindByBureau_thenReturnAsset() {
    	
    	// given
    	Asset a = new Asset();
    	a.setBc("bc 008");
    	a.setBureau("Bastille");
    	a.setSerialId("CH 012350");
    	a.setModele("Modele 5");
    	a.setType("Type 5");
    	a.setCustodian("Naomie");
    	a.setDateAchat("11-12-2020");
    	a.setObservation("ras");
    	assetRepository.save(a);
    	
    	// when
    	Asset found = assetRepository.findByBureau(a.getBureau());
    	
    	// then
    	assertThat(found.getBureau())
    	         .isEqualTo(a.getBureau());	
    }
    
    @Test
    public void whenfindByBureauContains_thenReturnListAssets() {
    	//given
    	int size = 3;
    	
    	//when
    	List<Asset> list = assetRepository.findByBureauContains("Abidjan");
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByModeleContains_thenReturnListAssets() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Asset> list = assetRepository.findByModeleContains("SAMSUNG");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    @Test
    public void whenfindByCustodianContains_thenReturnListAssets() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Asset> list = assetRepository.findByCustodianContains("elisa");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    @Test
    public void whenfindBySerialContains_thenReturnListAssets() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Asset> list = assetRepository.findBySerialIdContains("HCR-ICO-09887");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    @Test
    public void whenfindByBcContains_thenReturnListAssets() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Asset> list = assetRepository.findByBcContains("BC2311");
    	System.out.println("liste bc size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    @Test
    public void whenfindByTypeContains_thenReturnListAssets() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Asset> list = assetRepository.findByTypeContains("NoteBook");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }

}
