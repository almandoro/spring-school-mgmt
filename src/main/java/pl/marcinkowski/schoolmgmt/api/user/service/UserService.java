package pl.marcinkowski.schoolmgmt.api.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;
import pl.marcinkowski.schoolmgmt.api.user.repository.UserRepository;

import javax.persistence.EntityExistsException;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getAuthUser(Authentication user) throws EntityExistsException {
    if (user == null) return null;
    String userEmail = user.getName();
    User foundUser = userRepository.getByEmail(userEmail);

    return foundUser;
  }
}
