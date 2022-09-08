package yte.intern.sms.student.controller.responses;

import yte.intern.sms.student.entity.Student;

public record StudentQueryModel(
        Long id,
        String name,
        String surname,
        String email,
        String username,
        String password

) {

    public StudentQueryModel(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getUsername(),
                student.getPassword()

        );
    }
}