package yte.intern.sms.lesson.controller.requests;

import yte.intern.sms.academician.entity.Academician;
import yte.intern.sms.academician.service.AcademicianService;
import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.classroom.service.ClassroomService;
import yte.intern.sms.lesson.entity.Lesson;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

public record AddLessonRequest(

        @NotBlank
        String lessonName,
        String description,
        String type,
        String code,
        List<String> schedule,
        long classroomId,
        long academicianId

) {
    public Lesson toDomainEntity(ClassroomService classroomService, AcademicianService academicianService) {
        Classroom classroom = classroomService.getById(classroomId);
        Academician academician = academicianService.getById(academicianId);
        Collections.sort(schedule);
        return new Lesson(lessonName, description, type, code, schedule, classroom, academician);
    }

}