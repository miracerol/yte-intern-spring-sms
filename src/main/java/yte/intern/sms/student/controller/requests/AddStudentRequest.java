package yte.intern.sms.student.controller.requests;

import org.springframework.security.crypto.password.PasswordEncoder;
import yte.intern.sms.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddStudentRequest(
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
    public Student toDomainEntity(PasswordEncoder passwordEncoder, String username) {

        return new Student(username,passwordEncoder.encode(password),email, name, lastname);
    }

}