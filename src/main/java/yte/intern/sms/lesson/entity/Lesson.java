package yte.intern.sms.lesson.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.sms.academician.entity.Academician;
import yte.intern.sms.assistant.entity.Assistant;
import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.classroom.service.ClassroomService;
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

    private Integer duration;

    public Lesson(String lessonName, String description, String type, String code, Integer day, Integer timeSlot, Integer duration, Classroom classroom, Academician academician) {
        this.lessonName = lessonName;
        this.description = description;
        this.type = type;
        this.code = code;
        this.day = day;
        this.timeSlot = timeSlot;
        this.duration = duration;
        this.classroom = classroom;
        this.instructor = academician;

    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Classroom classroom;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academician_id")
    private Academician instructor;

    @ManyToMany
    @JoinTable(name = "lesson_assistant",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "assistant_id"))
    private List<Assistant> assistants;


    public void update(Lesson updatedLesson) {
        this.lessonName = updatedLesson.lessonName;
        this.description = updatedLesson.description;
        this.type = updatedLesson.type;
        this.code = updatedLesson.code;
        this.day = updatedLesson.day;
        this.timeSlot = updatedLesson.timeSlot;
        this.classroom = updatedLesson.classroom;
        this.instructor = updatedLesson.instructor;
    }

    public String dayConverter() {
        return switch (this.day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Invalid day";
        };
    }

    public String startTimeSlotConverter() {
        return switch (this.timeSlot) {
            case 1 -> "08:40";
            case 2 -> "09:40";
            case 3 -> "10:40";
            case 4 -> "11:40";
            case 5 -> "13:40";
            case 6 -> "14:40";
            case 7 -> "15:40";
            case 8 -> "16:40";
            default -> "Invalid time slot";
        };
    }

    public String endTimeSlotConverter() {
        return switch (this.timeSlot + this.duration - 1) {
            case 1 -> "09:30";
            case 2 -> "10:30";
            case 3 -> "11:30";
            case 4 -> "12:30";
            case 5 -> "14:30";
            case 6 -> "15:30";
            case 7 -> "16:30";
            case 8 -> "17:30";
            default -> "Invalid time slot";
        };
    }

}
