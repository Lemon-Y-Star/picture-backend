package com.lemon.pictruebackend.exception;

import com.lemon.pictruebackend.common.BaseResponse;
import com.lemon.pictruebackend.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器类
 * 使用@RestControllerAdvice注解标记，实现全局异常处理
 *
 * @description 全局异常处理，统一处理系统中的各种异常情况
 * @Author : lemon
 * @create 2026/2/1 15:14
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理业务异常的方法
     * 当系统中抛出BusinessException异常时，此方法会被自动调用
     *
     * @param e 捕获到的BusinessException异常对象
     * @return 返回一个包含错误码和错误信息的BaseResponse对象
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        // 记录异常日志，便于后续排查问题
        log.error("BusinessException", e);
        // 调用ResultUtils工具类的方法，构建错误响应并返回
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    /**
     * 全局异常处理器 - 处理运行时异常
     * 当系统中出现未捕获的运行时异常时，此方法会被自动调用
     *
     * @param e 运行时异常对象
     * @return 返回统一的错误响应结果，包含系统错误信息
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");  // 返回系统错误信息
    }
}


