package com.devkduck.letterfromthesea.user.mapper;


import com.devkduck.letterfromthesea.user.domain.Userdto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    Userdto findByUsername(String username);
    void save(Userdto user);
}
