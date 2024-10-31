//package com.devkduck.letterfromthesea.global.auth;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.view.RedirectView;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("")
//public class KakaoLoginController {
//
//    private final KakaoService kakaoService;
//
//    @GetMapping("/callback")
//    public RedirectView callback(@RequestParam("code") String code ) throws IOException {
//
//        KakaoTokenResponseDto responseDto = kakaoService.getAccessTokenFromKakao(code);
//        String accessToken = responseDto.accessToken;
//        String refreshToken = responseDto.refreshToken;
//        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
//        kakaoService.saveOrUpdateMember(userInfo, accessToken, refreshToken);
//
//        RedirectView redirectView = new RedirectView("/index.html");
//        redirectView.addStaticAttribute("accessToken", accessToken);
//        redirectView.addStaticAttribute("refreshToken", refreshToken);
//
//        return redirectView;
//    }
//}
