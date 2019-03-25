package bp.demo.iot.operation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CarrierTower
  implements Serializable {

  private String carrierName;

  private String towerName;

  private boolean active;

  private Date timeStamp;

  public CarrierTower() {
  }

  public CarrierTower(String carrierName, String towerName, boolean active,
                      Date timeStamp) {
    this.carrierName = carrierName;
    this.towerName = towerName;
    this.active = active;
    this.timeStamp = timeStamp;
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
    CarrierTower that = (CarrierTower) o;
    return getActive() == that.getActive() && getCarrierName().equals(that.getCarrierName()) && getTowerName().equals(that.getTowerName()) && getTimeStamp().equals(that.getTimeStamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCarrierName(), getTowerName(), getActive(), getTimeStamp());
  }

  @Override
  public String toString() {
    return "CarrierTower{" + "carrierName='" + carrierName + '\'' + ", " +
            "towerName='" + towerName + '\'' + ", active=" + active + ", " +
            "timeStamp=" + timeStamp + '}';
  }
}
