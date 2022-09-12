package yte.intern.sms.academician.controller.requests;

import org.springframework.security.crypto.password.PasswordEncoder;
import yte.intern.sms.academician.entity.Academician;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddAcademicianRequest(
        @NotBlank
        @Size(max = 25)
        String name,
        @NotBlank
        @Size(max = 25)
        String lastname,
        @Email
        String email,

        String password

) {
    public Academician toDomainEntity(PasswordEncoder passwordEncoder) {
        String username = name.toLowerCase() + "." + lastname.toLowerCase();
        return new Academician(username, passwordEncoder.encode(password), email, name, lastname);
    }

}