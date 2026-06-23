package com.haedong.backend.auth;

import com.haedong.backend.common.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        // 1. 아이디로 유저 조회
        User user = userMapper.findById(request.getId());
        if (user == null) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        // 3. JWT 토큰 생성
        String token = jwtUtil.generateToken(user.getId(), user.getRole());

        return new LoginResponse(token, user.getId(), user.getUsername(), user.getRole());
    }
}
