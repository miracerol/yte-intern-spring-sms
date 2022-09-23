package yte.intern.sms.homeworkPost.controller.requests;

import yte.intern.sms.homeworkPost.entity.HomeworkPost;
import yte.intern.sms.homeworkPost.repository.HomeworkPostRepository;
import yte.intern.sms.homeworkPost.service.HomeworkPostService;
import yte.intern.sms.lesson.service.LessonService;

public record AddHomeworkPostRequest(
    String title,
    String description,
    String deadline,
    long lessonId
) {
    public HomeworkPost toDomainEntity(LessonService lessonService) {
        return new HomeworkPost(title, description, deadline, lessonService.getById(lessonId));
    }
}
