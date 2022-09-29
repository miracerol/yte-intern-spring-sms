package yte.intern.sms.exam.controller.responses;

public record ExamQueryModel(
        long id,
        int grade,
        long examPostId,
        String studentName,
        long studentId


) {
    public ExamQueryModel(long id, int grade, long examPostId, String studentName, long studentId) {
        this.id = id;
        this.grade = grade;
        this.examPostId = examPostId;
        this.studentName = studentName;
        this.studentId = studentId;
    }
}
