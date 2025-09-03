package com.nahowo.rushTicket.service;

import com.nahowo.rushTicket.config.error.exception.LoginFailedException;
import com.nahowo.rushTicket.config.error.exception.UserEmailDuplicatedException;
import com.nahowo.rushTicket.domain.User;
import com.nahowo.rushTicket.dto.request.UserCreateRequest;
import com.nahowo.rushTicket.dto.request.UserLoginRequest;
import com.nahowo.rushTicket.dto.response.UserResponse;
import com.nahowo.rushTicket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(UserCreateRequest reqeust) {
        String name = reqeust.name();
        String email = reqeust.email();
        String password = passwordEncoder.encode(reqeust.password());
        try {
            User createdUser = userRepository.save(new User(name, email, password));
            return new UserResponse(createdUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserEmailDuplicatedException();
        }
    }

    @Transactional
    public UserResponse loginUser(UserLoginRequest request) {
        String email = request.email();
        String password = request.password();
        User user = userRepository.findByEmail(email)
            .orElseThrow(LoginFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginFailedException();
        }
        return new UserResponse(user);
    }
}
