package bp.demo.iot.operation.manager;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@SpringBootApplication
@ComponentScan(basePackages = {
        "bp.demo.iot.operation.manager",
        "bp.demo.iot.operation.kafka"})
public class OperationManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(OperationManagerApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper(){
    return  new ModelMapper();
  }

  @Bean
  public StringJsonMessageConverter jsonConverter() {
    return new StringJsonMessageConverter();
  }

}
