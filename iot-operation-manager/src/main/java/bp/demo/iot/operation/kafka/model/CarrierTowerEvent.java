package bp.demo.iot.operation.kafka.model;

import java.io.Serializable;
import java.util.Objects;

public class CarrierTowerEvent
  implements Serializable {

  private String carrierName;

  private String towerName;

  private boolean isActive;

  public CarrierTowerEvent() {
  }

  public CarrierTowerEvent(String carrierName, String towerName, boolean isActive) {
    this.carrierName = carrierName;
    this.towerName = towerName;
    this.isActive = isActive;
  }

  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getTowerName() {
    return towerName;
  }

  public void setTowerName(String towerName) {
    this.towerName = towerName;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @Override
  public String toString() {
    return "CarrierTowerEvent{" + "carrierName='" + carrierName + '\'' + ", " +
            "towerName='" + towerName + '\'' + ", getActive=" + isActive + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CarrierTowerEvent that = (CarrierTowerEvent) o;
    return isActive == that.isActive && carrierName.equals(that.carrierName) && towerName.equals(that.towerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(carrierName, towerName, isActive);
  }
}
