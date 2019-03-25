package bp.demo.iot.operation.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:iot-operation-kafka.properties"})
public class KafkaTopicConfig {

  @Value(value = "${bp.demo.iot.operation.kafka.brokerlist}")
  private String bootstrapAddress;

  @Value(value = "${bp.demo.iot.operation.kafka.event.carrier.topic}")
  private String carrierTopicName;

  @Value(value = "${bp.demo.iot.operation.kafka.event.carrier.tower.topic}")
  private String carrierTowerTopicName;

  @Value(value = "${bp.demo.iot.operation.kafka.event.device.user.topic}")
  private String deviceUserTopicName;

  @Value(value = "${bp.demo.iot.operation.kafka.data.registered.user.topic}")
  private String registeredUserTopicName;

  @Value(value = "${bp.demo.iot.operation.kafka.event.tower.gps.topic}")
  private String towerGPSTopicName;

  @Value(value = "${bp.demo.iot.operation.kafka.event.content.access.topic}")
  private String contentAccessTopicName;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic carrierTopic() {
    return new NewTopic(carrierTopicName, 1, (short) 1);
  }

  @Bean
  public NewTopic carrierTowerTopic() {
    return new NewTopic(carrierTowerTopicName, 1, (short) 1);
  }

  @Bean
  public NewTopic contentAccessTopic() {
    return new NewTopic(contentAccessTopicName, 1, (short) 1);
  }

  @Bean
  public NewTopic deviceUserTowerTopic() {
    return new NewTopic(deviceUserTopicName, 1, (short) 1);
  }

  @Bean
  public NewTopic registeredUserTowerTopic() {
    return new NewTopic(registeredUserTopicName, 1, (short) 1);
  }

  @Bean
  public NewTopic towerGPSTopic() {
    return new NewTopic(towerGPSTopicName, 1, (short) 1);
  }

}
