package com.devkduck.letterfromthesea.user.service;

import com.devkduck.letterfromthesea.user.auth.PrincipalDetails;
import com.devkduck.letterfromthesea.user.domain.UserRegisterDTO;
import com.devkduck.letterfromthesea.user.domain.User;
import com.devkduck.letterfromthesea.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입
    public int register(UserRegisterDTO userRegisterDTO) {
        //유저 존재하는지 확인
        Optional<User> existUser = userMapper.findByUsername(userRegisterDTO.getUsername());
        if(existUser.isPresent()) {
            throw new UsernameNotFoundException("Username " + userRegisterDTO.getUsername() + " 이미 존재띠");
        }
        userRegisterDTO.setPassword(bCryptPasswordEncoder.encode(userRegisterDTO.getPassword()));
        User user = User.createUser(userRegisterDTO);
        return userMapper.save(user);
    }

    //로그인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> existuser = userMapper.findByUsername(username);
        if (existuser.isPresent()){
            return new PrincipalDetails(existuser.get());
        }
        else{
            throw new UsernameNotFoundException("존재 하지 않는 아이띠!: " + username);
        }

    }
}
