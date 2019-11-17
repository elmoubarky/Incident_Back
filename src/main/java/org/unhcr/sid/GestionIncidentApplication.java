package org.unhcr.sid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.client.RestTemplate;
import org.unhcr.sid.entities.Asset;
import org.unhcr.sid.entities.Cartouche;
import org.unhcr.sid.entities.Tracking;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.unhcr.sid.entities.OperationAchat;
import org.unhcr.sid.entities.Tache;

@EnableSwagger2
@SpringBootApplication
public class GestionIncidentApplication implements CommandLineRunner {

	//@Autowired
	//private TacheService tacheService;

	// exposer l'id
	//@Autowired
//	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	//le bean de Swagger
	@Bean
	public Docket gestionIncidentApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.unhcr.sid")).build();
	}

	//@Autowired
	//private AssetRepository assetRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionIncidentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// exposer l'id
		//repositoryRestConfiguration.exposeIdsFor(Tache.class, Asset.class, Cartouche.class, Tracking.class,
		//		OperationAchat.class);

		/*
		 * tacheService.saveTache("SE defaillant", "MAINTENANCE", "ICO-Serial-0002",
		 * "MINEUR");
		 * 
		 * Asset a = new Asset(); a.setBc("bc001"); a.setBureau("Abidjan");
		 * a.setCustodian("Kris"); a.setDateEnreg("10-11-2019 09:20:11");
		 * a.setModele("Modele 1"); a.setObservation("RAS"); a.setStatus("DISPONIBLE");
		 * a.setSerialId("HCR-ICO-009988"); a.setType("Type 1");
		 * assetRepository.save(a);
		 * 
		 * // verifier qrcode String name = "HCR-CTO-12345OO.png"; File file = new
		 * File(System.getProperty("user.home") + "/unhcr/qrcode/asset/" + name);
		 * System.out.println("file " + file); String testQr =
		 * ZXingHelper.decodeQRCode(file); System.out.println("Test du QRCODE " +
		 * testQr);
		 * 
		 * Asset asset = new Asset(); // decoder le qrcode // String serial =
		 * ZXingHelper.decodeQRCode(file);
		 * 
		 * asset = assetRepository.findBySerialId("HCR-CTO-" + testQr);
		 * System.out.println("asset " + asset);
		 */
	}

}
