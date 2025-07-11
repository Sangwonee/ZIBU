package com.zibu.zibu.user.controller;

import com.zibu.zibu.user.dto.UserNicknameUpdateRequestDto;
import com.zibu.zibu.user.dto.UserSignUpRequestDto;
import com.zibu.zibu.user.dto.UserSignUpResponseDto;
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
        UserSignUpResponseDto responseDto = userService.signUpUser(userSignUpRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{userId}/nickname")
    public ResponseEntity<Void> updateUserNickname(@PathVariable Long userId,
                                                   @Valid @RequestBody UserNicknameUpdateRequestDto request) {
        userService.updateUserNickname(userId, request.getNickname());
        return ResponseEntity.ok().build();
    }
}
