package yte.intern.sms.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yte.intern.sms.login.entity.Authority;
import yte.intern.sms.login.entity.Users;
import yte.intern.sms.login.repository.UserRepository;
import yte.intern.sms.student.entity.Student;
import yte.intern.sms.student.service.StudentService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentService studentService;

    @PostConstruct
    public void init() {
        userRepository.save(new Users("user", passwordEncoder.encode("user"),"user@gmail.com","nameuser","lastuser", List.of(new Authority("USER"))));
        studentService.addStudent(new Student("student",passwordEncoder.encode("student"),"student@gmail.com","student","studentlast"));
        userRepository.save(new Users("admin", passwordEncoder.encode("admin"),"admin@gmail.com","nameadmin","lastadmin", List.of(new Authority("ADMIN"))));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username %s is not present".formatted(username)));
    }
}
