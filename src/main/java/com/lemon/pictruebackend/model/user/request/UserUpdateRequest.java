package com.lemon.pictruebackend.model.user.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description : 用户添加请求
 * @Author : lemon
 * @create 2026/2/1 17:49
 */
@Data
public class UserUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7026429868389759711L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

}


