package com.zibu.zibu.user.service;

import com.zibu.zibu.user.dto.UserNicknameUpdateResponseDto;
import com.zibu.zibu.user.dto.UserSignUpRequestDto;
import com.zibu.zibu.user.dto.UserSignUpResponseDto;
import com.zibu.zibu.user.entity.User;
import com.zibu.zibu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSignUpResponseDto signUpUser(UserSignUpRequestDto userSignUpRequestDto) {
        validateUserInfo(userSignUpRequestDto);
        User user = userSignUpRequestDto.toEntity();
        userRepository.save(user);
        return UserSignUpResponseDto.from(user);

    }

    private void validateUserInfo(UserSignUpRequestDto userSignUpRequestDto) {
        if (userRepository.findByEmail(userSignUpRequestDto.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
        if (userRepository.findByNickname(userSignUpRequestDto.getNickname()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    @Transactional
    public UserNicknameUpdateResponseDto updateUserNickname(Long userId, String newNickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. ID: " + userId));

        if (userRepository.findByNickname(newNickname).isPresent()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }

        User updatedUser = user.updateNickname(newNickname);

        return UserNicknameUpdateResponseDto.from(updatedUser);
    }

}
