package com.nahowo.rushTicket.service;

import com.nahowo.rushTicket.domain.User;
import com.nahowo.rushTicket.dto.request.UserCreateReqeust;
import com.nahowo.rushTicket.dto.response.UserResponse;
import com.nahowo.rushTicket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserCreateReqeust reqeust) {
        String name = reqeust.getName();
        String email = reqeust.getEmail();
        String password = reqeust.getPassword();
        try {
            isEmailDuplicated(email);
        } catch (Exception e) {
            log.error("이메일 중복: " + email);
            return null;
        }
        User createdUser = userRepository.save(new User(name, email, password));
        return new UserResponse(createdUser);
    }

    private void isEmailDuplicated(String email) throws Exception{
        if (userRepository.existsByEmail(email)) {
            throw new Exception();
        }
    }
}
