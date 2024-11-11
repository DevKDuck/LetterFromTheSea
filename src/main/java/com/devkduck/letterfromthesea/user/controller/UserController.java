package com.devkduck.letterfromthesea.user.controller;


import com.devkduck.letterfromthesea.common.CustomResponse;
import com.devkduck.letterfromthesea.user.domain.UserRegisterDTO;
import com.devkduck.letterfromthesea.user.service.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class UserController {

    private final PrincipalDetailsService principalDetailsService;

    @PostMapping("/join")
    public CustomResponse<String> join(@Validated @RequestBody UserRegisterDTO userRegisterDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.failure(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }else {
            int result = principalDetailsService.register(userRegisterDTO);
            if (result == 1) {
                System.out.println("등록되었습니다.");
                return CustomResponse.ok("등록 완료", null);
            } else {
                return CustomResponse.failure("등록 실패");
            }
        }
     }

}
