package pl.marcinkowski.schoolmgmt.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl {

  @Bean
  PasswordEncoder getEncoder() {
    return new BCryptPasswordEncoder();
  }
}
