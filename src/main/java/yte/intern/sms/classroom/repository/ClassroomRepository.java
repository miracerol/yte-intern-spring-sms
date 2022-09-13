package yte.intern.sms.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.classroom.entity.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    @Override
    Optional<Classroom> findById(Long id);

    @Override
    List<Classroom> findAll();


}
