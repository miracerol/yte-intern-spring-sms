package yte.intern.sms.homework.controller.requests;

import org.springframework.web.multipart.MultipartFile;
import yte.intern.sms.homework.entity.Homework;
import yte.intern.sms.homework.service.HomeworkService;
import yte.intern.sms.lesson.service.LessonService;
import yte.intern.sms.student.service.StudentService;



public record AddHomeworkRequest (

        String title,
        String description,
        String deadline,
        long lessonId,
        long studentId
){
    public Homework toDomainEntity(MultipartFile[] files, HomeworkService homeworkService,
                                   LessonService lessonService, StudentService studentService) {
        System.out.println("files: " + files);
        return new Homework(title, description,deadline,homeworkService.saveFiles(files), studentService.getById(studentId),lessonService.getById(lessonId),null);
    }

}
