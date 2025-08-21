package com.locquest.controller;

import com.locquest.dto.AuthCodeRequest;
import com.locquest.dto.KakaoUserInfo;
import com.locquest.service.AuthService;
import com.locquest.service.KakaoOAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final KakaoOAuthService kakaoOAuthService;
    private final AuthService authService;

    @PostMapping("/kakaoLogin")
    public ResponseEntity<?> kakaoLogin(@RequestBody AuthCodeRequest req) {
        String authCode = req.getAuthCode();
        KakaoUserInfo userInfo = kakaoOAuthService.loginWithAuthCode(authCode);

        String jwt = authService.loginWithKakao(userInfo);

        Map<String,Object> resp = Map.of(
                "userId", userInfo.getId(),
                "nickname", userInfo.getNickname(),
                "profileImage", userInfo.getProfileImage(),
                "jwt", jwt
        );

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validate() {
        return ResponseEntity.ok().build();
    }
}