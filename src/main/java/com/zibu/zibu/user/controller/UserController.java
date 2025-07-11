package com.zibu.zibu.user.controller;

import com.zibu.zibu.user.dto.*;
import com.zibu.zibu.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
