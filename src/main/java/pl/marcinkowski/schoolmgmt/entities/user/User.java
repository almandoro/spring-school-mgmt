package pl.marcinkowski.schoolmgmt.entities.user;

import lombok.*;
import pl.marcinkowski.schoolmgmt.entities.classgroup.ClassTeacher;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  private String password;

  public User(
      String firstName,
      String lastName,
      String email,
      String password,
      Collection<UserRole> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "userrole_id", referencedColumnName = "id"))
  private Collection<UserRole> roles;

  @OneToOne(mappedBy = "user")
  private ClassTeacher classTeacher;
}
