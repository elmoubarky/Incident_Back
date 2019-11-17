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
import org.unhcr.sid.entities.OperationAchat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OperationRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private OperationAchatRepository operationAchatRepository;
    
    @Test
    public void whenFindByType_thenReturnOperation() {
    	
    	// given
    	OperationAchat oper = new OperationAchat();
    	oper.setDateEnreg("14-11-2019");
    	oper.setMontant("100000");
    	oper.setPoNb("po 1234");
    	oper.setReqId("req 1234");
    	oper.setType("type 1");
    	operationAchatRepository.save(oper);
    	
    	// when
    	OperationAchat found = operationAchatRepository.findByType(oper.getType());
    	
    	// then
    	assertThat(found.getType())
    	         .isEqualTo(oper.getType());	
    }
    
    @Test
    public void whenFindByReqId_thenReturnOperation() {
    	
    	// given
    	OperationAchat oper = new OperationAchat();
    	oper.setDateEnreg("15-11-2019");
    	oper.setMontant("100000");
    	oper.setPoNb("po 1235");
    	oper.setReqId("req 1235");
    	oper.setType("type 3");
    	operationAchatRepository.save(oper);
    	
    	// when
    	OperationAchat found = operationAchatRepository.findByReqId(oper.getReqId());
    	
    	// then
    	assertThat(found.getReqId())
    	         .isEqualTo(oper.getReqId());	
    }
    
    @Test
    public void whenFindByPoNb_thenReturnOperation() {
    	
    	// given
    	OperationAchat oper = new OperationAchat();
    	oper.setDateEnreg("16-11-2019");
    	oper.setMontant("100000");
    	oper.setPoNb("po 1236");
    	oper.setReqId("req 1236");
    	oper.setType("type 4");
    	operationAchatRepository.save(oper);
    	
    	// when
    	OperationAchat found = operationAchatRepository.findByPoNb(oper.getPoNb());
    	
    	// then
    	assertThat(found.getPoNb())
    	         .isEqualTo(oper.getPoNb());	
    }
    
    
    @Test
    public void whenFindByTypeContains_thenReturnListOperations() {
    	//given
    	int size = 2;
    	
    	//when
    	List<OperationAchat> list = operationAchatRepository.findByTypeContains("type 2");
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenFindByPoNbContains_thenReturnListOperations() {
    	//given
    	int size = 2;
    	
    	//when
    	List<OperationAchat> list = operationAchatRepository.findByPoNbContains("po 234");
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    @Test
    public void whenFindByReqIdContains_thenReturnListOperations() {
    	//given
    	int size = 2;
    	
    	//when
    	List<OperationAchat> list = operationAchatRepository.findByReqIdContains("req 00334");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    
    @Test
    public void whenFindOperByDate_thenReturnListOperations() {
    	//given
    	int size = 2;
    	
    	//when
    	List<OperationAchat> list = operationAchatRepository.findOperByDate("2019-10-23", "2019-11-02");
    	System.out.println("liste size "+list.size());
    	
    	//then
    	assertThat(list.size()).isEqualTo(size);
    }
    

}
