package bp.demo.iot.operation.kafka.publisher;

import bp.demo.iot.operation.kafka.model.CarrierTowerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class CarrierTowerEventPublisher {

  @Autowired
  private KafkaTemplate<String, CarrierTowerEvent> carrierTowerEventKafkaTemplate;

  @Value(value = "${bp.demo.iot.operation.kafka.event.carrier.tower" +
          ".topic}")
  private String carrierTowerEventTopic;

  public void sendCarrierTowerEventMessage(CarrierTowerEvent carrierTowerEvent) {
    carrierTowerEventKafkaTemplate.send(carrierTowerEventTopic,
            carrierTowerEvent);
  }

  public String getTopic() {
    return this.carrierTowerEventTopic;
  }

  public void sendMessage(Message message) {

    ListenableFuture<SendResult<String, CarrierTowerEvent>> future =
            carrierTowerEventKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, CarrierTowerEvent>>() {

      @Override
      public void onSuccess(SendResult<String, CarrierTowerEvent> result) {
        System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }
      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
      }
    });
  }
}
