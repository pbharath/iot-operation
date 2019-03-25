package bp.demo.iot.operation.manager.controller;

import bp.demo.iot.operation.manager.repository.CarrierRepository;
import bp.demo.iot.operation.manager.service.DeviceService;
import bp.demo.iot.operation.model.DeviceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DeviceUserController {

  @Autowired
  private DeviceService deviceService;

  @Autowired
  private CarrierRepository carrierRepository;

  @PostMapping("/device/user")
  public ResponseEntity createNewUser(@RequestBody DeviceUser deviceUser) {

    Optional<DeviceUser> optDeviseUser =
            deviceService.registerNewDeviceUser(deviceUser);

    if(optDeviseUser.isPresent()) {
      return new ResponseEntity("User ID: " + optDeviseUser.get().getId().toString(), HttpStatus.CREATED);
    }

    return new ResponseEntity("User could not be registered", HttpStatus.CONFLICT);

  }

}
