package yte.intern.sms.student.controller.requests;

import javax.validation.constraints.NotBlank;

public record AddLessonRequestStudent(
        @NotBlank
        Long lessonId
) {
    @NotBlank public Long toDomainEntity() {
        return lessonId;
    }
}
