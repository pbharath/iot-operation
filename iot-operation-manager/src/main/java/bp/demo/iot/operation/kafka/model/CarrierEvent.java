package bp.demo.iot.operation.kafka.model;

import java.io.Serializable;

public class CarrierEvent
  implements Serializable {

  private String name;

  private boolean isActive;

  public CarrierEvent() { }

  public CarrierEvent(String name, boolean isActive) {
    this.name = name;
    this.isActive = isActive;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @Override
  public String toString() {
    return "CarrierEvent{" + "name='" + name + '\'' + ", getActive=" + isActive + '}';
  }
}
