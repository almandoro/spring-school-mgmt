package pl.marcinkowski.schoolmgmt.entities.schoolclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.marcinkowski.schoolmgmt.entities.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(mappedBy = "students")
  private Collection<SchoolClass> schoolClasses;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public Student(User user) {
    this.user = user;
  }

  public Student(Collection<SchoolClass> schoolClasses, User user) {
    this.schoolClasses = schoolClasses;
    this.user = user;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", schoolClasses=" + schoolClasses +
            ", user=" + user.getFirstName() + user.getLastName() +
            '}';
  }
}
