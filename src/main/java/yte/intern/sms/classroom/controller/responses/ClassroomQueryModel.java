package yte.intern.sms.classroom.controller.responses;

import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public record ClassroomQueryModel(
        long id,
        String classroomName,
        Integer capacity,
        boolean hasProjector,
        boolean hasComputer,
        boolean hasAirConditioner,
        boolean hasWindow,
        List<String> fullSlots

) {

    public ClassroomQueryModel(Classroom classroom) {
        this(
                classroom.getId(),
                classroom.getClassroomName(),
                classroom.getCapacity(),
                classroom.isHasProjector(),
                classroom.isHasComputer(),
                classroom.isHasAirConditioner(),
                classroom.isHasWindow(),
                new ArrayList<>()
        );
    }
    public ClassroomQueryModel(Classroom classroom, List<String> slots) {
        this(
                classroom.getId(),
                classroom.getClassroomName(),
                classroom.getCapacity(),
                classroom.isHasProjector(),
                classroom.isHasComputer(),
                classroom.isHasAirConditioner(),
                classroom.isHasWindow(),
                slots
        );
    }

}