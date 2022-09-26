package yte.intern.sms.homework.controller.responses;

import org.springframework.core.io.Resource;

import java.util.List;

public record HomeworkQueryModel(
        long id,
        String description,
        String studentName,
        long studentId,
        long homeworkPostId,
        List<String> fileNames,
        Integer grade
) {
    public HomeworkQueryModel(long id, String description, String studentName, long studentId, long homeworkPostId,List<String> fileNames, Integer grade) {
        this.id = id;
        this.description = description;
        this.studentName = studentName;
        this.studentId = studentId;
        this.homeworkPostId = homeworkPostId;
        this.fileNames = fileNames;
        this.grade = grade;
    }
}
