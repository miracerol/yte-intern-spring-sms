package yte.intern.sms.lesson.controller.requests;

import yte.intern.sms.academician.entity.Academician;
import yte.intern.sms.academician.service.AcademicianService;
import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.classroom.service.ClassroomService;
import yte.intern.sms.lesson.entity.Lesson;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

public record AddLessonRequest(

        @NotBlank
        @Size(max = 25)
        String lessonName,

        String description,
        String type,
        String code,
        List<String> schedule,
        Long classroomId,
        Long academicianId

) {
    public Lesson toDomainEntity(ClassroomService classroomService, AcademicianService academicianService) {
        Classroom classroom = classroomService.getById(classroomId);
        Academician academician = academicianService.getById(academicianId);
        Collections.sort(schedule);
        return new Lesson(lessonName, description, type, code, schedule, classroom, academician);
    }

}