package bp.demo.iot.operation.manager.controller;

import bp.demo.iot.operation.manager.repository.dao.CarrierDAO;
import bp.demo.iot.operation.manager.service.CarrierService;
import bp.demo.iot.operation.model.Carrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarrierController {

  @Autowired
  private CarrierService carrierService;

  private static final String CARRIER_BASE_PATH = "/carriers";

  @GetMapping(CARRIER_BASE_PATH)
  List<Carrier> findAllCarriers() {
    return carrierService.findAllCarriers();
  }

  @GetMapping(CARRIER_BASE_PATH + "/{name}")
  Optional<CarrierDAO> findCarrierByName(@PathVariable String name){

    return carrierService.findCarrierByName(name);
  }

  @PostMapping(CARRIER_BASE_PATH)
  Carrier createNewCarrier(@RequestBody Carrier carrier)
    throws Exception {

    return carrierService.createNewCarrier(carrier);
  }

  @PutMapping(CARRIER_BASE_PATH)
  Carrier updateCarrier(@RequestBody Carrier carrier)
    throws Exception {
    return carrierService.updateCarrier(carrier);
  }


}
