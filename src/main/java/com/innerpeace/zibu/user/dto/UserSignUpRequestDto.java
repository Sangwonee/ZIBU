package com.innerpeace.zibu.user.dto;

import com.innerpeace.zibu.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String nickname;

    public User toEntity() {
        return User.of(email, password, nickname);  // 또는 User.from(this)
    }
}
