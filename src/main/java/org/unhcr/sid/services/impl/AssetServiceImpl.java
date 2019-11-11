package org.unhcr.sid.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unhcr.sid.dao.AssetRepository;
import org.unhcr.sid.entities.Asset;
import org.unhcr.sid.helpers.ZXingHelper;
import org.unhcr.sid.services.AssetService;

@Service
@Transactional
public class AssetServiceImpl implements AssetService{
	
	@Autowired
	private AssetRepository assetRepository;

	@Override
	public Asset saveAsset(String modele, String type, String bc, String status, String serialId, 
			String dateAchat, String bureau, String observation, String custodian, String username) {
		System.out.println("---date Achat--"+dateAchat);
		String date = dateAchat.replace("T", " ");
		System.out.println("---date Achat new--"+date);
		Asset a1 = assetRepository.findByBc(bc);
		Asset a2 = assetRepository.findBySerialId(serialId);
		//verifier si l'asset existe pas
		if(a1!=null)throw new RuntimeException("Asset exists");
		if(a2!=null)throw new RuntimeException("Asset exists");
		Asset asset = new Asset();
		asset.setBc(bc);
		asset.setBureau(bureau);
		asset.setCustodian(custodian);
		asset.setDateAchat(dateAchat);
		SimpleDateFormat sdf2  = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
		String datecreation = sdf2.format(new Date());
		asset.setDateEnreg(sdf2.format(new Date()));
		asset.setModele(modele);
		asset.setObservation(observation);
		asset.setSerialId("HCR-ICO-"+serialId);
		asset.setStatus(status);
		asset.setType(type);
		asset.setUsername(username);
		
		String qr  = "HCR-CTO-"+serialId+".png";
		String contenu = "Modele : "+modele+" \nType : "+type+" \nBc : "+bc
				+" \nSerial : "+"HCR-ICO-"+serialId+" \nCustodian : "+custodian
				+" \nDate achat : "+dateAchat+" \nDate creation : "+datecreation
				+" \nStatut : "+status+" \nBureau : "+bureau+" \nObservation : "+observation;
		System.out.println("contenu qrcode "+contenu);
		byte[] f = ZXingHelper.getQRCodeImage(contenu, 200, 200);
		File file = new File(System.getProperty("user.home")+"/unhcr/qrcode/asset/");
        if(!file.exists()) {
        	if(file.mkdirs()) {
        		System.out.println("repertoires creer");
        	}else {
        		System.out.println("erreur creation");
        	}
        }
		try {
			Files.write(Paths.get(System.getProperty("user.home")+"/unhcr/qrcode/asset/"+qr),f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		asset.setQrCode(qr);
		assetRepository.save(asset);
		
		return asset;
	}

	@Override
	public Asset loadAssetByModele(String modele) {
		// TODO Auto-generated method stub
		return assetRepository.findByModele(modele);
	}

	@Override
	public Asset loadAssetBySerialId(String serialId) {
		// TODO Auto-generated method stub
		return assetRepository.findBySerialId(serialId);
	}

	@Override
	public Asset loadAssetByBc(String bc) {
		// TODO Auto-generated method stub
		return assetRepository.findByBc(bc);
	}

	@Override
	public Asset loadAssetByType(String type) {
		// TODO Auto-generated method stub
		return assetRepository.findByType(type);
	}

	@Override
	public Asset addMaintenanceToAsset(String numerotache) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asset updateAsset(int idAsset, String modele, String type, String serialId, String dateAchat, String dateMaintenance,
			String bc, String bureau, String status, String observation, String custodian, String username) {
		//verifier si l'asset existe
		Asset asset = assetRepository.findBySerialId(serialId);
		if(asset==null)throw new RuntimeException("Asset not exists");
		String serial = asset.getSerialId();
		String dateEnreg  =asset.getDateEnreg();
		asset.setBc(bc);
		asset.setBureau(bureau);
		asset.setSerialId(serial);
		asset.setCustodian(custodian);
		asset.setDateAchat(dateAchat);
		asset.setDateEnreg(dateEnreg);
		asset.setDateMaintenance(dateMaintenance);
		asset.setModele(modele);
		asset.setStatus(status);
		asset.setType(type);
		asset.setUsername(username);
		assetRepository.saveAndFlush(asset);
		
		return asset;
	}

	@Override
	public List<Asset> RechAsset(String option, String search) {
		// TODO Auto-generated method stub
		List<Asset> lis = new ArrayList<>();
		if(option==null) throw new RuntimeException("Value search are null"); 
		if(option.equals("Serial Id")) {
			lis = assetRepository.findBySerialIdContains(search);
			System.out.println("retour findBySerialIdContains "+lis);
		}else if(option.equals("Modele")) {
			lis = assetRepository.findByModeleContains(search);
			System.out.println("retour findByModeleContains "+lis);
		}else if(option.equals("Type")) {
			lis = assetRepository.findByTypeContains(search);
			System.out.println("retour findByTypeContains "+lis);
		}else if(option.equals("Bc")) {
			lis = assetRepository.findByBcContains(search);
			System.out.println("retour findByBcContains "+lis);
		}
		else if(option.equals("Bureau")) {
			lis = assetRepository.findByBureauContains(search);
			System.out.println("retour findByBcContains "+lis);
		}
		else if(option.equals("Custodian")) {
			lis = assetRepository.findByCustodianContains(search);
			System.out.println("retour findByBcContains "+lis);
		}
		return lis;
	}

	@Override
	public Asset loadAssetById(int id) {
		// TODO Auto-generated method stub
		return assetRepository.findByIdAsset(id);
	}

	@Override
	public Asset loadTacheByCustodian(String custodian) {
		// TODO Auto-generated method stub
		return assetRepository.findByCustodian(custodian);
	}

	@Override
	public Asset loadTacheByBureau(String bureau) {
		// TODO Auto-generated method stub
		return assetRepository.findByBureau(bureau);
	}

}
