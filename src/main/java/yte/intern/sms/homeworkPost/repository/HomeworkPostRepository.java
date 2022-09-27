package yte.intern.sms.homeworkPost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.homeworkPost.entity.HomeworkPost;

import java.util.List;

public interface HomeworkPostRepository extends JpaRepository<HomeworkPost, Long> {
    List<HomeworkPost> findAllByLessonIdOrderByDeadline(Long lessonId);
}

