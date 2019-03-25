package bp.demo.iot.operation.manager.repository.dao;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Table("Carrier")
public class CarrierDAO
  implements Serializable {

  @PrimaryKeyColumn(name = "name", ordinal = 0, type =
          PrimaryKeyType.PARTITIONED)
  private String name;

  @Column(value = "id")
  private UUID id;

  @Column(value = "is_active")
  private boolean isActive;

  @Column(value = "timeStamp")
  private Date timeStamp;

  public CarrierDAO() {
  }

  public CarrierDAO(String name, UUID id, boolean isActive,
                    Date timeStamp) {
    this.name = name;
    this.id = id;
    this.isActive = isActive;
    this.timeStamp = timeStamp;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public boolean isActive() {
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CarrierDAO carrierDAO = (CarrierDAO) o;
    return getName().equals(carrierDAO.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
