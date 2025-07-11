package com.zibu.zibu.user.dto;

import com.zibu.zibu.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPasswordUpdateResponseDto {
    private Long userId;
    private String message;

    public static UserPasswordUpdateResponseDto from(User user) {
        return new UserPasswordUpdateResponseDto(user.getId(), "비밀번호가 성공적으로 변경되었습니다.");
    }
}
