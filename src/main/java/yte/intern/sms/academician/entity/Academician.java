package yte.intern.sms.academician.entity;

import lombok.Getter;
import lombok.Setter;
import yte.intern.sms.login.entity.Authority;
import yte.intern.sms.login.entity.Users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("2")
public class Academician extends Users {

    public Academician( String username, String password, String email, String name, String lastName) {

        super( username,password, email, name, lastName, List.of(new Authority("ACADEMICIAN")));
    }

    public Academician() {
    }



    public void update(Academician updatedAcademician) {
        super.updateUser(updatedAcademician.getEmail(), updatedAcademician.getName(), updatedAcademician.getLastName());

    }


}