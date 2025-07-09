package com.innerpeace.zibu.user.service;

import com.innerpeace.zibu.user.dto.UserSignUpRequestDto;
import com.innerpeace.zibu.user.dto.UserSignUpResponseDto;
import com.innerpeace.zibu.user.entity.User;
import com.innerpeace.zibu.user.repository.UserRepository;
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

}
