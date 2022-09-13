package yte.intern.sms.classroom.controller.requests;

import org.springframework.security.crypto.password.PasswordEncoder;
import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddClassroomRequest(
        @NotBlank
        @Size(max = 25)
        String classroomName,

        Integer capacity,

        boolean hasProjector,
        boolean hasComputer,
        boolean hasAirConditioner,
        boolean hasWindow

) {
    public Classroom toDomainEntity() {

        return new Classroom(classroomName,capacity,hasProjector,hasComputer,hasAirConditioner,hasWindow);
    }

}