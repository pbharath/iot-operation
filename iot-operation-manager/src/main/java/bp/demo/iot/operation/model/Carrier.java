package bp.demo.iot.operation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Carrier
  implements Serializable {

  private String name;

  private UUID id;

  private boolean active;

  private Date timeStamp;

  public Carrier() {
  }

  public Carrier(String name, UUID id, boolean active, Date timeStamp) {
    this.name = name;
    this.id = id;
    this.active = active;
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

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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
    Carrier carrier = (Carrier) o;
    return getActive() == carrier.getActive() && getName().equals(carrier.getName()) && getId().equals(carrier.getId()) && getTimeStamp().equals(carrier.getTimeStamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getId(), getActive(), getTimeStamp());
  }

  @Override
  public String toString() {
    return "Carrier{" + "name='" + name + '\'' + ", id=" + id + ", active=" + active + ", timeStamp=" + timeStamp + '}';
  }
}
