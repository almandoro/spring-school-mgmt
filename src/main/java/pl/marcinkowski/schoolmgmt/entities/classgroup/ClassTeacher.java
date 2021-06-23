package pl.marcinkowski.schoolmgmt.entities.classgroup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.marcinkowski.schoolmgmt.entities.user.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ClassTeacher {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
