package org.unhcr.sid.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.unhcr.sid.dao.AssetRepository;
import org.unhcr.sid.entities.Asset;
import org.unhcr.sid.entities.Cartouche;
import org.unhcr.sid.helpers.ZXingHelper;
import org.unhcr.sid.services.AssetService;

import lombok.Data;

@CrossOrigin("*")
@RestController
public class AssetController {
	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private AssetRepository assetRepository;
	
	// methode permettant de creer et retourner un objet de type Asset
		@PostMapping("/createAsset")
		public Asset createAsset(@RequestBody AssetForm assetForm) {
			System.out.println("----------Asset Create ------------");
			return assetService.saveAsset(assetForm.getModele(), assetForm.getType(), assetForm.getBc(),
					assetForm.getStatus(), assetForm.getSerialId(), assetForm.getDateAchat(), assetForm.getBureau(),
					assetForm.getObservation(), assetForm.getCustodian(), assetForm.getUsername());
		}
		
		// methode permettant de modifier et retourner un objet de type Asset
		@PostMapping("/updateAsset")
		public Asset updateAsset(@RequestBody AssetUpdateForm assetUpdateForm) {
			System.out.println("----------Asset Update ------------");
			return assetService.updateAsset(assetUpdateForm.getIdAsset(), assetUpdateForm.getModele(), 
					assetUpdateForm.getType(), assetUpdateForm.getSerialId(), 
					assetUpdateForm.getDateAchat(), assetUpdateForm.getDateMaintenance(),
					assetUpdateForm.getBc(), assetUpdateForm.getBureau(),
					assetUpdateForm.getStatus(), assetUpdateForm.getObservation(), 
					assetUpdateForm.getCustodian(), assetUpdateForm.getUsername());
		}
		
		@GetMapping(path="/AssetById/{id}")
		public Asset getTrack(@PathVariable("id") int id) {
			Asset op = assetService.loadAssetById(id);
			System.out.println("Asset trouve "+op);
			return op;
		}
		
		@GetMapping(path="/listAssets")
		public List<Asset> listAsset(){
			return assetRepository.findAll();
		}
		
		@GetMapping(path="/assetsQrcode/{serial}",produces = MediaType.IMAGE_PNG_VALUE)
	    public byte[] getPhoto(@PathVariable("serial") String serial) throws Exception{
	        Asset a=assetService.loadAssetBySerialId(serial);
	        System.out.println("Serial "+serial);
	        System.out.println("Asset a "+a);
	        String qr  = a.getQrCode();
	        System.out.println("-----Qr Code----- "+qr);
	        
	        File file = new File(System.getProperty("user.home")+"/unhcr/qrcode/asset/");
	        if(!file.exists()) {
	        	if(file.mkdirs()) {
	        		System.out.println("repertoires creer");
	        	}else {
	        		System.out.println("erreur creation");
	        	}
	        }
	        
	        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/unhcr/qrcode/asset/"+qr));
	    }
		
		@PostMapping(path="/rechAsset")
		public List<Asset> rechTracking(@RequestBody AssetSearch assetSearch){
			return assetService.RechAsset(assetSearch.getOptionValue(), assetSearch.getSearchValue());
		}
		
		@PostMapping(path="/readQrcode")
		public Asset uploadQrcode(@RequestParam("file") MultipartFile file) throws Exception{
			Asset asset = new Asset();
			System.out.println("File "+file);
			System.out.println("File Name "+file.getName());
			System.out.println("File OriginalFileName "+file.getOriginalFilename());
			
			//convertir en file
			File f = new File(file.getOriginalFilename());
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(file.getBytes());
			fos.close();
			
			System.out.println("File after "+f);
			System.out.println("File AbsolutePath "+f.getAbsolutePath());
			System.out.println("File Name "+f.getName());
			System.out.println("File Path "+f.getPath());
			
			//decoder le qrcode
			String serial = ZXingHelper.decodeQRCode(f);
			if(serial.equals("")) return asset;
			asset = assetService.loadAssetBySerialId(serial);
			System.out.println("asset "+asset);
			return asset;
		}
		

}
@Data
class AssetSearch {
	private String optionValue;
	private String searchValue;
}

@Data
class AssetForm {
	private String modele;
	private String type;
	private String bc;
	private String serialId;
	private String dateAchat;
	private String bureau;
	private String observation;
	private String custodian;
	private String username;
	private String status;

}

@Data
class AssetUpdateForm {
	private int idAsset;
	private String modele;
	private String type;
	private String bc;
	private String serialId;
	private String dateAchat;
	private String dateEnreg;
	private String dateMaintenance;
	private String status;
	private String bureau;
	private String observation;
	private String custodian;
	private String username;

}

