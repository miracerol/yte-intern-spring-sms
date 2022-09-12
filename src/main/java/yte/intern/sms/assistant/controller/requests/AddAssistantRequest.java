package yte.intern.sms.assistant.controller.requests;

import org.springframework.security.crypto.password.PasswordEncoder;
import yte.intern.sms.assistant.entity.Assistant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddAssistantRequest(
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
    public Assistant toDomainEntity(PasswordEncoder passwordEncoder) {

        String username = name.toLowerCase() + "." + lastname.toLowerCase();

        return new Assistant(username,passwordEncoder.encode(password),email, name, lastname);
    }

}