package yte.intern.sms.lesson.controller.requests;

import yte.intern.sms.lesson.entity.Lesson;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddLessonRequest(
        @NotBlank
        @Size(max = 25)
        String lessonName,

        String description,
        String type,
        String code,
        Integer day,
        Integer timeSlot,
        Long classroomId,
        Long academicianId

) {
    public Lesson toDomainEntity() {

        return new Lesson(lessonName, description, type, code, day, timeSlot, classroomId, academicianId);
    }

}