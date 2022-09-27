package yte.intern.sms.homeworkPost.entity;

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
public class HomeworkPost extends BaseEntity {
    private String title;
    private String description;
    private String deadline;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lesson lesson;

    public void update(HomeworkPost updatedHomeworkPost) {
        this.title = updatedHomeworkPost.title;
        this.description = updatedHomeworkPost.description;
        this.deadline = updatedHomeworkPost.deadline;
    }
}

