package bp.demo.iot.operation.kafka.config;

import bp.demo.iot.operation.kafka.model.CarrierEvent;
import bp.demo.iot.operation.kafka.model.CarrierTowerEvent;
import bp.demo.iot.operation.kafka.model.RegisteredUserEvent;
import bp.demo.iot.operation.kafka.model.TowerGpsEvent;
import bp.demo.iot.operation.model.ContentAccess;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

  @Value(value = "${bp.demo.iot.operation.kafka.brokerlist}")
  private String bootstrapAddress;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return configProps;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }


  @Bean
  public Map<String, Object> stringKeyJsonValueProducerConfigs() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return configProps;
  }

  @Bean
  public ProducerFactory<String, RegisteredUserEvent> registeredUserEventProducerFactory() {
    return new DefaultKafkaProducerFactory<>(stringKeyJsonValueProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, RegisteredUserEvent> registeredUserEventKafkaTemplate() {
    return new KafkaTemplate<>(registeredUserEventProducerFactory());
  }


  @Bean
  public ProducerFactory<String, CarrierEvent> carrierEventProducerFactory() {
    return new DefaultKafkaProducerFactory<>(stringKeyJsonValueProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, CarrierEvent> carrierEventKafkaTemplate() {
    return new KafkaTemplate<>(carrierEventProducerFactory());
  }


  @Bean
  public ProducerFactory<String, CarrierTowerEvent> carrierTowerEventProducerFactory() {
    return new DefaultKafkaProducerFactory<>(stringKeyJsonValueProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, CarrierTowerEvent> carrierTowerEventKafkaTemplate() {
    return new KafkaTemplate<>(carrierTowerEventProducerFactory());
  }

  @Bean
  public ProducerFactory<String, ContentAccess> contentAccessProducerFactory() {
    return new DefaultKafkaProducerFactory<>(stringKeyJsonValueProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, ContentAccess> contentAccessKafkaTemplate() {
    return new KafkaTemplate<>(contentAccessProducerFactory());
  }

  @Bean
  public ProducerFactory<String, TowerGpsEvent> towerGPSEventProducerFactory() {
    return new DefaultKafkaProducerFactory<>(stringKeyJsonValueProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, TowerGpsEvent> towerGPSEventKafkaTemplate() {
    return new KafkaTemplate<>(towerGPSEventProducerFactory());
  }

}
