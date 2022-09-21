package yte.intern.sms.lesson.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import yte.intern.sms.academician.entity.Academician;
import yte.intern.sms.assistant.entity.Assistant;
import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.classroom.service.ClassroomService;
import yte.intern.sms.common.entity.BaseEntity;
import yte.intern.sms.student.entity.Student;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lesson extends BaseEntity {

    private String lessonName;
    private String description;
    private String type;
    private String code;

    @Convert(converter = StringListConverter.class)
    private List<String> schedule;

    public Lesson(String lessonName, String description, String type, String code,List<String> schedule,  Classroom classroom, Academician academician) {
        this.lessonName = lessonName;
        this.description = description;
        this.type = type;
        this.code = code;
        this.schedule=schedule;
        this.classroom = classroom;
        this.instructor = academician;

    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Classroom classroom;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academician_id")
    private Academician instructor;



    public void update(Lesson updatedLesson) {
        this.lessonName = updatedLesson.lessonName;
        this.description = updatedLesson.description;
        this.type = updatedLesson.type;
        this.code = updatedLesson.code;
        this.schedule=updatedLesson.schedule;
        this.classroom = updatedLesson.classroom;
        this.instructor = updatedLesson.instructor;
    }

    public List<String> getScheduleText() {
        List<String> scheduleText = new java.util.ArrayList<>(emptyList());
        String day = "";
        String StartTime = "";
        String EndTime = "";
        int lastSlot = -1;
        if (this.schedule.size() < 2) {
            return scheduleText;
        }
        for (String s : this.schedule){

            String[] split = s.split("-");
            int dayi=Integer.parseInt(split[0]);
            int sloti=Integer.parseInt(split[1]);
            String dayIn = dayConverter(dayi);
            String StartTimeIn = startTimeSlotConverter(sloti);
            String EndTimeIn = endTimeSlotConverter(sloti);
            if (day.equals(dayIn) && lastSlot+1==sloti){
                EndTime =EndTimeIn;
            }
            else{
                if(lastSlot!=-1){
                    scheduleText.add(day+" "+StartTime+"-"+EndTime);
                }
                day = dayIn;
                StartTime = StartTimeIn;
                EndTime = EndTimeIn;
            }
            lastSlot = sloti;

        }
        scheduleText.add(day+" "+StartTime+"-"+EndTime);
        return scheduleText;
    }


    public String dayConverter(int day) {
        return switch (day) {
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

    public String startTimeSlotConverter(int timeSlot) {
        return switch (timeSlot) {
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

    public String endTimeSlotConverter(int timeSlot) {
        return switch (timeSlot ) {
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
    @Converter
    public static class StringListConverter implements AttributeConverter<List<String>, String> {
        private static final String SPLIT_CHAR = ";";

        @Override
        public String convertToDatabaseColumn(List<String> stringList) {
            return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
        }

        @Override
        public List<String> convertToEntityAttribute(String string) {
            return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();
        }
    }

}
