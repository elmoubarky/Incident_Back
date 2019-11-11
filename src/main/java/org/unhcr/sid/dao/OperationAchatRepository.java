package org.unhcr.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.unhcr.sid.entities.OperationAchat;

@CrossOrigin("*")
@RepositoryRestResource
public interface OperationAchatRepository extends JpaRepository<OperationAchat, Integer>{
	
	public OperationAchat findByType(String type);
	public OperationAchat findByIdOperation(int id);
	public OperationAchat findByReqId(String reqId);
	public OperationAchat findByPoNb(String typeImpr);
	@RestResource(path = "/operationsByType")
    public List<OperationAchat> findByTypeContains(@Param("mc") String mc);
	@RestResource(path = "/operationsByReqId")
    public List<OperationAchat> findByReqIdContains(@Param("mc") String mc);
	@RestResource(path = "/operationsByPoNb")
    public List<OperationAchat> findByPoNbContains(@Param("mc") String mc);
	@RestResource(path = "/operationsByDateBetween")
    public List<OperationAchat> findByDateEnregBetween(String date1, String date2);
	
	@Query("select o from OperationAchat o where o.dateEnreg BETWEEN :date1 AND :date2")
	public List<OperationAchat> findOperByDate(@Param("date1")String date1, @Param("date2") String date2);

}
