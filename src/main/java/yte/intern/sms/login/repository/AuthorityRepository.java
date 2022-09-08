package yte.intern.sms.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.sms.login.entity.Authority;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String authority);

    boolean existsByAuthority(String authority);
}


