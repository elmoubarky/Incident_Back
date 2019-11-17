package org.unhcr.sid.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.unhcr.sid.dao.AssetRepository;
import org.unhcr.sid.entities.Asset;
import org.unhcr.sid.services.AssetService;
import org.unhcr.sid.services.impl.AssetServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AssetServiceImplIntegrationTest {

	@TestConfiguration
	static class AssetServiceImplTestContextConfiguration {

		@Bean
		public AssetService assetService() {
			return new AssetServiceImpl();
		}
	}

	@Autowired
	private AssetService assetService;

	@MockBean
	private AssetRepository assetRepository;

	@Before
	public void setUp() {
		Asset a = new Asset();
		a.setIdAsset(22);
		a.setBc("bc 0013");
		a.setBureau("Monaco 2");
		a.setSerialId("CH 3012346");
		a.setModele("Modele 31");
		a.setType("Type 31");
		a.setCustodian("kris koko");
		a.setDateAchat("21-12-2019");
		a.setObservation("ras not");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HH:MM");
		String datecreation = sdf2.format(new Date());
		a.setDateEnreg(datecreation);

		Asset a2 = new Asset();
		a2.setIdAsset(22);
		a2.setBc("bc 0013");
		a2.setBureau("Monaco 2");
		a2.setSerialId("CH 3012346");
		a2.setModele("Modele 31");
		a2.setType("Type 31");
		a2.setCustodian("kris koko");
		a2.setDateAchat("21-12-2019");
		a2.setObservation("ras not");

		List<Asset> list = new ArrayList<>();
		list.add(a);

		Mockito.when(assetRepository.findBySerialId(a.getSerialId())).thenReturn(a);

		Mockito.when(assetRepository.findByBc(a.getBc())).thenReturn(a);

		Mockito.when(assetRepository.findByModele(a.getModele())).thenReturn(a);

		Mockito.when(assetRepository.findByType(a.getType())).thenReturn(a);

		////// list

		Mockito.when(assetRepository.findByCustodianContains(a.getCustodian())).thenReturn(list);

		Mockito.when(assetRepository.findByBureauContains(a.getBureau())).thenReturn(list);

		Mockito.when(assetRepository.findByTypeContains(a.getType())).thenReturn(list);

		Mockito.when(assetRepository.findByModeleContains(a.getModele())).thenReturn(list);

		Mockito.when(assetRepository.findBySerialIdContains(a.getSerialId())).thenReturn(list);

		Mockito.when(assetRepository.findByBcContains(a.getBc())).thenReturn(list);

		/////// save
		Mockito.when(assetRepository.save(a)).thenReturn(a);

		////// UPDATE
		Mockito.when(assetRepository.saveAndFlush(a)).thenReturn(a);

	}

	@Test
	public void whenValidBc_thenAssetShouldBeFound() {
		String bc = "bc 0013";
		Asset found = assetService.loadAssetByBc(bc);

		assertThat(found.getBc()).isEqualTo(bc);
	}

	@Test
	public void whenValidSerial_thenAssetShouldBeFound() {
		String serial = "CH 3012346";
		Asset found = assetService.loadAssetBySerialId(serial);

		assertThat(found.getSerialId()).isEqualTo(serial);
	}

	@Test
	public void whenValidModele_thenAssetShouldBeFound() {
		String modele = "Modele 31";
		Asset found = assetService.loadAssetByModele(modele);

		assertThat(found.getModele()).isEqualTo(modele);
	}

	@Test
	public void whenValidType_thenAssetShouldBeFound() {
		String type = "Type 31";
		Asset found = assetService.loadAssetByType(type);

		assertThat(found.getType()).isEqualTo(type);
	}

	//////////////////////////////////////////////////////////
	///////////////// LIST////////////////////////////////////
	////////////////////////////////////////////////////////

	@Test
	public void whenValidTypeList_thenAssetShouldBeFound() {
		String value = "Type 31";
		String option = "Type";
		int size = 1;
		List<Asset> found = assetService.RechAsset(option, value);

		assertThat(found.size()).isEqualTo(size);
	}

	@Test
	public void whenValidBcList_thenAssetShouldBeFound() {
		String value = "bc 0013";
		String option = "Bc";
		int size = 1;
		List<Asset> found = assetService.RechAsset(option, value);

		assertThat(found.size()).isEqualTo(size);
	}

	@Test
	public void whenValidModeleList_thenAssetShouldBeFound() {
		String value = "Modele 31";
		String option = "Modele";
		int size = 1;
		List<Asset> found = assetService.RechAsset(option, value);

		assertThat(found.size()).isEqualTo(size);
	}

	@Test
	public void whenValidCustodianList_thenAssetShouldBeFound() {
		String value = "kris koko";
		String option = "Custodian";
		int size = 1;
		List<Asset> found = assetService.RechAsset(option, value);

		assertThat(found.size()).isEqualTo(size);
	}

	@Test
	public void whenValidBureauList_thenAssetShouldBeFound() {
		String value = "Monaco 2";
		String option = "Bureau";
		int size = 1;
		List<Asset> found = assetService.RechAsset(option, value);

		assertThat(found.size()).isEqualTo(size);
	}

	@Test
	public void whenValidSerialList_thenAssetShouldBeFound() {
		String value = "CH 3012346";
		String option = "Serial Id";
		int size = 1;
		List<Asset> found = assetService.RechAsset(option, value);

		assertThat(found.size()).isEqualTo(size);
	}

	/////////// SAVE

	@Test
	public void whenValidSAssetSave_thenAssetShouldBeFound() {

		Asset a = new Asset();
		// a.setIdAsset(22);
		a.setBc("bc 0014");
		a.setBureau("Monaco 3");
		a.setSerialId("HCR-ICO-CH 3012341");
		a.setModele("Modele 33");
		a.setType("Type 33");
		a.setCustodian("kris koko 2");
		a.setDateAchat("22-12-2019");
		a.setObservation("ras not 2");
		a.setQrCode("HCR-ICO-CH 3012341.png");
		a.setStatus("DISPONIBLE");
		a.setUsername("fidele");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HH:MM");
		String datecreation = sdf2.format(new Date());
		a.setDateEnreg(datecreation);

		Asset found = assetService.saveAsset("Modele 33", "Type 33", "bc 0014", "DISPONIBLE", "CH 3012341",
				"22-12-2019", "Monaco 3", "ras not 2", "kris koko 2", "fidele");

		assertThat(found).isEqualTo(a);
	}

///////////UPDATE

	@Test
	public void whenValidSAssetUpdate_thenAssetShouldBeFound() {

		Asset a = new Asset();
		 a.setIdAsset(22);
		a.setBc("bc 0014");
		a.setBureau("Monaco 3");
		a.setSerialId("CH 3012346");
		a.setModele("Modele 33");
		a.setType("Type 33");
		a.setCustodian("kris koko 2");
		a.setDateAchat("22-12-2019");
		a.setObservation("ras not");
	//	a.setQrCode("HCR-ICO-CH 3012346.png");
		a.setStatus("DISPONIBLE");
		a.setUsername("fidele");
		a.setDateMaintenance("");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HH:MM");
		String datecreation = sdf2.format(new Date());
		a.setDateEnreg(datecreation);

		Asset found = assetService.updateAsset(0, "Modele 33", "Type 33", "CH 3012346",
				"22-12-2019", "", "bc 0014", "Monaco 3", "DISPONIBLE", 
				"ras not 2", "kris koko 2", "fidele");

		assertThat(found).isEqualTo(a);
	}

}
