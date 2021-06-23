package pl.marcinkowski.schoolmgmt.repositories.classgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.entities.classgroup.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {}
