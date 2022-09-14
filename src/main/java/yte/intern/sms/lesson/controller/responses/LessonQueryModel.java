package yte.intern.sms.lesson.controller.responses;

import yte.intern.sms.lesson.entity.Lesson;

import java.util.List;

public record LessonQueryModel(
        long id,
        String lessonName,
        String description,
        String type,
        String code,
        List<String> schedule,
        List<String> slots,
        String classroomName,
        String academicianName

) {

    public LessonQueryModel(Lesson lesson) {
        this(
                lesson.getId(),
                lesson.getLessonName(),
                lesson.getDescription(),
                lesson.getType(),
                lesson.getCode(),
                lesson.getScheduleText(),
                lesson.getSchedule(),
                lesson.getClassroom().getClassroomName(),
                lesson.getInstructor().getName() + " " + lesson.getInstructor().getLastName()
        );
    }

    }