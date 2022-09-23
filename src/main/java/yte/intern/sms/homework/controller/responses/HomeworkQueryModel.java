package yte.intern.sms.homework.controller.responses;

import org.springframework.core.io.Resource;

import java.util.List;

public record HomeworkQueryModel(

        String description,
        String studentName,
        long studentId,
        List<byte[]> files,
        Integer grade
) {
    public HomeworkQueryModel( String description, String studentName, long studentId, List<byte[]> files, Integer grade) {
        this.description = description;
        this.studentName = studentName;
        this.studentId = studentId;
        this.files = files;
        this.grade = grade;
    }
}
