package yte.intern.sms.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.lesson.entity.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Override
    Optional<Lesson> findById(Long id);

    @Override
    List<Lesson> findAll();

    List<Lesson> findAllByClassroomId(Long classroomId);


}
