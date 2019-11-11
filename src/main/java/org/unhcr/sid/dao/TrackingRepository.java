package org.unhcr.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.unhcr.sid.entities.Tracking;

@CrossOrigin("*")
@RepositoryRestResource
public interface TrackingRepository extends JpaRepository<Tracking, Integer>{
	
	public Tracking findByReference(String reference);
	public Tracking findByIdTracking(int id);
	public Tracking findByType(String type);
	public Tracking findByDateEnreg(String dateEnreg);
	@RestResource(path = "/trackingByReference")
    public List<Tracking> findByReferenceContains(@Param("mc") String mc);
	@RestResource(path = "/trackingByDateEnreg")
    public List<Tracking> findByDateEnregContains(@Param("mc") String mc);
	@RestResource(path = "/trackingByType")
    public List<Tracking> findByTypeContains(@Param("mc") String mc);

}
