package com.lemon.pictruebackend.model.user.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description : 用户登录请求体
 * @Author : lemon
 * @create 2026/2/1 17:24
 */
@Data
public class UserLoginRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = 2092985349723520641L;
    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
