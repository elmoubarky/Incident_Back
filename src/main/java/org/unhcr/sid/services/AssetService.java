package org.unhcr.sid.services;

import java.util.List;

import org.unhcr.sid.entities.Asset;

public interface AssetService {
	
	public Asset saveAsset(String modele, String type, String bc, String status, String serialId, String dateAchat,
			String bureau, String observation, String custodian, String username);
	public Asset loadAssetByModele(String modele);
	public Asset loadAssetById(int id);
	public Asset loadTacheByCustodian(String custodian);
	public Asset loadTacheByBureau(String bureau);
	public Asset loadAssetBySerialId(String serialId);
	public Asset loadAssetByBc(String bc);
	public Asset loadAssetByType(String type);
	public Asset addMaintenanceToAsset(String numerotache);
	public Asset updateAsset(int idAsset, String modele, String type, String serialId, String dateAchat, String dateMaintenance,
			String bc, String bureau, String status, String observation, String custodian, String username);
	public List<Asset> RechAsset(String value, String search);

}
