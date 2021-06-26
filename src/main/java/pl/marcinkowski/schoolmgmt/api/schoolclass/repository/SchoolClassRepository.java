package pl.marcinkowski.schoolmgmt.api.schoolclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {}
