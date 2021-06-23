package pl.marcinkowski.schoolmgmt.entities.user;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Role role;

  @ManyToMany(mappedBy = "roles")
  private Collection<User> users;

  public UserRole(Role role) {
    this.role = role;
  }
}
