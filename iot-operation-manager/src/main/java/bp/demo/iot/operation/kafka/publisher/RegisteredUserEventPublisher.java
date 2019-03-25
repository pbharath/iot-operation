package bp.demo.iot.operation.kafka.publisher;

import bp.demo.iot.operation.kafka.model.RegisteredUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class RegisteredUserEventPublisher {

  @Autowired
  private KafkaTemplate<String, RegisteredUserEvent> registeredUserEventKafkaTemplate;

  @Value(value = "${bp.demo.iot.operation.kafka.data.registered.user.topic}")
  private String registeredUserEventTopic;

  public void sendCarrierEventMessage(RegisteredUserEvent registeredUserEvent) {
    registeredUserEventKafkaTemplate.send(registeredUserEventTopic, registeredUserEvent);
  }

  public String getTopic() {
    return this.registeredUserEventTopic;
  }

  public void sendMessage(Message message) {

    ListenableFuture<SendResult<String, RegisteredUserEvent>> future = registeredUserEventKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, RegisteredUserEvent>>() {

      @Override
      public void onSuccess(SendResult<String, RegisteredUserEvent> result) {
        System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }
      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
      }
    });
  }
}
