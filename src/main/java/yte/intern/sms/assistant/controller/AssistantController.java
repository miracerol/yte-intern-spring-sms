package yte.intern.sms.assistant.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.assistant.controller.requests.AddAssistantRequest;
import yte.intern.sms.assistant.controller.requests.AddLessonRequestAssistant;
import yte.intern.sms.assistant.controller.requests.UpdateAssistantRequest;
import yte.intern.sms.assistant.controller.requests.UpdatePasswordRequest;
import yte.intern.sms.assistant.controller.responses.AssistantQueryModel;
import yte.intern.sms.assistant.service.AssistantService;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.lesson.controller.responses.LessonQueryModel;
import yte.intern.sms.login.repository.AuthorityRepository;
import yte.intern.sms.login.repository.UserRepository;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/assistants")
@RequiredArgsConstructor
@Validated
public class AssistantController {
    private final AssistantService assistantService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addAssistant(@Valid @RequestBody AddAssistantRequest addAssistantRequest) {

        return assistantService.addAssistant(addAssistantRequest.toDomainEntity(passwordEncoder));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public List<AssistantQueryModel> getAllAssistants() {
        return assistantService.getAllAssistants()
                .stream()
                .map(assistant -> new AssistantQueryModel(assistant))
                .toList();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public AssistantQueryModel getById(@NotNull @PathVariable Long id) {
        return new AssistantQueryModel(assistantService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteAssistantById(@PathVariable @NotNull Long id) {
        return assistantService.deleteAssistantById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateAssistant(@Valid @RequestBody UpdateAssistantRequest request, @PathVariable Long id) {
        return assistantService.updateAssistant(id, request.toDomainEntity());
    }
    //update password
    @PutMapping("/updatePassword/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequest passwordRequest) {
        return assistantService.updatePassword(id, passwordEncoder.encode(passwordRequest.getPassword()));
    }

    @PostMapping("/lessons/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public MessageResponse addLesson(@PathVariable Long id, @RequestBody AddLessonRequestAssistant request) {
        return assistantService.addLesson(id, request.toDomainEntity());
    }
    @PutMapping("/lessons/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN')")
    public MessageResponse deleteLesson(@PathVariable Long id, @RequestBody AddLessonRequestAssistant request) {
        return assistantService.deleteLesson(id, request.toDomainEntity());
    }

    @GetMapping("/lessons/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ACADEMICIAN','ASSISTANT','STUDENT')")
    public List<LessonQueryModel> getLessons(@PathVariable Long id) {
        return assistantService.getLessons(id)
                .stream()
                .map(LessonQueryModel::new)
                .toList();
    }


}
