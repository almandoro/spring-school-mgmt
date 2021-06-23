package pl.marcinkowski.schoolmgmt.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.marcinkowski.schoolmgmt.entities.user.Role;
import pl.marcinkowski.schoolmgmt.entities.user.UserRole;

import java.util.List;

@EnableJpaRepositories
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

  List<UserRole> findAllByRole(Role role);
}
