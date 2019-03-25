package bp.demo.iot.operation.manager.service.helper;

import bp.demo.iot.operation.manager.repository.dao.CarrierDAO;
import bp.demo.iot.operation.model.Carrier;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarrierServiceHelper {

  @Autowired
  private ModelMapper modelMapper;

  public List<Carrier> mapDAOListToEntityList(List<CarrierDAO> daoList) {

    return daoList.stream()
            .map(dao -> convertToEntity(dao))
            .collect(Collectors.toList());

  }

  public CarrierDAO convertToDAO(Carrier policyRule) {

    PropertyMap<Carrier, CarrierDAO> prMap =
            new PropertyMap<Carrier, CarrierDAO>() {
              protected void configure() {
                map().setName(source.getName());
                map().setId(source.getId());
                map().setActive(source.getActive());
              }
            };

    TypeMap<Carrier, CarrierDAO> typeMap
            = modelMapper.getTypeMap(Carrier.class, CarrierDAO.class);
    if (typeMap == null) {
      modelMapper.addMappings(prMap);
    }

    return modelMapper.map(policyRule, CarrierDAO.class);
  }

  public Carrier convertToEntity(CarrierDAO dao) {

    PropertyMap<CarrierDAO,
            Carrier> prMap =
            new PropertyMap<CarrierDAO, Carrier>() {
              protected void configure() {
                map().setName(source.getName());
                map().setId(source.getId());
                map().setActive(source.isActive());
              }
            };

    TypeMap<CarrierDAO, Carrier> typeMap
            = modelMapper.getTypeMap(CarrierDAO.class, Carrier.class);
    if (typeMap == null) {
      modelMapper.addMappings(prMap);
    }

    return modelMapper.map(dao, Carrier.class);
  }

}
