package yte.intern.sms.lesson.controller.responses;

import yte.intern.sms.lesson.entity.Lesson;
import yte.intern.sms.lesson.service.LessonService;

import java.util.List;

public record LessonDetailResponse(
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
        long academicianId,
        List<LessonAssistantResponse> assistants,
        List<LessonStudentResponse> students,
        int studentCount,
        int assistantCount

) {
    public LessonDetailResponse(Lesson lesson, LessonService lessonService) {
        this(
                lesson.getId(),
                lesson.getLessonName(),
                lesson.getDescription(),
                lesson.getType(),
                lesson.getCode(),
                lesson.getScheduleText(),
                lesson.getSchedule(),
                lesson.getClassroom() != null ? lesson.getClassroom().getClassroomName() : "No Classroom",
                lesson.getClassroom() != null ? lesson.getClassroom().getId() : 0,
                lesson.getInstructor() != null ? (lesson.getInstructor().getName() + " " + lesson.getInstructor().getLastName()) : "No Instructor",
                lesson.getInstructor() != null ? lesson.getInstructor().getId() : 0,
                LessonAssistantResponse.toLessonAssistantResponseList(lesson.getAssistants()),
                LessonStudentResponse.toLessonStudentResponseList(lessonService.getStudentsByLessonId(lesson.getId())),
                lessonService.getStudentsByLessonId(lesson.getId()).size(),
                lesson.getAssistants().size()
        );
    }
}
