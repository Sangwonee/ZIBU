package com.innerpeace.zibu.user.dto;

import com.innerpeace.zibu.user.entity.User;
import lombok.Getter;

@Getter
public class UserSignUpResponseDto {
    private Long id;
    private String email;
    private String nickname;

    private UserSignUpResponseDto(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserSignUpResponseDto from(User user) {
        return new UserSignUpResponseDto(user.getId(), user.getEmail(), user.getNickname());
    }
}
