package bp.demo.iot.operation.manager.repository;

import bp.demo.iot.operation.manager.repository.dao.pk.CarrierTowerPrimaryKeyDAO;
import bp.demo.iot.operation.manager.repository.dao.CarrierTowerDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TowerCarrierRepository
        extends CassandraRepository<CarrierTowerDAO, CarrierTowerPrimaryKeyDAO> {

  @Query("SELECT * FROM IotOperationSpace.Carrier_Tower WHERE tower_name=?0 " +
          "AND carrier_name=?1")
  List<CarrierTowerDAO> findByKeyTowerNameAndKeyCarrierName(final String tower_name,
                                                            final String carrier_name);

}
