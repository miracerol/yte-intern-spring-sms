package yte.intern.sms.student.controller.responses;

import yte.intern.sms.homework.entity.Homework;

public record GradesQueryModel(
        String title,
        String className,
        Integer grade) {
    public GradesQueryModel(Homework homework) {
        this(
                homework.getHomeworkPost().getTitle(),
                homework.getLesson().getLessonName(),
                homework.getGrade()
        );
    }

}
