package com.nahowo.rushTicket.service;

import com.nahowo.rushTicket.domain.User;
import com.nahowo.rushTicket.dto.request.UserCreateReqeust;
import com.nahowo.rushTicket.dto.response.UserResponse;
import com.nahowo.rushTicket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserCreateReqeust reqeust) {
        String name = reqeust.getName();
        String email = reqeust.getEmail();
        String password = reqeust.getPassword();
        User createdUser = userRepository.save(new User(name, email, password));
        return new UserResponse(createdUser);
    }
}
