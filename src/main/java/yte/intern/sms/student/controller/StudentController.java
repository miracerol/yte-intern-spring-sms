package yte.intern.sms.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.login.entity.Authority;
import yte.intern.sms.login.entity.Users;
import yte.intern.sms.login.repository.AuthorityRepository;
import yte.intern.sms.login.repository.UserRepository;
import yte.intern.sms.student.controller.requests.AddStudentRequest;
import yte.intern.sms.student.controller.requests.UpdateStudentRequest;
import yte.intern.sms.student.controller.responses.StudentQueryModel;
import yte.intern.sms.student.service.StudentService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Validated
public class StudentController {
    private final StudentService studentService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addStudent(@Valid @RequestBody AddStudentRequest addStudentRequest) {
        Users a = new Users();
        Authority student = authorityRepository.findByAuthority("STUDENT")
                .orElseThrow(() -> new EntityNotFoundException("Authority:STUDENT not found!"));
        a.setPassword(passwordEncoder.encode(addStudentRequest.password()));

        a.setUsername(addStudentRequest.username());

        a.setAuthorities(List.of(student));

        userRepository.save(a);
        return studentService.addStudent(addStudentRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<StudentQueryModel> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(student -> new StudentQueryModel(student))
                .toList();
    }


    @GetMapping("/{id}")

    public StudentQueryModel getById(@NotNull @PathVariable Long id) {
        return new StudentQueryModel(studentService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteStudentById(@PathVariable @NotNull Long id) {
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateStudent(@Valid @RequestBody UpdateStudentRequest request, @PathVariable Long id) {
        return studentService.updateStudent(id, request.toDomainEntity());
    }
}
