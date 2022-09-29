package yte.intern.sms.examPost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;
import yte.intern.sms.examPost.entity.ExamPost;
import yte.intern.sms.examPost.repository.ExamPostRepository;
import yte.intern.sms.homeworkPost.entity.HomeworkPost;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamPostService {
    private final ExamPostRepository examPostRepository;

    public MessageResponse addExamPost(ExamPost examPost) {
        examPostRepository.save(examPost);
        return new MessageResponse(ResponseType.SUCCESS, "Exam has been added successfully");
    }

    public MessageResponse updateExamPost(long id, ExamPost updatedExamPost) {
        ExamPost examPost = examPostRepository.findById(id).get();
        examPost.update(updatedExamPost);
        examPostRepository.save(examPost);
        return new MessageResponse(ResponseType.SUCCESS, "Exam has been updated successfully");
    }

    public ExamPost getExamPostById(Long id) {
        return examPostRepository.findById(id).get();
    }

    public List<ExamPost> getExamPostsByLessonId(Long lessonId) {
        return examPostRepository.findAllByLessonId(lessonId);
    }
}
