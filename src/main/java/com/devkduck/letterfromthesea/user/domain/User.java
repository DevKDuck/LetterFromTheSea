package com.devkduck.letterfromthesea.user.domain;

import com.devkduck.letterfromthesea.user.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id; // 고유한 ID
    private String username; // 로그인 아이디 또는 이메일
    private String password; // 암호화된 비밀번호
    private String name; // 사용자 이름 또는 닉네임
    private String email; // 이메일 주소
    private String phoneNumber; // 전화번호
    private boolean enabled; // 계정 활성화 여부
    private boolean accountNonExpired; // 계정 만료 여부
    private boolean credentialsNonExpired; // 자격 증명 만료 여부
    private boolean accountNonLocked; // 계정 잠김 여부
    private Role role; // 권한 (예: "ROLE_USER,ROLE_ADMIN")
    private String provider; // 소셜 로그인 제공자 (예: "kakao")
    private String providerId; // 소셜 로그인 사용자 ID
    private String profileImageUrl; // 프로필 이미지 URL
    private String bio; // 사용자 소개
    private LocalDateTime createdAt; // 계정 생성일
    private LocalDateTime updatedAt; // 계정 수정일

    //회원가입
    public static User createUser(UserRegisterDTO userRegisterDTO){
        return User.builder()
                .username(userRegisterDTO.getUsername())
                .password(userRegisterDTO.getPassword())
                .name(userRegisterDTO.getName())
                .email(userRegisterDTO.getEmail())
                .phoneNumber(userRegisterDTO.getPhoneNumber())
                .role(Role.USER)
                .build();
    }
}
