package yte.intern.sms.homework.controller.responses;

import org.springframework.core.io.Resource;

import java.util.List;

public record HomeworkQueryModel(
        String title,
        String description,
        String deadline,
        String studentName,
        long studentId,
        List<Byte[]> files,
        Integer grade
) {
    public HomeworkQueryModel(String title, String description, String deadline, String studentName, long studentId, List<Byte[]> files, Integer grade) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.studentName = studentName;
        this.studentId = studentId;
        this.files = files;
        this.grade = grade;
    }
}
