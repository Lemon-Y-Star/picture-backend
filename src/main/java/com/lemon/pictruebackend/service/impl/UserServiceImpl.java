package com.lemon.pictruebackend.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.pictruebackend.common.BasePageResponse;
import com.lemon.pictruebackend.common.Constants;
import com.lemon.pictruebackend.converter.UserConverter;
import com.lemon.pictruebackend.exception.AssertUtils;
import com.lemon.pictruebackend.exception.ErrorCode;
import com.lemon.pictruebackend.model.domain.User;
import com.lemon.pictruebackend.model.enums.UserRoleEnum;
import com.lemon.pictruebackend.model.user.request.UserAddRequest;
import com.lemon.pictruebackend.model.user.request.UserQueryRequest;
import com.lemon.pictruebackend.model.user.request.UserUpdateRequest;
import com.lemon.pictruebackend.model.user.response.UserResponse;
import com.lemon.pictruebackend.service.UserService;
import com.lemon.pictruebackend.dal.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import static com.lemon.pictruebackend.common.Constants.USER_LOGIN_STATE;

/**
 * @author lemon
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2026-02-01 16:48:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final UserService userService;

    public UserServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        AssertUtils.isNotBlank(userAccount, ErrorCode.PARAMS_ERROR, "参数为空");
        AssertUtils.isNotBlank(userPassword, ErrorCode.PARAMS_ERROR, "参数为空");
        AssertUtils.isNotBlank(checkPassword, ErrorCode.PARAMS_ERROR, "参数为空");

        AssertUtils.isTrue(userAccount.length() >= 4, ErrorCode.PARAMS_ERROR, "用户账号过短");
        AssertUtils.isTrue(userPassword.length() >= 8 && checkPassword.length() >= 8, ErrorCode.PARAMS_ERROR, "用户密码过短");
        AssertUtils.isTrue(userPassword.equals(checkPassword), ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");

        // 2. 检查是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.baseMapper.selectCount(queryWrapper);
        AssertUtils.isFalse(count > 0, ErrorCode.PARAMS_ERROR, "账号重复");

        // 3. 加密
        String encryptPassword = getEncryptPassword(userPassword);

        // 4. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean saveResult = this.save(user);
        AssertUtils.isTrue(saveResult, ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");

        return user.getId();
    }


    @Override
    public String getEncryptPassword(String userPassword) {
        return DigestUtils.md5DigestAsHex((Constants.SALT + userPassword).getBytes());
    }

    @Override
    public UserResponse userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        AssertUtils.isNotBlank(userAccount, ErrorCode.PARAMS_ERROR, "参数为空");
        AssertUtils.isNotBlank(userPassword, ErrorCode.PARAMS_ERROR, "参数为空");

        AssertUtils.isTrue(userAccount.length() >= 4, ErrorCode.PARAMS_ERROR, "账号错误");
        AssertUtils.isTrue(userPassword.length() >= 8, ErrorCode.PARAMS_ERROR, "密码错误");

        // 2. 加密
        String encryptPassword = getEncryptPassword(userPassword);
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        AssertUtils.isNotNull(user, ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");

        // 3. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return UserConverter.INSTANCE.toResp(user);
    }

    @Override
    public UserResponse getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;

        // 验证当前用户不为null且ID不为null
        AssertUtils.isNotNull(currentUser, ErrorCode.NOT_LOGIN_ERROR);
        AssertUtils.isNotNull(currentUser.getId(), ErrorCode.NOT_LOGIN_ERROR);

        // 从数据库查询（追求性能的话可以注释，直接返回上述结果）
        long userId = currentUser.getId();
        currentUser = this.getById(userId);

        // 验证从数据库查询到的用户不为null
        AssertUtils.isNotNull(currentUser, ErrorCode.NOT_LOGIN_ERROR);

        return UserConverter.INSTANCE.toResp(currentUser);
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        AssertUtils.isNotNull(userObj, ErrorCode.OPERATION_ERROR, "未登录");

        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }


    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        AssertUtils.isNotNull(userQueryRequest, ErrorCode.PARAMS_ERROR);
        Long id = userQueryRequest.getId();
        String userAccount = userQueryRequest.getUserAccount();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
        queryWrapper.eq(StrUtil.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StrUtil.isNotBlank(userAccount), "userAccount", userAccount);
        queryWrapper.like(StrUtil.isNotBlank(userName), "userName", userName);
        queryWrapper.like(StrUtil.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }

    @Override
    public Long addUser(UserAddRequest userAddRequest) {
        User user = UserConverter.INSTANCE.toUser(userAddRequest);
        String encryptPassword = getEncryptPassword(Constants.DEFAULT_PASSWORD);
        user.setUserPassword(encryptPassword);
        boolean result = save(user);
        AssertUtils.isTrue(result, ErrorCode.OPERATION_ERROR);
        return user.getId();
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = getById(id);
        AssertUtils.isNotNull(user, ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        return UserConverter.INSTANCE.toResp(user);
    }

    @Override
    public boolean updateUser(UserUpdateRequest request) {
        User user = UserConverter.INSTANCE.toUser(request);
        return updateById(user);
    }

    @Override
    public BasePageResponse<UserResponse> listUserByPage(UserQueryRequest request) {
        long current = request.getCurrent();
        long size = request.getPageSize();
        QueryWrapper<User> queryWrapper = getQueryWrapper(request);
        Page<User> page = userService.page(new Page<>(current, size), queryWrapper);
        return new BasePageResponse<>(request.getCurrent(), request.getPageSize(),
                page.getTotal(), UserConverter.INSTANCE.toRespList(page.getRecords()));
    }




}




