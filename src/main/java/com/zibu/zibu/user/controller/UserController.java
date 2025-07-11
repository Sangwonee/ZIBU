package com.zibu.zibu.user.controller;

import com.zibu.zibu.user.dto.*;
import com.zibu.zibu.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        UserSignUpResponseDto userSignUpResponseDto = userService.signUpUser(userSignUpRequestDto);
        return ResponseEntity.ok(userSignUpResponseDto);
    }

    @PatchMapping("/{userId}/nickname")
    public ResponseEntity<UserNicknameUpdateResponseDto> updateUserNickname(@PathVariable Long userId,
                                                                            @Valid @RequestBody UserNicknameUpdateRequestDto userNicknameUpdateRequestDto) {
        UserNicknameUpdateResponseDto userNicknameUpdateResponseDto = userService.updateUserNickname(userId, userNicknameUpdateRequestDto);
        return ResponseEntity.ok(userNicknameUpdateResponseDto);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<UserPasswordUpdateResponseDto> updateUserPassword(@PathVariable Long userId,
                                                                            @Valid @RequestBody UserPasswordUpdateRequestDto request) {
        UserPasswordUpdateResponseDto userPasswordUpdateResponseDto = userService.updateUserPassword(userId, request);
        return ResponseEntity.ok(userPasswordUpdateResponseDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsResponseDto> getUserById(@PathVariable Long userId) {
        UserDetailsResponseDto userDetailsResponseDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDetailsResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailsResponseDto>> getAllUsers(@PageableDefault(size = 20, sort = "id") Pageable pageable) {
        Page<UserDetailsResponseDto> usersPage = userService.getAllUsers(pageable);
        return ResponseEntity.ok(usersPage);
    }
}
