package com.nahowo.rushTicket.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    @NotBlank(message = "이름은 필수입니다. ")
    private String name;
    @NotBlank(message = "이메일은 필수입니다. ")
    @Email(message = "유효한 이메일 형식이 아닙니다. ")
    private String email;
    @NotBlank(message = "비밀번호는 필수입니다. ")
    @Size(min = 6, max = 16, message = "비밀번호는 6~16자여야 합니다. ")
    private String password;
}
