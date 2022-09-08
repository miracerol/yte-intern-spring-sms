package yte.intern.sms.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;
import yte.intern.sms.student.entity.Student;
import yte.intern.sms.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;



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
        return new MessageResponse(ResponseType.SUCCESS, "Student has been delete successfully");

    }
    public MessageResponse updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        student.update(updatedStudent);

        studentRepository.save(student);

        return new MessageResponse(ResponseType.SUCCESS, "Student has been updated successfully");
    }
}

