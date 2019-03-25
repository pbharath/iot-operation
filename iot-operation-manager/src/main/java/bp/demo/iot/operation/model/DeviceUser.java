package bp.demo.iot.operation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class DeviceUser
  implements Serializable {

  private String carrierName;

  private String towerName;

  private String platformName;

  private String email;

  private String ssn;

  private String approvalId;

  private String firstName;

  private String lastName;

  private Date dob;

  private String deviceType;

  private String deviceId;

  private String longitude;

  private String latitude;

  private UUID id;

  private boolean active;

  private Date createdTimeStamp;

  private Date modifiedTimeStamp;

  public DeviceUser() {
  }

  public DeviceUser(String carrierName, String towerName, String platformName
          , String email, String ssn, String approvalId, String firstName,
                    String lastName, Date dob, String deviceType,
                    String deviceId, String longitude, String latitude,
                    UUID id, boolean active, Date createdTimeStamp,
                    Date modifiedTimeStamp) {
    this.carrierName = carrierName;
    this.towerName = towerName;
    this.platformName = platformName;
    this.email = email;
    this.ssn = ssn;
    this.approvalId = approvalId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.deviceType = deviceType;
    this.deviceId = deviceId;
    this.longitude = longitude;
    this.latitude = latitude;
    this.id = id;
    this.active = active;
    this.createdTimeStamp = createdTimeStamp;
    this.modifiedTimeStamp = modifiedTimeStamp;
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

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getApprovalId() {
    return approvalId;
  }

  public void setApprovalId(String approvalId) {
    this.approvalId = approvalId;
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
    DeviceUser that = (DeviceUser) o;
    return getActive() == that.getActive() && getCarrierName().equals(that.getCarrierName()) && getTowerName().equals(that.getTowerName()) && getPlatformName().equals(that.getPlatformName()) && getEmail().equals(that.getEmail()) && getSsn().equals(that.getSsn()) && getApprovalId().equals(that.getApprovalId()) && getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && getDob().equals(that.getDob()) && getDeviceType().equals(that.getDeviceType()) && getDeviceId().equals(that.getDeviceId()) && getLongitude().equals(that.getLongitude()) && getLatitude().equals(that.getLatitude()) && Objects.equals(getId(), that.getId()) && getCreatedTimeStamp().equals(that.getCreatedTimeStamp()) && Objects.equals(getModifiedTimeStamp(), that.getModifiedTimeStamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCarrierName(), getTowerName(), getPlatformName(),
            getEmail(), getSsn(), getApprovalId(), getFirstName(),
            getLastName(), getDob(), getDeviceType(), getDeviceId(),
            getLongitude(), getLatitude(), getId(), getActive(),
            getCreatedTimeStamp(), getModifiedTimeStamp());
  }

  @Override
  public String toString() {
    return "DeviceUser{" + "carrierName='" + carrierName + '\'' + ", " +
            "towerName='" + towerName + '\'' + ", platformName='" + platformName + '\'' + ", email='" + email + '\'' + ", ssn='" + ssn + '\'' + ", approvalId='" + approvalId + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", dob='" + dob + '\'' + ", deviceType='" + deviceType + '\'' + ", deviceId='" + deviceId + '\'' + ", longitude='" + longitude + '\'' + ", latitude='" + latitude + '\'' + ", id=" + id + ", active=" + active + ", createdTimeStamp=" + createdTimeStamp + ", modifiedTimeStamp=" + modifiedTimeStamp + '}';
  }

  public String response(){
    return "Id = " + id;
  }
}
