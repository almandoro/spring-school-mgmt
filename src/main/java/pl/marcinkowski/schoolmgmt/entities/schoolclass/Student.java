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

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
