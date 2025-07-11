package com.zibu.zibu.user.dto;

import com.zibu.zibu.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNicknameUpdateResponseDto {
    private String nickname;

    public static UserNicknameUpdateResponseDto from(User user) {
        return new UserNicknameUpdateResponseDto(user.getNickname());
    }
}
