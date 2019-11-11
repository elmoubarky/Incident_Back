package org.unhcr.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.unhcr.sid.entities.Asset;

@CrossOrigin("*")
@RepositoryRestResource
public interface AssetRepository extends JpaRepository<Asset, Integer>{
	
	public Asset findBySerialId(String serialId);
	public Asset findByBc(String bc);
	public Asset findByModele(String modele);
	public Asset findByType(String type);
	public Asset findByIdAsset(int id);
	public Asset findByBureau(String bureau);
	public Asset findByCustodian(String custodian);
	@RestResource(path = "/assetsByBureau")
    public List<Asset> findByBureauContains(@Param("mc") String mc);
	@RestResource(path = "/assetsByCustodian")
    public List<Asset> findByCustodianContains(@Param("mc") String mc);
	@RestResource(path = "/assetsBySerialId")
    public List<Asset> findBySerialIdContains(@Param("mc") String mc);
	@RestResource(path = "/assetsByBc")
    public List<Asset> findByBcContains(@Param("mc") String mc);
	@RestResource(path = "/assetsByModele")
    public List<Asset> findByModeleContains(@Param("mc") String mc);
	@RestResource(path = "/assetsByType")
    public List<Asset> findByTypeContains(@Param("mc") String mc);

}
