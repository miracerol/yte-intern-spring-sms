package yte.intern.sms.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.academician.service.AcademicianService;
import yte.intern.sms.classroom.service.ClassroomService;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.homeworkPost.service.HomeworkPostService;
import yte.intern.sms.lesson.controller.requests.AddLessonRequest;
import yte.intern.sms.lesson.controller.responses.LessonDetailResponse;
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
    private final ClassroomService classroomService;
    private final AcademicianService academicianService;
    private final HomeworkPostService homeworkPostService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addLesson(@Valid @RequestBody AddLessonRequest addLessonRequest) {
        return lessonService.addLesson(addLessonRequest.toDomainEntity(classroomService, academicianService));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public List<LessonQueryModel> getAllStudents() {
        return lessonService.getAllLessons()
                .stream()
                .map(LessonQueryModel::new)
                .toList();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
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
        return lessonService.updateLesson(id, request.toDomainEntity(classroomService,academicianService));
    }

    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public LessonDetailResponse getLessonDetail(@PathVariable @NotNull Long id) {
        return new LessonDetailResponse(lessonService.getById(id), lessonService, homeworkPostService);
    }

}
