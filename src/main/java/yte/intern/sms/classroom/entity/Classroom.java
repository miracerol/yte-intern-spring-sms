package yte.intern.sms.classroom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.sms.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom extends BaseEntity {
    private String classroomName;
    private Integer capacity;
    private boolean hasProjector;
    private boolean hasComputer;
    private boolean hasAirConditioner;
    private boolean hasWindow;

    public void update(Classroom updatedClassroom) {
        this.classroomName = updatedClassroom.classroomName;
        this.capacity = updatedClassroom.capacity;
        this.hasProjector = updatedClassroom.hasProjector;
        this.hasComputer = updatedClassroom.hasComputer;
        this.hasAirConditioner = updatedClassroom.hasAirConditioner;
        this.hasWindow = updatedClassroom.hasWindow;
    }
}
