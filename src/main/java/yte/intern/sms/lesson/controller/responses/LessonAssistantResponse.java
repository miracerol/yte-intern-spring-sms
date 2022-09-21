package yte.intern.sms.lesson.controller.responses;

import yte.intern.sms.assistant.entity.Assistant;
import yte.intern.sms.lesson.entity.Lesson;

import java.util.List;

public record LessonAssistantResponse(
        long id,
        String name,
        String lastName
) {
    public LessonAssistantResponse(Assistant assistant) {
        this(assistant.getId(), assistant.getName(), assistant.getLastName());
    }

    public static List<LessonAssistantResponse> toLessonAssistantResponseList(List<Assistant> assistants) {
        return assistants.stream().map(LessonAssistantResponse::new).toList();
    }
}
