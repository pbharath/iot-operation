package bp.demo.iot.operation.manager.service;

import bp.demo.iot.operation.kafka.model.CarrierTowerEvent;
import bp.demo.iot.operation.kafka.model.TowerGpsEvent;
import bp.demo.iot.operation.kafka.publisher.CarrierTowerEventPublisher;
import bp.demo.iot.operation.kafka.publisher.TowerGPSEventProducer;
import bp.demo.iot.operation.manager.service.helper.TowerServiceHelper;
import bp.demo.iot.operation.manager.repository.TowerCarrierRepository;
import bp.demo.iot.operation.manager.repository.TowerGpsRepository;
import bp.demo.iot.operation.manager.repository.dao.CarrierTowerDAO;
import bp.demo.iot.operation.manager.repository.dao.TowerGpsDAO;
import bp.demo.iot.operation.model.CarrierTower;
import bp.demo.iot.operation.model.TowerGps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TowerService {

  @Autowired
  TowerCarrierRepository towerCarrierRepository;

  @Autowired
  TowerGpsRepository towerGPSRepository;

  @Autowired
  private CarrierTowerEventPublisher carrierTowerEventPublisher;

//  @Autowired
//  private CarrierTowerEventConsumer carrierTowerEventMessageListener;

  @Autowired
  private TowerGPSEventProducer towerGPSEventProducer;

//  @Autowired
//  private TowerGpsEventMessageListener towerGPSEventMessageListener;

  @Autowired
  private TowerServiceHelper towerServiceHelper;

  public List<TowerGps> findAllTowersCoordinates() {
    List<TowerGpsDAO> towerGpsDAOList = towerGPSRepository.findAll();

    return towerServiceHelper.mapTowerGpsDAOListToEntityList(towerGpsDAOList);
  }

  public CarrierTower createNewCarrierByTower(CarrierTower carrierTower)
          throws Exception {

    String towerName =
            carrierTower.getTowerName().trim().toUpperCase();
    String carrierName =
            carrierTower.getCarrierName().trim().toUpperCase();

    List<CarrierTowerDAO> towerCarrierIterable =
            towerCarrierRepository.findByKeyTowerNameAndKeyCarrierName(towerName,
                    carrierName);

    if (!towerCarrierIterable.iterator().hasNext()) {

      carrierTower.setTimeStamp(new Date());
      CarrierTowerDAO dao = towerServiceHelper.convertToDAO(carrierTower);

      CarrierTowerDAO persistedCarrierTowerDAO =
              towerCarrierRepository.insert(dao);

      CarrierTowerEvent cte =
              new CarrierTowerEvent(
                      persistedCarrierTowerDAO.getKeyDAO().getTowerName(),
                      persistedCarrierTowerDAO.getKeyDAO().getCarrierName(),
                      persistedCarrierTowerDAO.getActive());

      Message<CarrierTowerEvent> message = MessageBuilder.withPayload(cte)
              .setHeader(KafkaHeaders.MESSAGE_KEY, carrierName)
              .setHeader(KafkaHeaders.TOPIC,
                      carrierTowerEventPublisher.getTopic())
              .build();
      carrierTowerEventPublisher.sendMessage(message);
//      carrierTowerEventMessageListener.getCarrierTowerEventLatch().await(10,
//              TimeUnit.SECONDS);

      return towerServiceHelper.convertToEntity(persistedCarrierTowerDAO);
    }
    else {
      throw new Exception("CarrierEvent " + carrierName + " is registered " +
              "with pre-registered CarrierEvent " + carrierName +
              " pre-registered with tower " + towerName);
    }
  }

  public TowerGps createNewTowerGPS(TowerGps towerGps)
          throws Exception {

    String towerName = towerGps.getTowerName().trim().toUpperCase();

    List<TowerGpsDAO> towerGPSIterable =
            towerGPSRepository.findByTowerName(towerName);

    if(towerGPSIterable.size() == 0) {

      TowerGpsDAO dao = towerServiceHelper.convertToDAO(towerGps);

      TowerGpsDAO persistedTowerGpsDAO = towerGPSRepository.insert(dao);

      TowerGpsEvent tge =
              new TowerGpsEvent(
                      persistedTowerGpsDAO.getTowerName(),
                      persistedTowerGpsDAO.getLongitudeStart(),
                      persistedTowerGpsDAO.getLatitudeStart(),
                      persistedTowerGpsDAO.getLongitudeEnd(),
                      persistedTowerGpsDAO.getLatitudeEnd(),
                      persistedTowerGpsDAO.getActive());

      Message<TowerGpsEvent> message = MessageBuilder.withPayload(tge)
              .setHeader(KafkaHeaders.MESSAGE_KEY, towerName)
              .setHeader(KafkaHeaders.TOPIC,
                      towerGPSEventProducer.getTopic())
              .build();
      towerGPSEventProducer.sendMessage(message);
//      towerGPSEventMessageListener.getTowerGPSEventLatch().await(10,
//              TimeUnit.SECONDS);

      return towerServiceHelper.convertToEntity(persistedTowerGpsDAO);
    }
    else {
      throw new Exception("GPS coordinates of Tower " + towerName + " are " +
              "pre-registered");
    }

  }

}
