package pl.marcinkowski.schoolmgmt.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);
}
