package yte.intern.sms.examPost.controller.responses;

import yte.intern.sms.examPost.entity.ExamPost;

public record ExamPostQueryModel(
        long id,
        String title,
        long lessonId
) {
    public ExamPostQueryModel(ExamPost examPost) {
        this(
                examPost.getId(),
                examPost.getTitle(),
                examPost.getLesson().getId());
    }
}
