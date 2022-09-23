package yte.intern.sms.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.homework.controller.requests.AddHomeworkRequest;
import yte.intern.sms.homework.controller.responses.HomeworkQueryModel;
import yte.intern.sms.homework.entity.Homework;
import yte.intern.sms.homework.service.HomeworkService;
import yte.intern.sms.homeworkPost.service.HomeworkPostService;
import yte.intern.sms.lesson.service.LessonService;
import yte.intern.sms.student.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/homeworks")
@RequiredArgsConstructor
@Validated
public class HomeworkController {
    private final HomeworkService homeworkService;
    private final LessonService lessonService;
    private final StudentService studentService;
    private final HomeworkPostService homeworkPostService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addHomework(@RequestParam("files") MultipartFile[] files, @Valid @ModelAttribute AddHomeworkRequest addHomeworkRequest) {

        return homeworkService.addHomework(addHomeworkRequest.toDomainEntity(files,homeworkService,lessonService,studentService, homeworkPostService));
    }

    @GetMapping("/{lessonId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public List<HomeworkQueryModel> getHomeworksByLessonId(@PathVariable long lessonId) {
        return homeworkService.getHomeworksByLessonId(lessonId)
                .stream()
                .map(homework -> new HomeworkQueryModel(
                        homework.getDescription(),
                        homework.getStudent().getName()+" "+homework.getStudent().getLastName(),
                        homework.getStudent().getId(),
                        homeworkService.getFiles(homework.getFilePaths()),
                        homework.getGrade()
                        ))
                .toList();
    }

    @GetMapping("{lessonId}/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public HomeworkQueryModel getHomeworkByLessonIdAndStudentId(@PathVariable long lessonId, @PathVariable long studentId) {
        Homework homework = homeworkService.getHomeworkByLessonIdAndStudentId(lessonId,studentId);
        return new HomeworkQueryModel(
                homework.getDescription(),
                homework.getStudent().getName()+" "+homework.getStudent().getLastName(),
                homework.getStudent().getId(),
                homeworkService.getFiles(homework.getFilePaths()),
                homework.getGrade()
        );
    }


    @GetMapping(value = "/{lessonId}/{studentId}/{fileIndex}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public byte[] getFile(@PathVariable long lessonId, @PathVariable long studentId, @PathVariable int fileIndex) {
        Homework homework = homeworkService.getHomeworkByLessonIdAndStudentId(lessonId,studentId);
        return homeworkService.getFiles(homework.getFilePaths()).get(fileIndex);
    }








}
