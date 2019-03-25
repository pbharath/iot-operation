package bp.demo.iot.operation.manager.repository.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import bp.demo.iot.operation.manager.repository.dao.pk.DeviceUserPrimaryKeyDAO;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("Device_User")
public class DeviceUserDAO
        implements Serializable {

  @PrimaryKey
  private DeviceUserPrimaryKeyDAO keyDAO;

  @Column(value = "tower_name")
  private String towerName;

  @Column(value = "platform_name")
  private String platformName;

  @Column(value = "approval_id")
  private String approvalId;

  @Column(value = "id")
  private UUID id;

  @Column(value = "first_name")
  private String firstName;

  @Column(value = "last_name")
  private String lastName;

  @Column(value = "date_of_birth")
  private Date dob;

  @Column(value = "device_type")
  private String deviceType;

  @Column(value = "device_id")
  private String deviceId;

  @Column(value = "longitude")
  private String longitude;

  @Column(value = "latitude")
  private String latitude;

  @Column(value = "is_active")
  private boolean active;

  @Column(value = "created_timeStamp")
  private Date createdTimeStamp;

  @Column(value = "modified_timeStamp")
  private Date modifiedTimeStamp;

  public DeviceUserDAO() {
  }

  public DeviceUserDAO(DeviceUserPrimaryKeyDAO keyDAO, String towerName,
                       String platformName, String approvalId, UUID id,
                       String firstName, String lastName, Date dob,
                       String deviceType, String deviceId, String longitude,
                       String latitude, boolean active, Date createdTimeStamp
          , Date modifiedTimeStamp) {
    this.keyDAO = keyDAO;
    this.towerName = towerName;
    this.platformName = platformName;
    this.approvalId = approvalId;
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.deviceType = deviceType;
    this.deviceId = deviceId;
    this.longitude = longitude;
    this.latitude = latitude;
    this.active = active;
    this.createdTimeStamp = createdTimeStamp;
    this.modifiedTimeStamp = modifiedTimeStamp;
  }

  public DeviceUserPrimaryKeyDAO getKeyDAO() {
    return keyDAO;
  }

  public void setKeyDAO(DeviceUserPrimaryKeyDAO keyDAO) {
    this.keyDAO = keyDAO;
  }

  public String getTowerName() {
    return towerName;
  }

  public void setTowerName(String towerName) {
    this.towerName = towerName;
  }

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  public String getApprovalId() {
    return approvalId;
  }

  public void setApprovalId(String approvalId) {
    this.approvalId = approvalId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Date getCreatedTimeStamp() {
    return createdTimeStamp;
  }

  public void setCreatedTimeStamp(Date createdTimeStamp) {
    this.createdTimeStamp = createdTimeStamp;
  }

  public Date getModifiedTimeStamp() {
    return modifiedTimeStamp;
  }

  public void setModifiedTimeStamp(Date modifiedTimeStamp) {
    this.modifiedTimeStamp = modifiedTimeStamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeviceUserDAO that = (DeviceUserDAO) o;
    return getActive() == that.getActive() && getKeyDAO().equals(that.getKeyDAO()) && getTowerName().equals(that.getTowerName()) && getPlatformName().equals(that.getPlatformName()) && getApprovalId().equals(that.getApprovalId()) && Objects.equals(getId(), that.getId()) && getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && getDob().equals(that.getDob()) && getDeviceType().equals(that.getDeviceType()) && getDeviceId().equals(that.getDeviceId()) && getLongitude().equals(that.getLongitude()) && getLatitude().equals(that.getLatitude()) && getCreatedTimeStamp().equals(that.getCreatedTimeStamp()) && Objects.equals(getModifiedTimeStamp(), that.getModifiedTimeStamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKeyDAO(), getTowerName(), getPlatformName(),
            getApprovalId(), getId(), getFirstName(), getLastName(), getDob()
            , getDeviceType(), getDeviceId(), getLongitude(), getLatitude(),
            getActive(), getCreatedTimeStamp(), getModifiedTimeStamp());
  }

  @Override
  public String toString() {
    return "DeviceUserDAO{" + "keyDAO=" + keyDAO + ", towerName='" + towerName + '\'' + ", platformName='" + platformName + '\'' + ", approvalId=" + approvalId + ", id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", dob='" + dob + '\'' + ", deviceType='" + deviceType + '\'' + ", deviceId='" + deviceId + '\'' + ", longitude='" + longitude + '\'' + ", latitude='" + latitude + '\'' + ", active=" + active + ", createdTimeStamp=" + createdTimeStamp + ", modifiedTimeStamp=" + modifiedTimeStamp + '}';
  }
}