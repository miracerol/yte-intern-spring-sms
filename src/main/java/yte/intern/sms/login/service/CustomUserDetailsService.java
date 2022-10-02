package yte.intern.sms.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yte.intern.sms.login.repository.UserRepository;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        //academicianService.addAcademician(new Academician("academician", passwordEncoder.encode("academician"),"academician@gmail.com","academician","academician"));
        //assistantService.addAssistant(new Assistant("assistant", passwordEncoder.encode("assistant"),"assistant@gmail.com","assistant","assistant"));
        //studentService.addStudent(new Student("student",passwordEncoder.encode("student"),"student@gmail.com","student","student"));
        //userRepository.save(new Users("admin", passwordEncoder.encode("admin"),"admin@gmail.com","admin","admin", List.of(new Authority("ADMIN"))));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username %s is not present".formatted(username)));
    }
}
