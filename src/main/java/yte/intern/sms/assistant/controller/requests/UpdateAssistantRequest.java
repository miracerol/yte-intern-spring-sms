package yte.intern.sms.assistant.controller.requests;

import yte.intern.sms.assistant.entity.Assistant;

public record UpdateAssistantRequest(
        String name,
        String lastname,
        String email

) {

    public Assistant toDomainEntity() {
        return new Assistant("null","null",email, name, lastname);
    }

}
