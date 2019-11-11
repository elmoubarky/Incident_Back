package org.unhcr.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.unhcr.sid.entities.Tache;

@CrossOrigin("*")
@RepositoryRestResource
public interface TacheRepository extends JpaRepository<Tache, String>{
	
	public Tache findByType(String type);
	public Tache findByNumeroTache(String numero);
	public Tache findByEquipement(String equipement);
	public Tache findByNature(String nature);
	public Tache findByAssigne(String assigne);
	public Tache findByDateCreation(String dateCreation);
	public Tache findByDateCloture(String dateCloture);
	@RestResource(path = "/tachesByNumero")
    public List<Tache> findByNumeroTacheContains(@Param("mc") String mc);
	@RestResource(path = "/tachesByAssigne")
    public List<Tache> findByAssigneContains(@Param("mc") String mc);
	@RestResource(path = "/tachesByStaff")
    public List<Tache> findByStaffContains(@Param("mc") String mc);
	@RestResource(path = "/tachesByType")
    public List<Tache> findByTypeContains(@Param("mc") String mc);
	@RestResource(path = "/tachesByNature")
    public List<Tache> findByNatureContains(@Param("mc") String mc);
	@RestResource(path = "/tachesByNumeroStatus")
    public List<Tache> findByNumeroTacheContainsAndStatusContains(@Param("numero") String numero, @Param("status") String status);
	@RestResource(path = "/tachesByStatus")
	public List<Tache> findByStatusContains(@Param("status") String status);
	@Query("select t from Tache t where t.dateCreation BETWEEN :date1 AND :date2")
	public List<Tache> findTacheByDate(@Param("date1")String date1, @Param("date2") String date2);
	
}
