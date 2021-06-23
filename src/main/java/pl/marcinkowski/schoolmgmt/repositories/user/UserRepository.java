package pl.marcinkowski.schoolmgmt.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marcinkowski.schoolmgmt.entities.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);
}
