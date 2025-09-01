package com.nahowo.rushTicket.service;

import com.nahowo.rushTicket.config.error.exception.UserEmailDuplicatedException;
import com.nahowo.rushTicket.domain.User;
import com.nahowo.rushTicket.dto.request.UserCreateRequest;
import com.nahowo.rushTicket.dto.response.UserResponse;
import com.nahowo.rushTicket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserCreateRequest reqeust) {
        String name = reqeust.name();
        String email = reqeust.email();
        String password = reqeust.password();
        isEmailDuplicated(email);
        User createdUser = userRepository.save(new User(name, email, password));
        return new UserResponse(createdUser);
    }

    private void isEmailDuplicated(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserEmailDuplicatedException();
        }
    }
}
