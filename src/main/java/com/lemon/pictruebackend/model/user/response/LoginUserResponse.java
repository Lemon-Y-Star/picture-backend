package com.lemon.pictruebackend.model.user.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description : 登录用户响应
 * @Author : lemon
 * @create 2026/2/1 17:28
 */
@Data
public class LoginUserResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -2913444153811392316L;
    /**
     * 用户 id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
