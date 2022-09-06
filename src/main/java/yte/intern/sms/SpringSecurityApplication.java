package yte.intern.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringSecurityApplication.class, args);
		//UserRepository userRepository = context.getBean(UserRepository.class);
		//userRepository.save(new CustomUser(null, "user", passwordEncoder.encode("user"), List.of(new Authority("USER"))));
	}


}
