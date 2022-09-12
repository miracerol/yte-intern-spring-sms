package yte.intern.sms.academician.controller.requests;

import yte.intern.sms.academician.entity.Academician;

public record UpdateAcademicianRequest(
        String name,
        String lastname,
        String email

) {

    public Academician toDomainEntity() {
        return new Academician("null","null",email, name, lastname);
    }

}