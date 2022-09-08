package yte.intern.sms.student.controller.requests;

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


        String username,


        String password

) {
    public Student toDomainEntity() {
        return new Student(username,password,email, name, lastname);
    }

}