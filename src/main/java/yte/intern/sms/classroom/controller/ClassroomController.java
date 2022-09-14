package yte.intern.sms.classroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.classroom.controller.requests.AddClassroomRequest;
import yte.intern.sms.classroom.controller.responses.ClassroomQueryModel;
import yte.intern.sms.classroom.service.ClassroomService;
import yte.intern.sms.common.response.MessageResponse;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
@Validated
public class ClassroomController {
    private final ClassroomService classroomService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addClassroom(@Valid @RequestBody AddClassroomRequest addClassroomRequest) {

        return classroomService.addClassroom(addClassroomRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT')")
    public List<ClassroomQueryModel> getAllStudents() {
        return classroomService.getAllClassrooms()
                .stream()
                .map(classroom -> new ClassroomQueryModel(classroom, classroomService.getLessonsByClassroomId(classroom.getId())))
                .toList();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT')")
    public ClassroomQueryModel getById(@NotNull @PathVariable Long id) {
        return new ClassroomQueryModel(classroomService.getById(id),classroomService.getLessonsByClassroomId(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteClassroomById(@PathVariable @NotNull Long id) {
        return classroomService.deleteClassroomById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateClassroom(@Valid @RequestBody AddClassroomRequest request, @PathVariable Long id) {
        return classroomService.updateClassroom(id, request.toDomainEntity());
    }



}
