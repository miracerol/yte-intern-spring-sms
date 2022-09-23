package yte.intern.sms.homeworkPost.controller.responses;

import yte.intern.sms.homeworkPost.entity.HomeworkPost;

public record HomeworkPostQueryModel(
        long id,
        String title,
        String description,
        String deadline,
        long lessonId
) {
    public HomeworkPostQueryModel(HomeworkPost homeworkPost) {
        this(
                homeworkPost.getId(),
                homeworkPost.getTitle(),
                homeworkPost.getDescription(),
                homeworkPost.getDeadline(),
                homeworkPost.getLesson().getId());
    }
}