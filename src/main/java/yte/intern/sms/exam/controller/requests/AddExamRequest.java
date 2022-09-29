package yte.intern.sms.exam.controller.requests;

import yte.intern.sms.exam.entity.Exam;
import yte.intern.sms.examPost.service.ExamPostService;
import yte.intern.sms.lesson.service.LessonService;
import yte.intern.sms.student.service.StudentService;

public record AddExamRequest(
        long examPostId,
        long lessonId,
        long studentId,
        int grade
) {
    public Exam toDomainEntity(LessonService lessonService, StudentService studentService, ExamPostService examPostService) {
        return new Exam(grade,examPostService.getExamPostById(examPostId),  studentService.getById(studentId),lessonService.getById(lessonId));
    }
}
