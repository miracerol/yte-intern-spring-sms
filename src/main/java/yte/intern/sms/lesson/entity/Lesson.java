package yte.intern.sms.lesson.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.sms.academician.entity.Academician;
import yte.intern.sms.assistant.entity.Assistant;
import yte.intern.sms.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    private Integer room;


    @ManyToOne
    private Academician instructor;

    @ManyToMany
    private List<Assistant> assistants;


}
