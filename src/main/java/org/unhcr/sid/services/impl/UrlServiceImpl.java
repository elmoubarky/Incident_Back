package org.unhcr.sid.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unhcr.sid.dao.UrlRepository;
import org.unhcr.sid.entities.Url;
import org.unhcr.sid.services.UrlService;

@Service
@Transactional
public class UrlServiceImpl implements UrlService{
	
	@Autowired
	private UrlRepository urlRepository;

	@Override
	public Url saveUrl(String name, String port, String url, String description) {
		// TODO Auto-generated method stub
		Url u = urlRepository.findByName(name);
		if(u!=null)throw new RuntimeException("Url exists");
		Url ur = new Url();
		ur.setDescription(description);
		ur.setUrl(url);
		ur.setName(name);
		ur.setPort(port);
		urlRepository.save(ur);
		return ur;
	}

	@Override
	public Url loadUrlByName(String name) {
		// TODO Auto-generated method stub
		return urlRepository.findByName(name);
	}

	@Override
	public Url loadUrlById(int id) {
		// TODO Auto-generated method stub
		return urlRepository.findByIdUrl(id);
	}

	@Override
	public Url loadUrlByPort(String port) {
		// TODO Auto-generated method stub
		return urlRepository.findByPort(port);
	}

	@Override
	public Url updateUrl(int idUrl, String name, String port, String url, String description) {
		// TODO Auto-generated method stub
		System.out.println("id url "+idUrl);
		Url ur = urlRepository.findByIdUrl(idUrl);
		System.out.println(" url "+ur);
		if(ur==null)throw new RuntimeException("Url not exists");
		ur.setDescription(description);
		ur.setName(name);
		ur.setPort(port);
		ur.setUrl(url);
		urlRepository.saveAndFlush(ur);
		return ur;
	}

	@Override
	public List<Url> getAll() {
		// TODO Auto-generated method stub
		List<Url> urls = new ArrayList<>();
		urls = urlRepository.findAll();
		System.out.println("listes des urls "+urls);
		return urls;
	}

}
