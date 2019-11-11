package org.unhcr.sid.services;

import java.util.List;

import org.unhcr.sid.entities.Tracking;

public interface TrackingService {
	
	public Tracking saveTracking(String reference, String destination, String heureDepart, String type, 
			String qualite, String heureArrive,String dureeTrajet, String username);
	public Tracking loadTrackingByReference(String reference);
	public Tracking loadTrackingByType(String type);
	public Tracking loadTrackingById(int id);
	public List<Tracking> listTracking();
	public Tracking loadTrackingByDateEnreg(String dateEnreg);
	public Tracking updateTracking(int idTracking, String reference, String destination, String heureDepart, String type, 
			String qualite, String heureArrive,String dureeTrajet, String username);
	public List<Tracking> RechTracking(String value, String search);

}
