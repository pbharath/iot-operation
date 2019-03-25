package bp.demo.iot.operation.manager.service;

import bp.demo.iot.operation.kafka.model.RegisteredUserEvent;
import bp.demo.iot.operation.kafka.publisher.ContentAccessPublisher;
import bp.demo.iot.operation.kafka.publisher.RegisteredUserEventPublisher;
import bp.demo.iot.operation.manager.repository.ContentByAgePolicyRuleRepository;
import bp.demo.iot.operation.manager.repository.DeviceUserRepository;
import bp.demo.iot.operation.manager.repository.dao.ContentByAgePolicyRuleDAO;
import bp.demo.iot.operation.manager.repository.dao.DeviceUserDAO;
import bp.demo.iot.operation.manager.service.helper.DeviceServiceHelper;
import bp.demo.iot.operation.model.ContentAccess;
import bp.demo.iot.operation.model.DeviceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class DeviceService {

  @Autowired
  DeviceServiceHelper deviceServiceHelper;

  @Autowired
  DeviceUserRepository deviceUserRepository;

  @Autowired
  RegisteredUserEventPublisher registeredUserEventPublisher;

  @Autowired
  ContentAccessPublisher contentAccessPublisher;

  @Autowired
  ContentByAgePolicyRuleRepository contentByAgePolicyRuleRepository;


  public Optional<DeviceUser> registerNewDeviceUser(DeviceUser deviceUser) {

    Optional optDeviceUser = Optional.empty();

    String carrierName = deviceUser.getCarrierName().trim();
    String ssn = deviceUser.getSsn().trim();
    String email = deviceUser.getEmail();

    List<DeviceUserDAO> deviceUserDAOList = deviceUserRepository.
            findByCompositePrimaryKey(carrierName, ssn, email);

    if (deviceUserDAOList.size() == 0) {

      deviceUser.setId(UUID.randomUUID());
      deviceUser.setCreatedTimeStamp(new Date());
      DeviceUserDAO dao = deviceServiceHelper.convertDeviceUserToDAO(deviceUser);
      DeviceUserDAO persistedDeviceDAO = deviceUserRepository.save(dao);
      DeviceUser du = deviceServiceHelper.convertDeviceUserDAOToEntity(persistedDeviceDAO);

      optDeviceUser = Optional.of(du);

      publishRegisteredUser(persistedDeviceDAO);
      publishContentAccess(persistedDeviceDAO);

      return optDeviceUser;

    }

    return optDeviceUser;
  }

  private void publishRegisteredUser(DeviceUserDAO deviceUserDAO) {
    RegisteredUserEvent registeredUserEvent =
            deviceServiceHelper.convertDeviceUserDAOToRegisteredUserEvent(deviceUserDAO);

    Message<RegisteredUserEvent> registeredUserEventMessage =
            MessageBuilder.withPayload(registeredUserEvent)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, registeredUserEvent.getCarrierName())
                    .setHeader(KafkaHeaders.TOPIC, registeredUserEventPublisher.getTopic())
                    .build();

    registeredUserEventPublisher.sendMessage(registeredUserEventMessage);
  }

  private void publishContentAccess(DeviceUserDAO deviceUserDAO) {

    int ageInYears =
            Period.between(convertToLocalDateViaMilisecond(deviceUserDAO.getDob()),
                    convertToLocalDateViaMilisecond(new Date())).getYears();

    List<ContentByAgePolicyRuleDAO> optionalPolicyRule =
            contentByAgePolicyRuleRepository.findByAgeRange(ageInYears, ageInYears);

    Set<String> contentTypes = new HashSet<>();
    if (optionalPolicyRule.iterator().hasNext()){
      ContentByAgePolicyRuleDAO contentByAgePolicyRuleDAO =
              optionalPolicyRule.iterator().next();

      contentTypes = contentByAgePolicyRuleDAO.getContentSet();
    }

    ContentAccess contentAccess = new ContentAccess();
    contentAccess.setCarrierName(deviceUserDAO.getKeyDAO().getCarrierName());
    contentAccess.setUserId(deviceUserDAO.getId());
    contentAccess.setContentTypes(contentTypes);
    contentAccess.setAccessResult(deviceUserDAO.getActive());
    contentAccess.setTimeStamp(new Date());

    Message<ContentAccess> contentAccessEventMessage =
            MessageBuilder.withPayload(contentAccess)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, contentAccess.getCarrierName())
                    .setHeader(KafkaHeaders.TOPIC, contentAccessPublisher.getTopic())
                    .build();

    contentAccessPublisher.sendMessage(contentAccessEventMessage);

  }

  private LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
  }


}
