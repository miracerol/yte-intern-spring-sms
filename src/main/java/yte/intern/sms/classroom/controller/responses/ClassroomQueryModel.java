package yte.intern.sms.classroom.controller.responses;

import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.student.entity.Student;

public record ClassroomQueryModel(
        String classroomName,
        Integer capacity,
        boolean hasProjector,
        boolean hasComputer,
        boolean hasAirConditioner,
        boolean hasWindow

) {

    public ClassroomQueryModel(Classroom classroom) {
        this(
                classroom.getClassroomName(),
                classroom.getCapacity(),
                classroom.isHasProjector(),
                classroom.isHasComputer(),
                classroom.isHasAirConditioner(),
                classroom.isHasWindow()
        );
    }

}