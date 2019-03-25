package bp.demo.iot.operation.manager.repository.dao;

import bp.demo.iot.operation.manager.repository.dao.pk.CarrierTowerPrimaryKeyDAO;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Table("Carrier_Tower")
public class CarrierTowerDAO
        implements Serializable {

  @PrimaryKey
  private CarrierTowerPrimaryKeyDAO keyDAO;

  @Column(value = "is_active")
  private boolean isActive;

  @Column(value = "timeStamp")
  private Date timeStamp;

  public CarrierTowerDAO() {
  }

  public CarrierTowerDAO(CarrierTowerPrimaryKeyDAO keyDAO,
                         boolean isActive,
                         Date timeStamp) {
    this.keyDAO = keyDAO;
    this.isActive = isActive;
    this.timeStamp = timeStamp;
  }

  public CarrierTowerPrimaryKeyDAO getKeyDAO() {
    return keyDAO;
  }

  public void setKeyDAO(CarrierTowerPrimaryKeyDAO keyDAO) {
    this.keyDAO = keyDAO;
  }

  public boolean getActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  @Override
  public String toString() {
    return "CarrierTowerEvent{" + "keyDAO=" + keyDAO + ", getActive=" + isActive + ", " +
            "timeStamp=" + timeStamp + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CarrierTowerDAO that = (CarrierTowerDAO) o;
    return getActive() == that.getActive() && getKeyDAO().equals(that.getKeyDAO()) && getTimeStamp().equals(that.getTimeStamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKeyDAO(), getActive(), getTimeStamp());
  }
}
