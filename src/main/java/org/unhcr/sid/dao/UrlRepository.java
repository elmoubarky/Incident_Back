package org.unhcr.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unhcr.sid.entities.Url;

public interface UrlRepository extends JpaRepository<Url, Integer>{
	public Url findByIdUrl(int id);
	public Url findByName(String name);
	public Url findByPort(String port);

}
