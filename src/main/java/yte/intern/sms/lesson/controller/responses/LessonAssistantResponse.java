package yte.intern.sms.lesson.controller.responses;

import yte.intern.sms.assistant.entity.Assistant;

public record LessonAssistantResponse(
        long id,
        String name,
        String lastName
) {
    public LessonAssistantResponse(Assistant assistant) {
        this(assistant.getId(), assistant.getName(), assistant.getLastName());
    }
}
