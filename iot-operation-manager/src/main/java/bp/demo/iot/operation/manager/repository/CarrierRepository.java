package bp.demo.iot.operation.manager.repository;

import bp.demo.iot.operation.manager.repository.dao.CarrierDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarrierRepository
        extends CassandraRepository<CarrierDAO, String> {

  @Query("SELECT * FROM IotOperationSpace.Carrier WHERE name = ?0")
  List<CarrierDAO> findByName(String name);

  @Query("SELECT * FROM IotOperationSpace.Carrier WHERE id = ?0")
  List<CarrierDAO> findByCarrierId(UUID id);
}
