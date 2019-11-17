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
import org.unhcr.sid.entities.Tracking;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TrackingRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private TrackingRepository trackingRepository;
    
    @Test
    public void whenFindByReference_thenReturnTracking() {
    	
    	// given
    	Tracking track = new Tracking();
    	track.setDestination("Abidjan");
    	track.setDureeTrajet("02:00");
    	track.setHeureArrive("20:21:12");
    	track.setHeureDepart("18:21:12");
    	track.setQualiteSignal("Radio");
    	track.setReference("ref 003");
    	track.setType("type 3");
    	track.setDateEnreg("11-11-2019 12:11:934");
    	
    	// when
    	Tracking found = trackingRepository.save(track);
    	
    	// then
    	assertThat(found.getReference())
    	         .isEqualTo(track.getReference());	
    }
    
    @Test
    public void whenFindByType_thenReturnTracking() {
    	
    	// given
    	Tracking track = new Tracking();
    	track.setDestination("Mali");
    	track.setDureeTrajet("03:00");
    	track.setHeureArrive("22:21:12");
    	track.setHeureDepart("19:21:12");
    	track.setQualiteSignal("Radio");
    	track.setReference("ref 004");
    	track.setType("type 4");
    	track.setDateEnreg("12-11-2019 12:11:934");
    	
    	// when
    	Tracking found = trackingRepository.save(track);
    	
    	// then
    	assertThat(found.getType())
    	         .isEqualTo(track.getType());	
    }
    
    @Test
    public void whenFindByDateEnreg_thenReturnTracking() {
    	
    	// given
    	Tracking track = new Tracking();
    	track.setDestination("Kaire");
    	track.setDureeTrajet("06:00");
    	track.setHeureArrive("23:21:12");
    	track.setHeureDepart("17:21:12");
    	track.setQualiteSignal("Radio");
    	track.setReference("ref 005");
    	track.setType("type 5");
    	track.setDateEnreg("13-11-2019 12:11:934");
    	
    	// when
    	Tracking found = trackingRepository.save(track);
    	
    	// then
    	assertThat(found.getDateEnreg())
    	         .isEqualTo(track.getDateEnreg());	
    }
    
   
    @Test
    public void whenfindByTypeContains_thenReturnListTrackings() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Tracking> list = trackingRepository.findByTypeContains("type 2");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByReferenceContains_thenReturnListTrackings() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Tracking> list = trackingRepository.findByReferenceContains("ref 002");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenfindByDateEnregContains_thenReturnListTrackings() {
    	//given
    	int size = 1;
    	
    	//when
    	List<Tracking> list = trackingRepository.findByDateEnregContains("10-11-2019 12:11:934");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }

}
