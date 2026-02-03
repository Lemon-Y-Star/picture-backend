package com.lemon.pictruebackend.dal;

import com.lemon.pictruebackend.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lemon
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2026-02-01 16:48:19
* @Entity com.lemon.pictruebackend.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




