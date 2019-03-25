package bp.demo.iot.operation.kafka.publisher;

import bp.demo.iot.operation.kafka.model.TowerGpsEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class TowerGPSEventProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private KafkaTemplate<String, TowerGpsEvent> towerGPSEventKafkaTemplate;

  @Value(value = "${bp.demo.iot.operation.kafka.event.tower.gps.topic}")
  private String towerGPSEventTopic;

  public void sendTowerGPSEventMessage(TowerGpsEvent towerGPSEvent) {
    towerGPSEventKafkaTemplate.send(towerGPSEventTopic, towerGPSEvent);
  }

  public String getTopic() {
    return this.towerGPSEventTopic;
  }

  public void sendMessage(Message message) {

    ListenableFuture<SendResult<String, TowerGpsEvent>> future = towerGPSEventKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, TowerGpsEvent>>() {

      @Override
      public void onSuccess(SendResult<String, TowerGpsEvent> result) {
        System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }
      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
      }
    });
  }
}
