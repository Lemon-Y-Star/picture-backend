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
public class UserAddRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3113782852370324242L;
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

}

