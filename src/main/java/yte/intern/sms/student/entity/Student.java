package yte.intern.sms.student.entity;

import yte.intern.sms.common.entity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.sms.login.entity.Authority;
import yte.intern.sms.login.entity.Users;
import yte.intern.sms.login.repository.UserRepository;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@DiscriminatorValue("1")
public class Student extends Users {

    public Student( String username, String password, String email, String name, String lastName) {

        super( username,password, email, name, lastName, List.of(new Authority("STUDENT")), new ArrayList<>());
    }

    public Student() {
    }



    public void update(Student updatedStudent) {
        super.updateUser(updatedStudent.getEmail(), updatedStudent.getName(), updatedStudent.getLastName());

    }


}