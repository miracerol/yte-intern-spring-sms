package yte.intern.sms.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.login.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

}
