package yte.intern.sms.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.exam.controller.requests.AddExamRequest;
import yte.intern.sms.exam.controller.responses.ExamQueryModel;
import yte.intern.sms.exam.entity.Exam;
import yte.intern.sms.exam.service.ExamService;
import yte.intern.sms.examPost.service.ExamPostService;
import yte.intern.sms.lesson.service.LessonService;
import yte.intern.sms.student.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
@Validated
public class ExamController {
    private final ExamService examService;
    private final ExamPostService examPostService;
    private final LessonService lessonService;
    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public MessageResponse addExam(@Valid @RequestBody AddExamRequest addExamRequest) {
        return examService.addExam(addExamRequest.toDomainEntity(lessonService, studentService, examPostService));
    }

    @GetMapping("/{lessonId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public List<ExamQueryModel> getExamByLessonId(@PathVariable Long lessonId) {
        return examService.getAllExamsByLessonId(lessonId)
                .stream()
                .map(
                        exam -> new ExamQueryModel(
                                exam.getId(),
                                exam.getGrade(),
                                exam.getExamPost().getId(),
                                exam.getStudent().getName() + " " + exam.getStudent().getLastName(),
                                exam.getStudent().getId()
                ))
                .toList();
    }

    @PutMapping("/{examId}/grade")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public MessageResponse updateExamGrade(@PathVariable Long examId, @RequestParam int grade) {
        return examService.gradeExam(examId, grade);
    }


}
