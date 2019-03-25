package bp.demo.iot.operation.manager.repository.dao;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table("Content_By_Age_Policy_Rule")
public class ContentByAgePolicyRuleDAO
        implements Serializable {

  @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
  private UUID id;

  @Column(value = "start_age")
  private int startAge;

  @Column(value = "end_age")
  private int endAge;

  @Column(value = "content_types")
  private Set<String> contentSet;

  public ContentByAgePolicyRuleDAO() {
  }

  public ContentByAgePolicyRuleDAO(UUID id, int startAge, int endAge,
                                   Set<String> contentSet) {
    this.id = id;
    this.startAge = startAge;
    this.endAge = endAge;
    this.contentSet = contentSet;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getStartAge() {
    return startAge;
  }

  public void setStartAge(int startAge) {
    this.startAge = startAge;
  }

  public int getEndAge() {
    return endAge;
  }

  public void setEndAge(int endAge) {
    this.endAge = endAge;
  }

  public Set<String> getContentSet() {
    return contentSet;
  }

  public void setContentSet(Set<String> contentSet) {
    this.contentSet = contentSet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContentByAgePolicyRuleDAO that = (ContentByAgePolicyRuleDAO) o;
    return getStartAge() == that.getStartAge() && getEndAge() == that.getEndAge() && getId().equals(that.getId()) && getContentSet().equals(that.getContentSet());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getStartAge(), getEndAge(), getContentSet());
  }

  @Override
  public String toString() {
    return "ContentByAgePolicyRuleDAO{" + "id=" + id + ", startAge=" + startAge + ", endAge=" + endAge + ", contentSet=" + contentSet + '}';
  }
}
