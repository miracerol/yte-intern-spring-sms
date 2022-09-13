package yte.intern.sms.lesson.controller.responses;

import yte.intern.sms.lesson.entity.Lesson;

public record LessonQueryModel(
        String lessonName,
        String description,
        String type,
        String code,
        Integer day,
        Integer timeSlot,
        Long classroomId,
        Long academicianId

) {

    public LessonQueryModel(Lesson lesson) {
        this(
                lesson.getLessonName(),
                lesson.getDescription(),
                lesson.getType(),
                lesson.getCode(),
                lesson.getDay(),
                lesson.getTimeSlot(),
                lesson.getClassroom().getId(),
                lesson.getInstructor().getId()
        );
    }

}