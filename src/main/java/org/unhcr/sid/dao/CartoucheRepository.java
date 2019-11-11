package org.unhcr.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.unhcr.sid.entities.Cartouche;

@CrossOrigin("*")
@RepositoryRestResource
public interface CartoucheRepository extends JpaRepository<Cartouche, Integer>{
	
	public Cartouche findByType(String type);
	public Cartouche findByIdCartouche(int id);
	public Cartouche findByDateAppro(String dateAppro);
	public Cartouche findByTypeImprim(String typeImpr);
	@RestResource(path = "/cartouchesByType")
    public List<Cartouche> findByTypeContains(@Param("mc") String mc);
	@RestResource(path = "/cartouchesByDateAppro")
    public List<Cartouche> findByDateApproContains(@Param("mc") String mc);
	@RestResource(path = "/cartouchesByTypeImprim")
    public List<Cartouche> findByTypeImprimContains(@Param("mc") String mc);

}
