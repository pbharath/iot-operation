package bp.demo.iot.operation.manager.repository;

import bp.demo.iot.operation.manager.repository.dao.TowerGpsDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

public interface TowerGpsRepository
        extends CassandraRepository<TowerGpsDAO, String> {

  @Query("SELECT * FROM IotOperationSpace.Tower_GPS WHERE tower_name = ?0")
  List<TowerGpsDAO> findByTowerName(String name);


}
