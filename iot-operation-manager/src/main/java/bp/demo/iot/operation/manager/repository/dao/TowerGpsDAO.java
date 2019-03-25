package bp.demo.iot.operation.manager.repository.dao;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Objects;

@Table("Tower_GPS")
public class TowerGpsDAO
  implements Serializable {

  @PrimaryKeyColumn(name = "tower_name", type = PrimaryKeyType.PARTITIONED)
  private String towerName;

  @Column(value = "longitude_start")
  private String longitudeStart;

  @Column(value = "latitude_start")
  private String latitudeStart;

  @Column(value = "longitude_end")
  private String longitudeEnd;

  @Column(value = "latitude_end")
  private String latitudeEnd;

  @Column(value = "is_active")
  private boolean active;

  public TowerGpsDAO() {
  }

  public TowerGpsDAO(String towerName, String longitudeStart,
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
    TowerGpsDAO that = (TowerGpsDAO) o;
    return getActive() == that.getActive() && getTowerName().equals(that.getTowerName()) && getLongitudeStart().equals(that.getLongitudeStart()) && getLatitudeStart().equals(that.getLatitudeStart()) && getLongitudeEnd().equals(that.getLongitudeEnd()) && getLatitudeEnd().equals(that.getLatitudeEnd());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTowerName(), getLongitudeStart(),
            getLatitudeStart(), getLongitudeEnd(), getLatitudeEnd(), getActive());
  }

  @Override
  public String toString() {
    return "TowerGpsDAO{" + "towerName='" + towerName + '\'' + ", " +
            "longitudeStart='" + longitudeStart + '\'' + ", latitudeStart='" + latitudeStart + '\'' + ", longitudeEnd='" + longitudeEnd + '\'' + ", latitudeEnd='" + latitudeEnd + '\'' + ", active='" + active + '\'' + '}';
  }
}
