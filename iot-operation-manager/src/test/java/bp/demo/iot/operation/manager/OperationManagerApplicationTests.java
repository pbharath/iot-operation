package bp.demo.iot.operation.manager;

import bp.demo.iot.operation.manager.repository.dao.DeviceUserDAO;
import bp.demo.iot.operation.manager.repository.DeviceUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationManagerApplicationTests {

  @Autowired
  DeviceUserRepository deviceUserRepository;

  private static final UUID carrier_id_1 = UUID.randomUUID();
  private static final String ssn_1 = "123-456-7890";
  private static final String email_1 = "fn1.ln1@gmail.com";

  @Test
  public void contextLoads() {
//    UUID id = UUID.randomUUID();
//    DeviceUserDAO deviceUserDAO = new DeviceUserDAO(carrier_id_1, ssn_1, email_1,
//            id, "fn1", "ln1",24, true, new Date());
//    DeviceUserDAO du = deviceUserRepository.insert(deviceUserDAO);
//    assert(du.getId().toString().equals(id.toString()));
  }


}
