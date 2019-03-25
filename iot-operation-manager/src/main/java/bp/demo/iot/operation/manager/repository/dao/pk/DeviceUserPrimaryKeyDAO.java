package bp.demo.iot.operation.manager.repository.dao.pk;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

@PrimaryKeyClass
public class DeviceUserPrimaryKeyDAO
        implements Serializable {

  @PrimaryKeyColumn(name = "carrier_name", type = PrimaryKeyType.PARTITIONED)
  private String carrierName;

  @PrimaryKeyColumn(name = "ssn", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
  private String ssn;

  @PrimaryKeyColumn(name = "email", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
  private String email;

  public DeviceUserPrimaryKeyDAO() {
  }

  public DeviceUserPrimaryKeyDAO(String carrierName, String ssn, String email) {
    this.carrierName = carrierName;
    this.ssn = ssn;
    this.email = email;
  }

  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeviceUserPrimaryKeyDAO that = (DeviceUserPrimaryKeyDAO) o;
    return getCarrierName().equals(that.getCarrierName()) &&
            getSsn().equals(that.getSsn()) &&
            getEmail().equals(that.getEmail());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCarrierName(), getSsn(), getEmail());
  }

  @Override
  public String toString() {
    return "DeviceUserPrimaryKeyDAO{" +
            "carrierName='" + carrierName + '\'' +
            ", ssn='" + ssn + '\'' +
            ", email='" + email + '\'' + '}';
  }

}
