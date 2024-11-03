package com.devkduck.letterfromthesea.member.mapper;

import com.devkduck.letterfromthesea.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
    Member findByKakaoId(String kakaoId);
    void updateTokens(@Param("kakaoId") String kakaoId,
                      @Param("accessToken") String accessToken,
                      @Param("refreshToken") String refreshToken);
}
