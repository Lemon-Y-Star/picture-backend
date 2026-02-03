package com.lemon.pictruebackend.controller;

import com.lemon.pictruebackend.annotation.AuthCheck;
import com.lemon.pictruebackend.common.*;
import com.lemon.pictruebackend.exception.AssertUtils;
import com.lemon.pictruebackend.exception.ErrorCode;
import com.lemon.pictruebackend.model.user.request.*;
import com.lemon.pictruebackend.model.user.response.UserResponse;
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
    public BaseResponse<UserResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        AssertUtils.isTrue(userLoginRequest != null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        UserResponse resp = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(resp);
    }

    /**
     * 获取当前登录用户
     * @param request 请求参数
     * @return 登录用户响应
     */
    @GetMapping("/get/login")
    public BaseResponse<UserResponse> getLoginUser(HttpServletRequest request) {
        UserResponse user = userService.getLoginUser(request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        AssertUtils.isTrue(request != null, ErrorCode.PARAMS_ERROR);
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 创建用户
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = Constants.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        AssertUtils.isNotNull(userAddRequest, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userService.addUser(userAddRequest));
    }

    /**
     * 根据 id 获取用户（仅管理员）
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = Constants.ADMIN_ROLE)
    public BaseResponse<UserResponse> getUserById(long id) {
        AssertUtils.isNotNull(id, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userService.getUserById(id));
    }


    /**
     * 删除用户（仅管理员）
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = Constants.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest request) {
        AssertUtils.isNotNull(request, ErrorCode.PARAMS_ERROR);
        AssertUtils.isTrue(request.getId() > 0, ErrorCode.PARAMS_ERROR);
        boolean result = userService.removeById(request.getId());
        return ResultUtils.success(result);
    }

    /**
     * 更新用户（仅管理员）
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = Constants.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest request) {
        AssertUtils.isNotNull(request, ErrorCode.PARAMS_ERROR);
        AssertUtils.isNotNull(request.getId(), ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userService.updateUser(request));
    }

    /**
     * 分页获取用户封装列表（仅管理员）
     */
    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = Constants.ADMIN_ROLE)
    public BaseResponse<BasePageResponse<UserResponse>> listUserVOByPage(@RequestBody UserQueryRequest request) {
        AssertUtils.isNotNull(request, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userService.listUserByPage(request));
    }

}
