package com.innerpeace.zibu.user.controller;

import com.innerpeace.zibu.user.dto.UserSignUpRequestDto;
import com.innerpeace.zibu.user.dto.UserSignUpResponseDto;
import com.innerpeace.zibu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
