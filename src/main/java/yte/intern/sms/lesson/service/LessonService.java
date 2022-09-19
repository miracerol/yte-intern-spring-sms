package yte.intern.sms.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.academician.entity.Academician;
import yte.intern.sms.academician.repository.AcademicianRepository;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;
import yte.intern.sms.lesson.entity.Lesson;
import yte.intern.sms.lesson.repository.LessonRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final AcademicianRepository academicianRepository;



    public MessageResponse addLesson(Lesson lesson) {


        lessonRepository.save(lesson);
        Academician academician  = lesson.getInstructor();
        List<Lesson> lessons = academician.getLessons();
        lessons.add(lesson);
        academician.setLessons(lessons);
        academicianRepository.save(academician);
        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been added successfully");
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));
    }

    public MessageResponse deleteLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        Academician academician = lesson.getInstructor();
        List<Lesson> lessons = academician.getLessons();
        lessons.remove(lesson);
        academician.setLessons(lessons);
        academicianRepository.save(academician);
        lessonRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been deleted successfully");

    }
    public MessageResponse updateLesson(Long id, Lesson updatedLesson) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        if (!Objects.equals(lesson.getInstructor().getId(), updatedLesson.getInstructor().getId())) {
            Academician academician = lesson.getInstructor();
            List<Lesson> lessons = academician.getLessons();
            lessons.remove(lesson);
            academician.setLessons(lessons);
            academicianRepository.save(academician);
            Academician updatedAcademician = updatedLesson.getInstructor();
            List<Lesson> updatedLessons = updatedAcademician.getLessons();
            updatedLessons.add(updatedLesson);
            updatedAcademician.setLessons(updatedLessons);
            academicianRepository.save(updatedAcademician);
        }
        lesson.update(updatedLesson);

        lessonRepository.save(lesson);

        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been updated successfully");
    }
    public List<Lesson> findAllByClassroomId(Long id) {
        return lessonRepository.findAllByClassroomId(id);
    }

    public List<Lesson> findAllByAcademicianId(Long id) {
        Academician academician = academicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academician not found"));
        return lessonRepository.findAllByInstructor(academician);
    }
    public void clearAcademicianId(Long id) {

        List<Lesson> lessons = findAllByAcademicianId(id);
        for (Lesson lesson : lessons) {
            lesson.setInstructor(null);
            lessonRepository.save(lesson);
        }
    }
    public void clearClassroomId(Long id) {

        List<Lesson> lessons = findAllByClassroomId(id);
        for (Lesson lesson : lessons) {
            lesson.setClassroom(null);
            lesson.setSchedule(null);
            lessonRepository.save(lesson);
        }
    }

}

