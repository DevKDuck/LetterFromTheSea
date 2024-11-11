package com.devkduck.letterfromthesea.user.mapper;

import com.devkduck.letterfromthesea.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;


@Mapper
public interface UserMapper {
    Optional<User> findByUsername(String username);
    int save(User user);
}
