package bp.demo.iot.operation.kafka.publisher;

import bp.demo.iot.operation.model.ContentAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ContentAccessPublisher {

  @Autowired
  private KafkaTemplate<String, ContentAccess> contentAccessKafkaTemplate;

  @Value(value = "${bp.demo.iot.operation.kafka.event.content.access.topic}")
  private String contentAccessTopic;

  public void sendCarrierEventMessage(ContentAccess contentAccess) {
    contentAccessKafkaTemplate.send(contentAccessTopic, contentAccess);
  }

  public String getTopic() {
    return this.contentAccessTopic;
  }

  public void sendMessage(Message message) {

    ListenableFuture<SendResult<String, ContentAccess>> future = contentAccessKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, ContentAccess>>() {

      @Override
      public void onSuccess(SendResult<String, ContentAccess> result) {
        System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }
      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
      }
    });
  }

}
