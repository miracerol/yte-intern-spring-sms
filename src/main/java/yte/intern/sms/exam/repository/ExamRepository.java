package yte.intern.sms.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.exam.entity.Exam;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findAllByLessonId(Long lessonId);
    List<Exam> findAllByStudentId(Long lessonId);
    Exam findByLessonIdAndStudentId(Long lessonId, Long studentId);
    Exam findByStudentIdAndExamPostId(Long studentId, Long examPostId);


}

