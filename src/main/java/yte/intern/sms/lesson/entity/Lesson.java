package yte.intern.sms.lesson.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.sms.academician.entity.Academician;
import yte.intern.sms.assistant.entity.Assistant;
import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lesson extends BaseEntity {

    private String lessonName;
    private String description;
    private String type;
    private String code;
    private Integer day;
    private Integer timeSlot;

    public Lesson(String lessonName, String description, String type, String code, Integer day, Integer timeSlot, Long ClassroomId, Long academicianId) {
        this.lessonName = lessonName;
        this.description = description;
        this.type = type;
        this.code = code;
        this.day = day;
        this.timeSlot = timeSlot;

    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Classroom room;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academician_id")
    private Academician instructor;

    @ManyToMany
    @JoinTable(name = "lesson_assistant",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "assistant_id"))
    private List<Assistant> assistants;


}
