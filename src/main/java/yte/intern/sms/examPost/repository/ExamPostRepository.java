package yte.intern.sms.examPost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.examPost.entity.ExamPost;

import java.util.List;

public interface ExamPostRepository extends JpaRepository<ExamPost, Long> {
    List<ExamPost> findAllByLessonId(Long lessonId);
}




