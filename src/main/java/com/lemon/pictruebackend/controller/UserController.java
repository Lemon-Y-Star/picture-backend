package com.lemon.pictruebackend.controller;

import com.lemon.pictruebackend.common.BaseResponse;
import com.lemon.pictruebackend.common.ResultUtils;
import com.lemon.pictruebackend.exception.AssertUtils;
import com.lemon.pictruebackend.exception.ErrorCode;
import com.lemon.pictruebackend.model.user.request.UserLoginRequest;
import com.lemon.pictruebackend.model.user.request.UserRegisterRequest;
import com.lemon.pictruebackend.model.user.response.LoginUserResponse;
import com.lemon.pictruebackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description : 用户接口
 * @Author : lemon
 * @create 2026/2/1 17:03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequest 用户注册请求
     * @return 注册结果
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        AssertUtils.isTrue(userRegisterRequest != null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     * @param userLoginRequest 用户登录请求
     * @param request 请求参数
     * @return 登录用户响应
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        AssertUtils.isTrue(userLoginRequest != null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        LoginUserResponse resp = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(resp);
    }

    /**
     * 获取当前登录用户
     * @param request 请求参数
     * @return 登录用户响应
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserResponse> getLoginUser(HttpServletRequest request) {
        LoginUserResponse user = userService.getLoginUser(request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        AssertUtils.isTrue(request != null, ErrorCode.PARAMS_ERROR);
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

}
