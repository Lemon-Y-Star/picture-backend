package com.lemon.pictruebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.pictruebackend.common.BasePageResponse;
import com.lemon.pictruebackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.pictruebackend.model.user.request.UserAddRequest;
import com.lemon.pictruebackend.model.user.request.UserQueryRequest;
import com.lemon.pictruebackend.model.user.request.UserUpdateRequest;
import com.lemon.pictruebackend.model.user.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author lemon
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2026-02-01 16:48:19
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 获取加密后的密码
     *
     * @param userPassword 用户密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request       请求
     * @return 脱敏后的用户信息
     */
    UserResponse userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 请求
     * @return 当前登录用户
     */
    UserResponse getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request 请求
     * @return 是否注销成功
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取查询条件
     * @param userQueryRequest 查询请求
     * @return 查询条件
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    /**
     * 添加用户
     * @param userAddRequest 添加用户请求
     */
    Long addUser(UserAddRequest userAddRequest);

    /**
     * 根据id查询用户
     * @param id  用户id
     * @return 用户信息
     */
    UserResponse getUserById(Long id);

    /**
     * 更新用户
     * @param request 更新用户请求
     * @return 是否更新成功
     */
    boolean updateUser(UserUpdateRequest request);

    /**
     * 分页查询用户
     * @param request 查询请求
     * @return 用户列表
     */
    BasePageResponse<UserResponse> listUserByPage(UserQueryRequest request);

}
