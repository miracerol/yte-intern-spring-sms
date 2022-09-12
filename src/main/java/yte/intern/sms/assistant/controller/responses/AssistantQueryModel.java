package yte.intern.sms.assistant.controller.responses;

import yte.intern.sms.assistant.entity.Assistant;

public record AssistantQueryModel(
        Long id,
        String name,
        String lastname,
        String email,
        String username

) {

    public AssistantQueryModel(Assistant assistant) {
        this(
                assistant.getId(),
                assistant.getName(),
                assistant.getLastName(),
                assistant.getEmail(),
                assistant.getUsername()

        );
    }

}