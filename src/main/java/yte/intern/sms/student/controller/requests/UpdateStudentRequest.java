package yte.intern.sms.student.controller.requests;

import yte.intern.sms.student.entity.Student;

public record UpdateStudentRequest(
        String name,
        String lastname,
        String email

) {

    public Student toDomainEntity() {
        return new Student("null","null",email, name, lastname);
    }

}
