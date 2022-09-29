package yte.intern.sms.examPost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.examPost.controller.requests.AddExamPostRequest;
import yte.intern.sms.examPost.controller.responses.ExamPostQueryModel;
import yte.intern.sms.examPost.service.ExamPostService;
import yte.intern.sms.lesson.service.LessonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exam-posts")
@RequiredArgsConstructor
@Validated
public class ExamPostController {
    private final ExamPostService examPostService;
    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public MessageResponse addExamPost(@Valid @RequestBody AddExamPostRequest addExamPostRequest) {
        return examPostService.addExamPost(addExamPostRequest.toDomainEntity(lessonService));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public MessageResponse updateExamPost(@Valid @RequestBody AddExamPostRequest request, @PathVariable Long id) {
        return examPostService.updateExamPost(id, request.toDomainEntity(lessonService));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public List<ExamPostQueryModel> getExamPostById(@PathVariable Long id) {
        return examPostService.getExamPostsByLessonId(id)
                .stream()
                .map(ExamPostQueryModel::new)
                .toList();
    }
}
