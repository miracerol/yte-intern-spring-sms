package yte.intern.sms.academician.controller.responses;

import yte.intern.sms.academician.entity.Academician;

public record AcademicianQueryModel(
        Long id,
        String name,
        String lastname,
        String email,
        String username

) {

    public AcademicianQueryModel(Academician academician) {
        this(
                academician.getId(),
                academician.getName(),
                academician.getLastName(),
                academician.getEmail(),
                academician.getUsername()

        );
    }

}