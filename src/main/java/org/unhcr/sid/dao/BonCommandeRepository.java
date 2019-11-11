package org.unhcr.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.unhcr.sid.entities.Bon_commande;

@CrossOrigin("*")
@RepositoryRestResource
public interface BonCommandeRepository extends JpaRepository<Bon_commande, Integer>{
	
	public Bon_commande findByNumBc(String numbc);
	@RestResource(path = "/bonCommandeByNumBc")
    public List<Bon_commande> findByNumBcContains(@Param("mc") String mc);

}
