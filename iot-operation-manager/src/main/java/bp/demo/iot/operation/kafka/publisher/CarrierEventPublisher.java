package bp.demo.iot.operation.kafka.publisher;

import bp.demo.iot.operation.kafka.model.CarrierEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class CarrierEventPublisher {

  @Autowired
  private KafkaTemplate<String, CarrierEvent> carrierEventKafkaTemplate;

  @Value(value = "${bp.demo.iot.operation.kafka.event.carrier.topic}")
  private String carrierEventTopic;

  public void sendCarrierEventMessage(CarrierEvent carrierEvent) {
    carrierEventKafkaTemplate.send(carrierEventTopic, carrierEvent);
  }

  public String getTopic() {
    return this.carrierEventTopic;
  }

  public void sendMessage(Message message) {

    ListenableFuture<SendResult<String, CarrierEvent>> future = carrierEventKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, CarrierEvent>>() {

      @Override
      public void onSuccess(SendResult<String, CarrierEvent> result) {
        System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }
      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
      }
    });
  }

}
