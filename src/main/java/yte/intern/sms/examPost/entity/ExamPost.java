package yte.intern.sms.examPost.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.sms.common.entity.BaseEntity;
import yte.intern.sms.lesson.entity.Lesson;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamPost extends BaseEntity {
    String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lesson lesson;

    public void update(ExamPost updatedExamPost) {
        this.title = updatedExamPost.title;
    }
}
