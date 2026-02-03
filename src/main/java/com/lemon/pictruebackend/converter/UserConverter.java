package com.lemon.pictruebackend.converter;

import com.lemon.pictruebackend.model.domain.User;
import com.lemon.pictruebackend.model.user.request.UserAddRequest;
import com.lemon.pictruebackend.model.user.request.UserUpdateRequest;
import com.lemon.pictruebackend.model.user.response.UserResponse;
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

    UserResponse toResp(User user);

    List<UserResponse> toRespList(List<User> user);

    User toUser(UserAddRequest userAddRequest);

    User toUser(UserUpdateRequest request);
}
