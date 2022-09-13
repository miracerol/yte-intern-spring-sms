package yte.intern.sms.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.classroom.entity.Classroom;
import yte.intern.sms.classroom.repository.ClassroomRepository;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;



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

}

