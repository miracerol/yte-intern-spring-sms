package yte.intern.sms.academician.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.sms.academician.entity.Academician;
import yte.intern.sms.academician.repository.AcademicianRepository;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicianService {

    private final AcademicianRepository academicianRepository;



    public MessageResponse addAcademician(Academician academician) {

        academicianRepository.save(academician);

        return new MessageResponse(ResponseType.SUCCESS, "Academician has been added successfully");
    }

    public List<Academician> getAllAcademicians() {
        return academicianRepository.findAll();
    }

    public Academician getById(Long id) {
        return academicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academician not found"));
    }

    public MessageResponse deleteAcademicianById(Long id) {
        academicianRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Academician has been deleted successfully");

    }
    public MessageResponse updateAcademician(Long id, Academician updatedAcademician) {
        Academician academician = academicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academician not found"));

        academician.update(updatedAcademician);

        academicianRepository.save(academician);

        return new MessageResponse(ResponseType.SUCCESS, "Academician has been updated successfully");
    }
    //update password
    public MessageResponse updatePassword(Long id, String password) {
        Academician academician = academicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academician not found"));

        academician.setPassword(password);
        academicianRepository.save(academician);
        return new MessageResponse(ResponseType.SUCCESS, "Password has been updated successfully");
    }
}

