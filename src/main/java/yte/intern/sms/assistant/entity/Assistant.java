package yte.intern.sms.assistant.entity;

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
@DiscriminatorValue("3")
public class Assistant extends Users {

    public Assistant( String username, String password, String email, String name, String lastName) {

        super( username,password, email, name, lastName, List.of(new Authority("ASSISTANT")));
    }

    public Assistant() {
    }



    public void update(Assistant updatedAssistant) {
        super.updateUser(updatedAssistant.getEmail(), updatedAssistant.getName(), updatedAssistant.getLastName(), updatedAssistant.getUsername());

    }


}