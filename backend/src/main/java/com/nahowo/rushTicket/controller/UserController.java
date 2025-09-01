package com.nahowo.rushTicket.controller;

import com.nahowo.rushTicket.dto.request.UserCreateRequest;
import com.nahowo.rushTicket.dto.response.UserResponse;
import com.nahowo.rushTicket.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    @Operation(description = "User Registration")
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody UserCreateRequest userCreateReqeust) {
        UserResponse user = userService.createUser(userCreateReqeust);
        return ResponseEntity.ok(user.toString());
    }
}
