package bp.demo.iot.operation.manager.service.helper;

import bp.demo.iot.operation.kafka.model.RegisteredUserEvent;
import bp.demo.iot.operation.manager.repository.dao.DeviceUserDAO;
import bp.demo.iot.operation.model.DeviceUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceServiceHelper {

  @Autowired
  private ModelMapper modelMapper;

  public DeviceUserDAO convertDeviceUserToDAO(DeviceUser deviceUser) {

    PropertyMap<DeviceUser, DeviceUserDAO> duMap =
            new PropertyMap<DeviceUser, DeviceUserDAO>() {
              protected void configure() {
                map().getKeyDAO().setCarrierName(source.getCarrierName());
                map().getKeyDAO().setSsn(source.getSsn());
                map().getKeyDAO().setEmail(source.getEmail());
                map().setTowerName(source.getTowerName());
                map().setPlatformName(source.getPlatformName());
                map().setApprovalId(source.getApprovalId());
                map().setId(source.getId());
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setDob(source.getDob());
                map().setDeviceType(source.getDeviceType());
                map().setDeviceId(source.getDeviceId());
                map().setLongitude(source.getLongitude());
                map().setLatitude(source.getLatitude());
                map().setActive(source.getActive());
                map().setCreatedTimeStamp(source.getCreatedTimeStamp());
                map().setModifiedTimeStamp(source.getModifiedTimeStamp());
              }
            };

    TypeMap<DeviceUser, DeviceUserDAO> typeMap
            = modelMapper.getTypeMap(DeviceUser.class, DeviceUserDAO.class);

    if (typeMap == null) {
      modelMapper.addMappings(duMap);
    }

    return modelMapper.map(deviceUser, DeviceUserDAO.class);
  }

  public DeviceUser convertDeviceUserDAOToEntity(DeviceUserDAO dao) {

    PropertyMap<DeviceUserDAO,
            DeviceUser> prMap =
            new PropertyMap<DeviceUserDAO, DeviceUser>() {
              protected void configure() {
                map().setCarrierName(source.getKeyDAO().getCarrierName());
                map().setSsn(source.getKeyDAO().getSsn());
                map().setEmail(source.getKeyDAO().getEmail());
                map().setTowerName(source.getTowerName());
                map().setPlatformName(source.getPlatformName());
                map().setApprovalId(source.getApprovalId());
                map().setId(source.getId());
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setDob(source.getDob());
                map().setDeviceType(source.getDeviceType());
                map().setDeviceId(source.getDeviceId());
                map().setLongitude(source.getLongitude());
                map().setLatitude(source.getLatitude());
                map().setActive(source.getActive());
                map().setCreatedTimeStamp(source.getCreatedTimeStamp());
                map().setModifiedTimeStamp(source.getModifiedTimeStamp());
              }
            };

    TypeMap<DeviceUserDAO, DeviceUser> typeMap
            = modelMapper.getTypeMap(DeviceUserDAO.class, DeviceUser.class);

    if (typeMap == null) {
      modelMapper.addMappings(prMap);
    }

    return modelMapper.map(dao, DeviceUser.class);

  }

  public RegisteredUserEvent convertDeviceUserDAOToRegisteredUserEvent(DeviceUserDAO dao) {

    PropertyMap<DeviceUserDAO, RegisteredUserEvent> propertyMap =
            new PropertyMap<DeviceUserDAO, RegisteredUserEvent>() {
              protected void configure() {
                map().setCarrierName(source.getKeyDAO().getCarrierName());
                map().setEmail(source.getKeyDAO().getEmail());
                map().setTowerName(source.getTowerName());
                map().setUserId(source.getId());
                map().setDob(source.getDob());
                map().setActive(source.getActive());
                map().setTimeStamp(source.getCreatedTimeStamp());
              }
            };

    TypeMap<DeviceUserDAO, RegisteredUserEvent> typeMap
            = modelMapper.getTypeMap(DeviceUserDAO.class, RegisteredUserEvent.class);

    if (typeMap == null) {
      modelMapper.addMappings(propertyMap);
    }

    return modelMapper.map(dao, RegisteredUserEvent.class);

  }

}
