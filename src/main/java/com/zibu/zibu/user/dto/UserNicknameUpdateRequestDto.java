package com.zibu.zibu.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserNicknameUpdateRequestDto {
    @NotBlank(message = "닉네임은 비워둘 수 없습니다.")
    private String nickname;
}
