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
        long classroomId,
        String academicianName,
        long academicianId

) {
    public LessonQueryModel(Lesson lesson) {
        this(
                lesson.getId(),
                lesson.getLessonName(),
                lesson.getDescription(),
                lesson.getType(),
                lesson.getCode(),
                lesson.getScheduleText() != null ? lesson.getScheduleText() : List.of(),
                lesson.getSchedule() != null ? lesson.getSchedule() : List.of(),
                lesson.getClassroom() != null ? lesson.getClassroom().getClassroomName() : "No Classroom",
                lesson.getClassroom() != null ? lesson.getClassroom().getId() : 0,
                lesson.getInstructor() != null ? (lesson.getInstructor().getName() + " " + lesson.getInstructor().getLastName()) : "No Instructor",
                lesson.getInstructor() != null ? lesson.getInstructor().getId() : 0
        );
    }

    }