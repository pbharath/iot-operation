package bp.demo.iot.operation.kafka.consumer;

import bp.demo.iot.operation.manager.service.DeviceService;
import bp.demo.iot.operation.model.DeviceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Service
public class DeviceUserEventConsumer {

  private CountDownLatch deviceUserDataLatch = new CountDownLatch(1);

  private Logger logger =
          LoggerFactory.getLogger(DeviceUserEventConsumer.class);

  @Autowired
  private DeviceService deviceService;

  public CountDownLatch getDeviceUserDataLatch() {
    return this.deviceUserDataLatch;
  }

  @KafkaListener(
          topics = "${bp.demo.iot.operation.kafka.event.device.user.topic}",
          containerFactory = "deviceUserDataKafkaListenerContainerFactory")
  public void carrierListener(DeviceUser deviceUser) {

    logger.info("Received content by age data message: " + deviceUser);

    Optional<DeviceUser> du = deviceService.registerNewDeviceUser(deviceUser);

    if(du.isPresent())
      logger.info("Processed registration of new device user with id: " + du.get().getId());
    else
      logger.error("Failed to register new device user with email " + deviceUser.getEmail());

    this.getDeviceUserDataLatch().countDown();
  }
}
