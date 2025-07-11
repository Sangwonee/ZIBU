package com.zibu.zibu.user.service;

import com.zibu.zibu.user.dto.*;
import com.zibu.zibu.user.entity.User;
import com.zibu.zibu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSignUpResponseDto signUpUser(UserSignUpRequestDto userSignUpRequestDto) {
        validateEmailNotExists(userSignUpRequestDto.getEmail());
        validateNicknameNotExists(userSignUpRequestDto.getNickname());

        User user = userSignUpRequestDto.toEntity();
        userRepository.save(user);
        return UserSignUpResponseDto.from(user);

    }

    @Transactional
    public UserNicknameUpdateResponseDto updateUserNickname(Long userId, UserNicknameUpdateRequestDto userNicknameUpdateRequestDto) {
        User user = findUser(userId);

        validateNicknameNotExists(userNicknameUpdateRequestDto.getNickname());

        User updatedUser = user.updateNickname(userNicknameUpdateRequestDto.getNickname());

        return UserNicknameUpdateResponseDto.from(updatedUser);
    }

    @Transactional
    public UserPasswordUpdateResponseDto updateUserPassword(Long userId, UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        User user = findUser(userId);

        if (!user.getPassword().equals(userPasswordUpdateRequestDto.getCurrentPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        User updatedUser = user.updatePassword(userPasswordUpdateRequestDto.getNewPassword());

        return UserPasswordUpdateResponseDto.from(updatedUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = findUser(userId);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public UserDetailsResponseDto getUserById(Long userId) {
        User user = findUser(userId);
        return UserDetailsResponseDto.from(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDetailsResponseDto> getAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage.map(UserDetailsResponseDto::from);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. ID: " + userId));
    }

    private void validateEmailNotExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }

    private void validateNicknameNotExists(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

}
