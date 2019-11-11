package org.unhcr.sid.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unhcr.sid.dao.TrackingRepository;
import org.unhcr.sid.entities.Tracking;
import org.unhcr.sid.services.TrackingService;;

@Service
@Transactional
public class TrackingServiceImpl implements TrackingService{
	
	@Autowired
	private TrackingRepository trackingRepository;
	
	public String calcHeur(String arrive, String depart) {
		String diff = null;
		System.out.println(" date arrive "+arrive);
		System.out.println(" date depart "+depart);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			Date deb = sdf.parse(depart);
			Date fin = sdf.parse(arrive);
			
			long diffMin = Math.abs(fin.getTime() - deb.getTime()) / 60000l;
			long min = diffMin%60l;
			long hour = diffMin/60l;
			System.out.println( hour + ":" + min);
			diff = hour + ":" + min;

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		return diff;
	}

	
	@Override
	public Tracking saveTracking(String reference, String destination, String heureDepart, String type, String qualite,
			String heureArrive, String dureeTrajet, String username) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
		Tracking track = new Tracking();
		track.setDateEnreg(sdf.format(new Date()));
		track.setDestination(destination);
		track.setHeureArrive(heureArrive);
		System.out.println("heureArrive "+heureArrive);
		track.setHeureDepart(heureDepart);
		System.out.println("heureDepart "+heureDepart);
		track.setQualiteSignal(qualite);
		track.setReference(reference);
		track.setUserName(username);
		track.setType(type);
		
		//calcul de la duree du trajet
		String duree  =calcHeur(heureArrive, heureDepart);
		System.out.println("duree "+duree);
		track.setDureeTrajet(duree);
		trackingRepository.save(track);
		System.out.println("tracking "+track);
		return track;
	}

	@Override
	public Tracking loadTrackingByReference(String reference) {
		// TODO Auto-generated method stub
		return trackingRepository.findByReference(reference);
	}

	@Override
	public Tracking loadTrackingByType(String type) {
		// TODO Auto-generated method stub
		return trackingRepository.findByType(type);
	}

	@Override
	public Tracking loadTrackingById(int id) {
		// TODO Auto-generated method stub
		return trackingRepository.findByIdTracking(id);
	}

	@Override
	public List<Tracking> listTracking() {
		// TODO Auto-generated method stub
		return trackingRepository.findAll();
	}

	@Override
	public Tracking loadTrackingByDateEnreg(String dateEnreg) {
		// TODO Auto-generated method stub
		return trackingRepository.findByDateEnreg(dateEnreg);
	}

	@Override
	public Tracking updateTracking(int idTracking, String reference, String destination, String heureDepart,
			String type, String qualite, String heureArrive, String dureeTrajet, String username) {
		// TODO Auto-generated method stub
		System.out.println("id tracking "+idTracking);
		Tracking track = trackingRepository.findByIdTracking(idTracking);
		System.out.println(" tracking "+track);
		if(track==null)throw new RuntimeException("Tracking not exists");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
		System.out.println("----- after simple date format ----");
		track.setDateEnreg(sdf.format(new Date()));
		track.setDestination(destination);
		track.setHeureArrive(heureArrive);
		track.setHeureDepart(heureDepart);
		track.setQualiteSignal(qualite);
		track.setReference(reference);
		track.setUserName(username);
		track.setType(type);
		System.out.println("----- after type----");
		
		//calcul de la duree du trajet
		String duree  =calcHeur(heureArrive, heureDepart);
		System.out.println("duree "+duree);
		track.setDureeTrajet(duree);
		trackingRepository.saveAndFlush(track);
		System.out.println("tracking "+track);
		return track;
	}


	@Override
	public List<Tracking> RechTracking(String option, String search) {
		// TODO Auto-generated method stub
		List<Tracking> lis = new ArrayList<>();
		if(option==null) throw new RuntimeException("Value search are null"); 
		if(option.equals("Reference")) {
			lis = trackingRepository.findByReferenceContains(search);
			System.out.println("retour findByReferenceContains "+lis);
		}else if(option.equals("Date Enreg")) {
			lis = trackingRepository.findByDateEnregContains(search);
			System.out.println("retour findByTypeContains "+lis);
		}else if(option.equals("Type")) {
			lis = trackingRepository.findByTypeContains(search);
			System.out.println("retour findByTypeContains "+lis);
		}
		return lis;
	}

	
	

}
