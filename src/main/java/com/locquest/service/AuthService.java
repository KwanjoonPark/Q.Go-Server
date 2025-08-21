package com.locquest.service;

import com.locquest.dto.KakaoUserInfo;
import com.locquest.entity.UserEntity;
import com.locquest.repository.UserRepository;
import com.locquest.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String loginWithKakao(KakaoUserInfo userInfo) {
        // 사용자가 이미 존재하는지 확인
        UserEntity user = userRepository.findById(userInfo.getId())
                .orElseGet(() -> {
                    // 새 사용자 생성
                    UserEntity newUser = new UserEntity();
                    newUser.setUserId(userInfo.getId());
                    newUser.setNickname(userInfo.getNickname());
                    newUser.setProfileImage(userInfo.getProfileImage());
                    return userRepository.save(newUser);
                });

        // JWT 토큰 생성
        return jwtUtil.generateToken(user.getUserId());
    }
}