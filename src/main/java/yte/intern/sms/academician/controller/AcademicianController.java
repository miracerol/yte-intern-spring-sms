package yte.intern.sms.academician.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.sms.academician.controller.requests.AddAcademicianRequest;
import yte.intern.sms.academician.controller.requests.UpdateAcademicianRequest;
import yte.intern.sms.academician.controller.requests.UpdatePasswordRequest;
import yte.intern.sms.academician.controller.responses.AcademicianQueryModel;
import yte.intern.sms.academician.service.AcademicianService;
import yte.intern.sms.common.response.MessageResponse;

import yte.intern.sms.login.repository.AuthorityRepository;
import yte.intern.sms.login.repository.UserRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/academicians")
@RequiredArgsConstructor
@Validated
public class AcademicianController {
    private final AcademicianService academicianService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addAcademician(@Valid @RequestBody AddAcademicianRequest addAcademicianRequest) {

        return academicianService.addAcademician(addAcademicianRequest.toDomainEntity(passwordEncoder));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AcademicianQueryModel> getAllAcademicians() {
        return academicianService.getAllAcademicians()
                .stream()
                .map(academician -> new AcademicianQueryModel(academician))
                .toList();
    }


    @GetMapping("/{id}")

    public AcademicianQueryModel getById(@NotNull @PathVariable Long id) {
        return new AcademicianQueryModel(academicianService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteAcademicianById(@PathVariable @NotNull Long id) {
        return academicianService.deleteAcademicianById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateAcademician(@Valid @RequestBody UpdateAcademicianRequest request, @PathVariable Long id) {
        return academicianService.updateAcademician(id, request.toDomainEntity());
    }
    //update password
    @PutMapping("/updatePassword/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequest passwordRequest) {
        return academicianService.updatePassword(id, passwordEncoder.encode(passwordRequest.getPassword()));
    }
}
