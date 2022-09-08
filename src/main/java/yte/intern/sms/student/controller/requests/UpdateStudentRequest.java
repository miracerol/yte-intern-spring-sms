package yte.intern.sms.student.controller.requests;

import yte.intern.sms.student.entity.Student;

public record UpdateStudentRequest(
        String name,
        String surname,
        String email,
        String username,

        String password
) {

    public Student toDomainEntity() {
        return new Student(name, surname, email, username, password);
    }

}
