package yte.intern.sms.assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.assistant.entity.Assistant;

import java.util.List;
import java.util.Optional;

public interface AssistantRepository  extends JpaRepository<Assistant, Long> {
    Optional<Assistant> findAssistantById(Long id);

    List<Assistant> findAll();
}
