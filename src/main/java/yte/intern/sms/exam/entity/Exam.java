package yte.intern.sms.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.sms.common.entity.BaseEntity;
import yte.intern.sms.examPost.entity.ExamPost;
import yte.intern.sms.lesson.entity.Lesson;
import yte.intern.sms.student.entity.Student;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exam extends BaseEntity {
    Integer grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_post_id")
    private ExamPost examPost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

}
