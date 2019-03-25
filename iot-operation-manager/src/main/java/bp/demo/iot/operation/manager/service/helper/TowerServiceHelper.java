package bp.demo.iot.operation.manager.service.helper;


import bp.demo.iot.operation.manager.repository.dao.CarrierTowerDAO;
import bp.demo.iot.operation.manager.repository.dao.TowerGpsDAO;
import bp.demo.iot.operation.model.CarrierTower;
import bp.demo.iot.operation.model.TowerGps;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TowerServiceHelper {

  @Autowired
  private ModelMapper modelMapper;

  public List<CarrierTower> mapCarrierTowerDAOListToEntityList(List<CarrierTowerDAO> daoList) {

    return daoList.stream()
            .map(dao -> convertToEntity(dao))
            .collect(Collectors.toList());

  }

  public List<TowerGps> mapTowerGpsDAOListToEntityList(List<TowerGpsDAO> daoList) {

    return daoList.stream()
            .map(dao -> convertToEntity(dao))
            .collect(Collectors.toList());

  }

  public CarrierTowerDAO convertToDAO(CarrierTower carrierTower) {

    PropertyMap<CarrierTower, CarrierTowerDAO> ctMap =
            new PropertyMap<CarrierTower, CarrierTowerDAO>() {
              protected void configure() {
                map().getKeyDAO().setTowerName(source.getTowerName());
                map().getKeyDAO().setCarrierName(source.getCarrierName());
                map().setActive(source.getActive());
                map().setTimeStamp(source.getTimeStamp());
              }
            };

    TypeMap<CarrierTower, CarrierTowerDAO> typeMap
            = modelMapper.getTypeMap(CarrierTower.class, CarrierTowerDAO.class);
    if (typeMap == null) {
      modelMapper.addMappings(ctMap);
    }

    return modelMapper.map(carrierTower, CarrierTowerDAO.class);
  }

  public CarrierTower convertToEntity(CarrierTowerDAO dao) {

    PropertyMap<CarrierTowerDAO, CarrierTower> prMap =
            new PropertyMap<CarrierTowerDAO, CarrierTower>() {
              protected void configure() {
                map().setTowerName(source.getKeyDAO().getTowerName());
                map().setCarrierName(source.getKeyDAO().getCarrierName());
                map().setActive(source.getActive());
                map().setTimeStamp(source.getTimeStamp());
              }
            };

    TypeMap<CarrierTowerDAO, CarrierTower> typeMap
            = modelMapper.getTypeMap(CarrierTowerDAO.class, CarrierTower.class);
    if (typeMap == null) {
      modelMapper.addMappings(prMap);
    }

    return modelMapper.map(dao, CarrierTower.class);
  }

  public TowerGpsDAO convertToDAO(TowerGps carrierTower) {

    PropertyMap<TowerGps, TowerGpsDAO> ctMap =
            new PropertyMap<TowerGps, TowerGpsDAO>() {
              protected void configure() {
                map().setTowerName(source.getTowerName());
                map().setLongitudeStart(source.getLongitudeStart());
                map().setLongitudeEnd(source.getLongitudeEnd());
                map().setLatitudeStart(source.getLatitudeStart());
                map().setLatitudeEnd(source.getLatitudeEnd());
                map().setActive(source.getActive());
              }
            };

    TypeMap<TowerGps, TowerGpsDAO> typeMap
            = modelMapper.getTypeMap(TowerGps.class, TowerGpsDAO.class);
    if (typeMap == null) {
      modelMapper.addMappings(ctMap);
    }

    return modelMapper.map(carrierTower, TowerGpsDAO.class);
  }

  public TowerGps convertToEntity(TowerGpsDAO dao) {

    PropertyMap<TowerGpsDAO, TowerGps> prMap =
            new PropertyMap<TowerGpsDAO, TowerGps>() {
              protected void configure() {
                map().setTowerName(source.getTowerName());
                map().setLongitudeStart(source.getLongitudeStart());
                map().setLongitudeEnd(source.getLongitudeEnd());
                map().setLatitudeStart(source.getLatitudeStart());
                map().setLatitudeEnd(source.getLatitudeEnd());
                map().setActive(source.getActive());
              }
            };

    TypeMap<TowerGpsDAO, TowerGps> typeMap
            = modelMapper.getTypeMap(TowerGpsDAO.class, TowerGps.class);
    if (typeMap == null) {
      modelMapper.addMappings(prMap);
    }

    return modelMapper.map(dao, TowerGps.class);
  }

}
