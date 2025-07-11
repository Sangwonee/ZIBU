package com.zibu.zibu.user.dto;

import com.zibu.zibu.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignUpResponseDto {
    private Long id;
    private String email;
    private String nickname;

    public static UserSignUpResponseDto from(User user) {
        return new UserSignUpResponseDto(user.getId(), user.getEmail(), user.getNickname());
    }
}
