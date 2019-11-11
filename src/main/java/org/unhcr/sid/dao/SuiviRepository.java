package org.unhcr.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.unhcr.sid.entities.Suivi;

@CrossOrigin("*")
@RepositoryRestResource
public interface SuiviRepository extends JpaRepository<Suivi, Integer>{

}
