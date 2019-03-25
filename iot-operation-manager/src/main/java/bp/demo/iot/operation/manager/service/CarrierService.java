package bp.demo.iot.operation.manager.service;

import bp.demo.iot.operation.kafka.publisher.CarrierEventPublisher;
import bp.demo.iot.operation.kafka.model.CarrierEvent;
import bp.demo.iot.operation.manager.repository.CarrierRepository;
import bp.demo.iot.operation.manager.repository.dao.CarrierDAO;
import bp.demo.iot.operation.manager.service.helper.CarrierServiceHelper;
import bp.demo.iot.operation.model.Carrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarrierService
  implements Serializable {

  @Autowired
  private CarrierRepository carrierRepository;

  @Autowired
  private CarrierEventPublisher carrierEventPublisher;

//  @Autowired
//  private CarrierEventMessageListener carrierEventMessageListener;

  @Autowired
  private CarrierServiceHelper carrierServiceHelper;


  public List<Carrier> findAllCarriers() {
    List<CarrierDAO> carrierDAOList = carrierRepository.findAll();

    return carrierServiceHelper.mapDAOListToEntityList(carrierDAOList);
  }

  public Optional<CarrierDAO> findCarrierByName(String name){
    String carrierName = name.trim().toUpperCase();
    List<CarrierDAO> carrierDAOList =
            carrierRepository.findByName(carrierName);

    if (carrierDAOList.size() != 0) {
      return Optional.of(carrierDAOList.iterator().next());
    }
    else {
      return Optional.empty();
    }
  }

  public Carrier createNewCarrier(Carrier carrier)
          throws Exception {

    String carrierName = carrier.getName().trim().toUpperCase();
    List<CarrierDAO> carrierDAOList =
            carrierRepository.findByName(carrierName);

    if (carrierDAOList.size() == 0) {

      carrier.setId(UUID.randomUUID());
      carrier.setTimeStamp(new Date());
      CarrierDAO dao = carrierServiceHelper.convertToDAO(carrier);

      carrierRepository.save(dao);

      CarrierDAO persistedCarrierDAO = carrierRepository.insert(dao);

      CarrierEvent ce = new CarrierEvent(persistedCarrierDAO.getName(),
              persistedCarrierDAO.isActive());

      Message<CarrierEvent> message = MessageBuilder.withPayload(ce)
              .setHeader(KafkaHeaders.MESSAGE_KEY, carrier.getName())
              .setHeader(KafkaHeaders.TOPIC, carrierEventPublisher.getTopic())
              .build();
      carrierEventPublisher.sendMessage(message);
//      carrierEventMessageListener.getCarrierEventLatch().await(10,
//              TimeUnit.SECONDS);

      return carrierServiceHelper.convertToEntity(persistedCarrierDAO);
    }
    else {
      throw new Exception("CarrierEvent " + carrierName + " pre-registered with " +
              "id " + carrierDAOList.iterator().next().getId());
    }
  }


  public Carrier updateCarrier(Carrier carrier)
    throws Exception{
    String carrierName = carrier.getName().trim().toUpperCase();
    List<CarrierDAO> carrierDAOList =
            carrierRepository.findByName(carrierName);

    if (carrierDAOList.size() != 0) {
      CarrierDAO existingCarrierDAO = carrierDAOList.iterator().next();
      existingCarrierDAO.setActive(carrier.getActive());

      return carrierServiceHelper.convertToEntity(
              carrierRepository.save(existingCarrierDAO));
    }
    else {
      throw new Exception("CarrierEvent " + carrierName + " not found");
    }
  }

}
