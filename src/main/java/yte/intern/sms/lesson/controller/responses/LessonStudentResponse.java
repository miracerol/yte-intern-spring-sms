package yte.intern.sms.lesson.controller.responses;

import yte.intern.sms.assistant.entity.Assistant;
import yte.intern.sms.student.entity.Student;

import java.util.List;

public record LessonStudentResponse (
        long id,
        String name,
        String lastName
){
    public LessonStudentResponse(Student student) {
        this(student.getId(), student.getName(), student.getLastName());
    }
    public static List<LessonStudentResponse> toLessonStudentResponseList(List<Student> students) {
        return students.stream().map(LessonStudentResponse::new).toList();
    }
}
