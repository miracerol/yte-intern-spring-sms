package yte.intern.sms.homeworkPost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.homeworkPost.controller.requests.AddHomeworkPostRequest;
import yte.intern.sms.homeworkPost.controller.responses.HomeworkPostQueryModel;
import yte.intern.sms.homeworkPost.service.HomeworkPostService;
import yte.intern.sms.lesson.service.LessonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/homework-posts")
@RequiredArgsConstructor
@Validated
public class HomeworkPostController {
    private final HomeworkPostService homeworkPostService;
    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public MessageResponse addHomeworkPost(@Valid @RequestBody AddHomeworkPostRequest addHomeworkPostRequest) {
        return homeworkPostService.addHomeworkPost(addHomeworkPostRequest.toDomainEntity(lessonService));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public MessageResponse updateHomeworkPost(@Valid @RequestBody AddHomeworkPostRequest request, @PathVariable Long id) {
        return homeworkPostService.updateHomeworkPost(id, request.toDomainEntity(lessonService));
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public List<HomeworkPostQueryModel> getHomeworkPostById(@PathVariable Long id) {
        return homeworkPostService.getHomeworkPostsByLessonId(id)
                .stream()
                .map(HomeworkPostQueryModel::new)
                .toList();
    }
}
