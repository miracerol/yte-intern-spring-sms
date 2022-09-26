package yte.intern.sms.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.homework.entity.Homework;

import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {

    @Override
    Optional<Homework> findById(Long id);

    @Override
    void deleteById(Long id);

    List<Homework> findAllByStudentId(Long studentId);

    List<Homework> findAllByLessonId(Long lessonId);

    Homework findByLessonIdAndStudentId(Long lessonId, Long studentId);

    Homework findByStudentIdAndHomeworkPostId(Long studentId, Long homeworkPostId);


}
