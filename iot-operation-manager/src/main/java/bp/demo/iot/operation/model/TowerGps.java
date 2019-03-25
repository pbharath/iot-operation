package bp.demo.iot.operation.model;

import java.io.Serializable;
import java.util.Objects;

public class TowerGps
  implements Serializable {

  private String towerName;

  private String longitudeStart;

  private String latitudeStart;

  private String longitudeEnd;

  private String latitudeEnd;

  private boolean active;

  public TowerGps() {
  }

  public TowerGps(String towerName, String longitudeStart,
                  String latitudeStart, String longitudeEnd,
                  String latitudeEnd, boolean active) {
    this.towerName = towerName;
    this.longitudeStart = longitudeStart;
    this.latitudeStart = latitudeStart;
    this.longitudeEnd = longitudeEnd;
    this.latitudeEnd = latitudeEnd;
    this.active = active;
  }

  public String getTowerName() {
    return towerName;
  }

  public void setTowerName(String towerName) {
    this.towerName = towerName;
  }

  public String getLongitudeStart() {
    return longitudeStart;
  }

  public void setLongitudeStart(String longitudeStart) {
    this.longitudeStart = longitudeStart;
  }

  public String getLatitudeStart() {
    return latitudeStart;
  }

  public void setLatitudeStart(String latitudeStart) {
    this.latitudeStart = latitudeStart;
  }

  public String getLongitudeEnd() {
    return longitudeEnd;
  }

  public void setLongitudeEnd(String longitudeEnd) {
    this.longitudeEnd = longitudeEnd;
  }

  public String getLatitudeEnd() {
    return latitudeEnd;
  }

  public void setLatitudeEnd(String latitudeEnd) {
    this.latitudeEnd = latitudeEnd;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TowerGps towerGps = (TowerGps) o;
    return getActive() == towerGps.getActive() && getTowerName().equals(towerGps.getTowerName()) && getLongitudeStart().equals(towerGps.getLongitudeStart()) && getLatitudeStart().equals(towerGps.getLatitudeStart()) && getLongitudeEnd().equals(towerGps.getLongitudeEnd()) && getLatitudeEnd().equals(towerGps.getLatitudeEnd());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTowerName(), getLongitudeStart(),
            getLatitudeStart(), getLongitudeEnd(), getLatitudeEnd(), getActive());
  }

  @Override
  public String toString() {
    return "TowerGps{" + "towerName='" + towerName + '\'' + ", longitudeStart" +
            "='" + longitudeStart + '\'' + ", latitudeStart='" + latitudeStart + '\'' + ", longitudeEnd='" + longitudeEnd + '\'' + ", latitudeEnd='" + latitudeEnd + '\'' + ", active=" + active + '}';
  }
}
