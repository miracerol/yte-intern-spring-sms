package yte.intern.sms.homework.controller.requests;

import org.springframework.web.multipart.MultipartFile;
import yte.intern.sms.homework.entity.Homework;
import yte.intern.sms.homework.service.HomeworkService;
import yte.intern.sms.homeworkPost.service.HomeworkPostService;
import yte.intern.sms.lesson.service.LessonService;
import yte.intern.sms.student.service.StudentService;



public record AddHomeworkRequest (

        String description,
        long homeworkPostId,
        long lessonId,
        long studentId
){
    public Homework toDomainEntity(MultipartFile[] files, HomeworkService homeworkService,
                                   LessonService lessonService, StudentService studentService,
                                   HomeworkPostService homeworkPostService) {
        System.out.println("files: " + files);
        return new Homework( description,homeworkService.saveFiles(files),homeworkPostService.getHomeworkPostById(homeworkPostId), studentService.getById(studentId),lessonService.getById(lessonId),null);
    }

}
