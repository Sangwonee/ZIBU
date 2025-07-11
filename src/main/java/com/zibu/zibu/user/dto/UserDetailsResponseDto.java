package com.zibu.zibu.user.dto;

import com.zibu.zibu.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDetailsResponseDto {
    private Long id;
    private String email;
    private String nickname;

    public static UserDetailsResponseDto from(User user) {
        return new UserDetailsResponseDto(user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}
