package yte.intern.sms.lesson.controller.responses;

import yte.intern.sms.student.entity.Student;

public record LessonStudentResponse (
        long id,
        String name,
        String lastName
){
    public LessonStudentResponse(Student student) {
        this(student.getId(), student.getName(), student.getLastName());
    }
}
