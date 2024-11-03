package com.devkduck.letterfromthesea.global.config;
import com.devkduck.letterfromthesea.global.auth.KakaoService;
import com.devkduck.letterfromthesea.global.auth.KakaoUserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final KakaoService kakaoService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String accessToken = userRequest.getAccessToken().getTokenValue();

        try {
            KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
            Map<String, Object> attributes = userInfo.toAttributes();

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    attributes,
                    "id"
            );
        } catch (Exception e) {
            log.error("Error loading user info from Kakao: ", e);

            OAuth2Error oauth2Error = new OAuth2Error("invalid_token", "Failed to load user info from Kakao", null);
            throw new OAuth2AuthenticationException(oauth2Error, e.getMessage(), e);
        }
    }
}



//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
//
//        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
//
//        try {
//            return processOAuth2User(oAuth2UserRequest, oAuth2User);
//        } catch (AuthenticationException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
//            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
//        }
//    }
//
//    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
//
//        String registrationId = userRequest.getClientRegistration()
//                .getRegistrationId();
//
//        String accessToken = userRequest.getAccessToken().getTokenValue();
//        log.info(registrationId , accessToken + "----------------여기-----");
//        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId,
//                accessToken,
//                oAuth2User.getAttributes());
//
//        // OAuth2UserInfo field value validation
//        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
//            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
//        }
//
//        return new OAuth2UserPrincipal(oAuth2UserInfo);
//    }
//}