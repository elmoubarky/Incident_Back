package org.unhcr.sid.services;

import java.util.List;

import org.unhcr.sid.entities.Url;

public interface UrlService {
	
	public Url saveUrl(String name, String port, String url, String description);
	public Url loadUrlByName(String name);
	public Url loadUrlById(int id);
	public Url loadUrlByPort(String port);
	public Url updateUrl(int idUrl, String name, String port, String url, String description);
	public List<Url> getAll();

}
