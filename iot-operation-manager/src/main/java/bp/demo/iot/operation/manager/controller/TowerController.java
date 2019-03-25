package bp.demo.iot.operation.manager.controller;

import bp.demo.iot.operation.manager.service.TowerService;
import bp.demo.iot.operation.model.CarrierTower;
import bp.demo.iot.operation.model.TowerGps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TowerController {

  @Autowired
  private TowerService towerService;

  private static final String CARRIER_BASE_PATH = "/towers";

  @GetMapping(CARRIER_BASE_PATH + "/gps/all")
  List<TowerGps> findAllTowerCoordinates() {
    return towerService.findAllTowersCoordinates();
  }

  @PostMapping(CARRIER_BASE_PATH + "/carrier")
  CarrierTower createNewCarrierByTower(@RequestBody CarrierTower carrierTower)
          throws Exception {

    return towerService.createNewCarrierByTower(carrierTower);
  }

  @PostMapping(CARRIER_BASE_PATH + "/gps")
  TowerGps createNewTowerGPS(@RequestBody TowerGps towerGps)
    throws Exception {

    return towerService.createNewTowerGPS(towerGps);
  }

}
