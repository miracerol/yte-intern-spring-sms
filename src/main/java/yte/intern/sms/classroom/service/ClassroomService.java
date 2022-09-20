package yte.intern.sms.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.classroom.repository.ClassroomRepository;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;
import yte.intern.sms.lesson.entity.Lesson;
import yte.intern.sms.lesson.service.LessonService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final LessonService lessonService;



    public MessageResponse addClassroom(Classroom classroom) {


        classroomRepository.save(classroom);

        return new MessageResponse(ResponseType.SUCCESS, "Classroom has been added successfully");
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Classroom getById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
    }

    public MessageResponse deleteClassroomById(Long id) {
        List<Lesson> lessons = lessonService.getByClassroom(id);
        for(Lesson lesson : lessons){
            lesson.setClassroom(null);
            lesson.setSchedule(new ArrayList<>());
            lessonService.updateLesson(lesson.getId(),lesson);
        }
        classroomRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Classroom has been deleted successfully");

    }
    public MessageResponse updateClassroom(Long id, Classroom updatedClassroom) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));

        classroom.update(updatedClassroom);

        classroomRepository.save(classroom);

        return new MessageResponse(ResponseType.SUCCESS, "Classroom has been updated successfully");
    }

    public List<String> getLessonsByClassroomId(Long id) {
        List<Lesson> lessons =lessonService.findAllByClassroomId(id);
        List<String> slots= new ArrayList<>();
        for (Lesson lesson:lessons) {
            slots.addAll(lesson.getSchedule());
        }
        return slots;
    }


}

