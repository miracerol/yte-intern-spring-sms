package yte.intern.sms.assistant.controller.requests;

import javax.validation.constraints.NotBlank;

public record AddLessonRequestAssistant(
        @NotBlank
        Long lessonId
) {
    @NotBlank public Long toDomainEntity() {
        return lessonId;
    }
}
