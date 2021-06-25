package pl.marcinkowski.schoolmgmt.entities.schoolclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.marcinkowski.schoolmgmt.entities.lesson.Teacher;

import javax.persistence.*;
import java.util.Collection;

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

  public SchoolClass(Integer grade, String className, Teacher teacher) {
    this.grade = grade;
    this.className = className;
  }

  @ManyToMany
  @JoinTable(
      name = "class_attendance",
      joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "schoolclass_id", referencedColumnName = "id"))
  private Collection<Student> students;

  @Override
  public String toString() {
    return "SchoolClass{" + ", grade=" + grade + ", className='" + className + '\'' + '}';
  }
}
