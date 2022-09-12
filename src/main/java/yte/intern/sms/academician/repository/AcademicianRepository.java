package yte.intern.sms.academician.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.academician.entity.Academician;

import java.util.List;
import java.util.Optional;

public interface AcademicianRepository  extends JpaRepository<Academician, Long> {
    Optional<Academician> findAcademicianById(Long id);

    List<Academician> findAll();
}
