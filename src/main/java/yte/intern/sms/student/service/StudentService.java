package yte.intern.sms.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;
import yte.intern.sms.lesson.entity.Lesson;
import yte.intern.sms.lesson.service.LessonService;
import yte.intern.sms.student.entity.Student;
import yte.intern.sms.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final LessonService lessonService;



    public MessageResponse addStudent(Student student) {


        studentRepository.save(student);

        return new MessageResponse(ResponseType.SUCCESS, "Student has been added successfully");
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    public MessageResponse deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Student has been deleted successfully");

    }
    public MessageResponse updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        student.update(updatedStudent);

        studentRepository.save(student);

        return new MessageResponse(ResponseType.SUCCESS, "Student has been updated successfully");
    }
    public MessageResponse updatePassword(Long id, String password) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        student.setPassword(password);
        studentRepository.save(student);
        return new MessageResponse(ResponseType.SUCCESS, "Password has been updated successfully");
    }

    public MessageResponse addLesson(Long id, Long lessonId) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Lesson lesson = lessonService.getById(lessonId);
        if (student.getLessons().contains(lesson)) {
            return new MessageResponse(ResponseType.ERROR, "Student already has this lesson");
        }
        student.addLesson(lesson);
        studentRepository.save(student);
        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been added successfully");
    }

    public MessageResponse deleteLesson(Long id, Long lessonId) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Lesson lesson = lessonService.getById(lessonId);
        student.deleteLesson(lesson);
        studentRepository.save(student);
        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been deleted successfully");
    }

    public List<Lesson> getLessons(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        return student.getLessons();
    }

    public List<Student> getStudentsByLessonId(Long id) {
        return lessonService.getStudentsByLessonId(id);
    }
}

