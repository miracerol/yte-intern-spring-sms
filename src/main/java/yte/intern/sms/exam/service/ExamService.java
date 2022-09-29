package yte.intern.sms.exam.service;

import antlr.debug.MessageAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;
import yte.intern.sms.exam.entity.Exam;
import yte.intern.sms.exam.repository.ExamRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    public MessageResponse addExam(Exam exam) {
        examRepository.save(exam);
        return new MessageResponse(ResponseType.SUCCESS, "Exam has been added successfully");
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public List<Exam> getAllExamsByLessonId(Long lessonId) {
        return examRepository.findAllByLessonId(lessonId);
    }

    public List<Exam> getAllExamsByStudentId(Long studentId) {
        return examRepository.findAllByStudentId(studentId);
    }

    public Exam getExamByLessonIdAndStudentId(Long lessonId, Long studentId) {
        return examRepository.findByLessonIdAndStudentId(lessonId, studentId);
    }

    public Exam getExamByStudentIdAndExamPostId(Long studentId, Long examPostId) {
        return examRepository.findByStudentIdAndExamPostId(studentId, examPostId);
    }
    public MessageResponse gradeExam(long examId, int grade) {
        Exam exam = examRepository.findById(examId).get();
        exam.setGrade(grade);
        examRepository.save(exam);
        return new MessageResponse(ResponseType.SUCCESS, "Exam has been graded successfully");

    }



}
