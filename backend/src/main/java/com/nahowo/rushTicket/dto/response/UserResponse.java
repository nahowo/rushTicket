package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.User;

public record UserResponse (
    Long id,
    String name,
    String email
) {
    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
