package com.lemon.pictruebackend.common;

import com.lemon.pictruebackend.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**

 * 通用基础响应类，用于封装API返回结果
 * @description 提供统一的API响应格式，包含状态码、数据和消息
 * @Author : lemon
 * @create 2026/2/1 15:11
 */
@Data
public class BaseResponse<T> implements Serializable {

    // 响应状态码，通常用于表示请求处理状态
    private int code;

    // 响应数据，使用泛型T支持不同类型的数据
    private T data;

    // 响应消息，用于返回额外的提示信息
    private String message;

    /**
     * 全参构造方法
     * @param code 响应状态码
     * @param data 响应数据
     * @param message 响应消息
     */
    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * 部分参数构造方法，默认消息为空字符串
     * @param code 响应状态码
     * @param data 响应数据
     */
    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    /**
     * 枚举参数构造方法，使用错误码枚举创建响应对象
     * @param errorCode 错误码枚举，包含状态码和消息
     */
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}


