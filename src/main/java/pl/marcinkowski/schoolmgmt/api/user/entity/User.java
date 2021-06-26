package pl.marcinkowski.schoolmgmt.api.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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
  @JsonIgnore
  private String password;

  @Enumerated
  private UserRole role;

//  @OneToOne(mappedBy = "user")
//  private Teacher teacher;
//
//  @OneToOne(mappedBy = "user")
//  private Student student;

  public User(String firstName, String lastName, String email, String password, UserRole role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
  }
}
