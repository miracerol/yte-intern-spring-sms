package yte.intern.sms.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yte.intern.sms.common.response.ResponseType;
import yte.intern.sms.common.response.SecurityRecord;
import yte.intern.sms.login.controller.LoginRequest;
import yte.intern.sms.login.entity.Users;
import yte.intern.sms.login.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private String authority = "";
    public SecurityRecord login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        Authentication authenticatedAuthentication = authenticationManager.authenticate(token);

        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(authenticatedAuthentication);
        SecurityContextHolder.setContext(newContext);

        authority = newContext.getAuthentication().getAuthorities().stream().toList().get(0).getAuthority();
        Users users = userRepository.findByUsername(loginRequest.username()).get();
        Long id = users.getId();
        return new SecurityRecord(ResponseType.SUCCESS, "Login is successful",authority, id);
    }
}
