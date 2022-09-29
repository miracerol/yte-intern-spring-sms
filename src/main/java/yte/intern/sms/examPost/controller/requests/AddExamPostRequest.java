package yte.intern.sms.examPost.controller.requests;

import yte.intern.sms.examPost.entity.ExamPost;
import yte.intern.sms.lesson.service.LessonService;

public record AddExamPostRequest(
        String title,
        long lessonId
) {
    public ExamPost toDomainEntity(LessonService lessonService) {
        return new ExamPost(title, lessonService.getById(lessonId));
    }

}
