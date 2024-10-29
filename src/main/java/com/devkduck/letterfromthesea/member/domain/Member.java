package com.devkduck.letterfromthesea.member.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String kakaoId;
    private String nickname;
    private String profileImageUrl;
    private String accessToken;
    private String refreshToken;
}
