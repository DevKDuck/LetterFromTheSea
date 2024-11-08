package com.devkduck.letterfromthesea.user.service;

import com.devkduck.letterfromthesea.user.auth.PrincipalDetails;
import com.devkduck.letterfromthesea.user.domain.User;
import com.devkduck.letterfromthesea.user.domain.Userdto;
import com.devkduck.letterfromthesea.user.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = loginMapper.findByUsername(username);
        Userdto user = loginMapper.findByUsername(username);
        if (user != null){
            return new PrincipalDetails(user);
        }
        return null;
    }
}
