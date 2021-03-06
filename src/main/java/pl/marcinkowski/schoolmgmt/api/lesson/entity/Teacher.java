package pl.marcinkowski.schoolmgmt.api.lesson.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Teacher {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public Teacher(User user) {
    this.user = user;
  }

}
