package yte.intern.sms.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.lesson.controller.requests.AddLessonRequest;
import yte.intern.sms.lesson.controller.responses.LessonQueryModel;
import yte.intern.sms.lesson.service.LessonService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
@Validated
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addLesson(@Valid @RequestBody AddLessonRequest addLessonRequest) {

        return lessonService.addLesson(addLessonRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT')")
    public List<LessonQueryModel> getAllStudents() {
        return lessonService.getAllLessons()
                .stream()
                .map(lesson -> new LessonQueryModel(lesson))
                .toList();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT')")
    public LessonQueryModel getById(@NotNull @PathVariable Long id) {
        return new LessonQueryModel(lessonService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteLessonById(@PathVariable @NotNull Long id) {
        return lessonService.deleteLessonById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateLesson(@Valid @RequestBody AddLessonRequest request, @PathVariable Long id) {
        return lessonService.updateLesson(id, request.toDomainEntity());
    }

}
