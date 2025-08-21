package com.locquest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // application.properties 에 정의된 비밀키
    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSigningKey() {
        // 문자열이 Base64인지 확인하고 처리
        try {
            // Base64로 시도
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            // Base64가 아니면 일반 문자열로 처리
            byte[] keyBytes = secretKey.getBytes();
            // 최소 32바이트 보장 (HS256 요구사항)
            if (keyBytes.length < 32) {
                String paddedSecret = secretKey;
                while (paddedSecret.getBytes().length < 32) {
                    paddedSecret += paddedSecret;
                }
                keyBytes = paddedSecret.substring(0, 64).getBytes(); // 64자로 자름
            }
            return Keys.hmacShaKeyFor(keyBytes);
        }
    }

    /**
     * userId로 JWT 토큰을 생성합니다.
     */
    public String generateToken(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // 24시간

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * 토큰에서 subject(claims.getSubject()) 값을 Long 으로 꺼냅니다.
     * 일반적으로 subject에 userId를 넣어두었다고 가정합니다.
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.valueOf(claims.getSubject());
    }

    /**
     * 토큰이 유효한지 검증합니다.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 토큰 만료, 서명오류 등 모든 예외를 여기서 캐치
            return false;
        }
    }
}

