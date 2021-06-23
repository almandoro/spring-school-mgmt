package pl.marcinkowski.schoolmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SchoolMgmtApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchoolMgmtApplication.class, args);
  }
}
