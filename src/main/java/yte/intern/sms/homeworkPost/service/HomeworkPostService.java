package yte.intern.sms.homeworkPost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.homeworkPost.entity.HomeworkPost;
import yte.intern.sms.homeworkPost.repository.HomeworkPostRepository;
import yte.intern.sms.common.response.ResponseType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkPostService {

    private final HomeworkPostRepository homeworkPostRepository;

    public MessageResponse addHomeworkPost(HomeworkPost homeworkPost) {
        homeworkPostRepository.save(homeworkPost);
        return new MessageResponse(ResponseType.SUCCESS, "Homework has been added successfully");
    }

    public MessageResponse updateHomeworkPost(long id,HomeworkPost updatedHomeworkPost) {
        HomeworkPost homeworkPost = homeworkPostRepository.findById(id).get();
        homeworkPost.update(updatedHomeworkPost);
        homeworkPostRepository.save(homeworkPost);
        return new MessageResponse(ResponseType.SUCCESS, "Homework has been updated successfully");
    }

    public HomeworkPost getHomeworkPostById(Long id) {
        return homeworkPostRepository.findById(id).get();
    }

    public void deleteHomeworkPostById(Long id) {
        homeworkPostRepository.deleteById(id);
    }

    public List<HomeworkPost> getHomeworkPostsByLessonId(Long lessonId) {
        return homeworkPostRepository.findAllByLessonIdOrderByDeadline(lessonId);
    }


}
