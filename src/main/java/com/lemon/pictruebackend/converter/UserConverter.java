package com.lemon.pictruebackend.converter;

import com.lemon.pictruebackend.model.domain.User;
import com.lemon.pictruebackend.model.user.response.LoginUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description : 用户转换器
 * @Author : lemon
 * @create 2026/2/1 17:30
 */
@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    LoginUserResponse toResp(User user);

    List<LoginUserResponse> toRespList(List<User> user);
}
