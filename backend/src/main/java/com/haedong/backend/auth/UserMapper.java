package com.haedong.backend.auth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findById(String id);
}
