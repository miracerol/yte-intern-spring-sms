package yte.intern.sms.student.controller.responses;

import yte.intern.sms.exam.entity.Exam;
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

    public GradesQueryModel(Exam exam) {
        this(
                exam.getExamPost().getTitle(),
                exam.getLesson().getLessonName(),
                exam.getGrade()
        );
    }
}
