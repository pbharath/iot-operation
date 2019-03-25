package bp.demo.iot.operation.kafka.consumer;

import bp.demo.iot.operation.kafka.model.CarrierTowerEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

// @Service
public class CarrierTowerEventConsumer {

  @Value(value = "${bp.demo.iot.operation.kafka.event.carrier.tower" +
          ".topic}")
  private String carrierTowerEventTopic;

  private CountDownLatch carrierTowerEventLatch = new CountDownLatch(1);

  public CountDownLatch getCarrierTowerEventLatch() {
    return this.carrierTowerEventLatch;
  }

  @KafkaListener(
          topics = "${bp.demo.iot.operation.kafka.event.carrier.tower.topic}",
          containerFactory = "carrierTowerEventKafkaListenerContainerFactory")
  public void carrierListener(CarrierTowerEvent carrierTowerEvent) {
    System.out.println("Received carrier tower event message: " + carrierTowerEvent);
    this.carrierTowerEventLatch.countDown();
  }
}
