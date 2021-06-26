package pl.marcinkowski.schoolmgmt.api.schoolclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.ScheduledLesson;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class SchoolClass {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private Integer grade;
  private String className;

  @ManyToMany
  @JoinTable(
      name = "class_attendance",
      joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "schoolclass_id", referencedColumnName = "id"))
  private Collection<Student> students;

  @JsonIgnore
  @OneToMany(mappedBy = "schoolClass")
  private List<ScheduledLesson> scheduledLessons;

  public SchoolClass(Integer grade, String className) {
    this.grade = grade;
    this.className = className;
  }

  @Override
  public String toString() {
    return "SchoolClass{" + ", grade=" + grade + ", className='" + className + '\'' + '}';
  }
}
