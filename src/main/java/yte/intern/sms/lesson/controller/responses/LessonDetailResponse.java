package yte.intern.sms.lesson.controller.responses;

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
        List<LessonStudentResponse> students

) {

}
