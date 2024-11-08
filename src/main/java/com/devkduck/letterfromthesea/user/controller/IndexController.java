package com.devkduck.letterfromthesea.user.controller;


import com.devkduck.letterfromthesea.user.domain.User;
import com.devkduck.letterfromthesea.user.domain.Userdto;
import com.devkduck.letterfromthesea.user.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class IndexController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private LoginMapper loginMapper;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(Userdto userdto){
//        User user = new User();
//        user.setUsername(userdto.getUsername());
//        user.setName(userdto.getName());
//        user.setEmail(userdto.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
//        user.setPhoneNumber(userdto.getPhoneNumber());
        userdto.setRoles("ROLE_USER");
        System.out.println(userdto);
        loginMapper.save(userdto);
        return "redirect:/loginForm";
     }

}
