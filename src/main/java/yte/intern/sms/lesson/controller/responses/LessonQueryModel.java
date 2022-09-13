package yte.intern.sms.lesson.controller.responses;

import yte.intern.sms.lesson.entity.Lesson;

public record LessonQueryModel(
        String lessonName,
        String description,
        String type,
        String code,
        String day,
        String startTime,
        String endTime,
        String classroomName,
        String academicianName

) {

    public LessonQueryModel(Lesson lesson) {
        this(
                lesson.getLessonName(),
                lesson.getDescription(),
                lesson.getType(),
                lesson.getCode(),
                lesson.dayConverter(),
                lesson.startTimeSlotConverter(),
                lesson.endTimeSlotConverter(),
                lesson.getClassroom().getClassroomName(),
                lesson.getInstructor().getName() + " " + lesson.getInstructor().getLastName()
        );
    }

    }