package com.lemon.pictruebackend.model.user.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description
 * @Author : lemon
 * @create 2026/2/1 16:55
 */
@Data
public class UserRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8087555683119155487L;
    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}
