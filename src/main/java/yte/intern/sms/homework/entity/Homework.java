package yte.intern.sms.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.sms.common.entity.BaseEntity;
import yte.intern.sms.homeworkPost.entity.HomeworkPost;
import yte.intern.sms.lesson.entity.Lesson;
import yte.intern.sms.student.entity.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Homework extends BaseEntity {


    private String description;


    @Convert(converter = StringListConverter.class)
    private List<String> filePaths;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "homework_post_id")
    private HomeworkPost homeworkPost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    Integer grade;



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


