package bp.demo.iot.operation.manager.repository;

import bp.demo.iot.operation.manager.repository.dao.DeviceUserDAO;
import bp.demo.iot.operation.manager.repository.dao.pk.DeviceUserPrimaryKeyDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceUserRepository
  extends CassandraRepository<DeviceUserDAO, DeviceUserPrimaryKeyDAO> {

  @Query("SELECT * FROM IotOperationSpace.Device_User WHERE carrier_name=?0 " +
          "AND ssn=?1 AND email=?2")
  List<DeviceUserDAO> findByCompositePrimaryKey(String carrierName,
                                                       String ssn, String email);

}
