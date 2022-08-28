package com.guang.yuantodo.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.entity.User;
import com.guang.yuantodo.mapper.TodoMapper;
import com.guang.yuantodo.mapper.UserMapper;
import com.guang.yuantodo.utils.JwtToken;
import com.guang.yuantodo.utils.response.ResultData;
import com.guang.yuantodo.utils.response.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王立广
 * @since 2022-05-07
 */
@RestController
@Api(tags = "用户表")
@RequestMapping("/api/user")
@Validated
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("注册")
    @PostMapping("/register")
    @Transactional
    public User register(String phone, String password, HttpServletResponse response) throws Exception {
        User user = new User();
        user.setMobile(phone);
        user.setPassword(password);
        boolean isExist = userMapper.exists(new QueryWrapper<>(user));
        if (isExist) {
            throw new Exception(ReturnCode.USERNAME_EXIST.getMessage());
        }
        userMapper.insert(user);
        return user;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    @Transactional
    public User login(@NotNull String mobile, @NotNull String password, HttpServletRequest request) throws Exception {
        User user = new User();
        user.setMobile(mobile);
        user.setPassword(password);
        boolean isExist = userMapper.exists(new QueryWrapper<>(user));
        if (isExist) {
            User result = userMapper.selectOne(new QueryWrapper<>(user));
            result.setToken(JwtToken.createToken());
            return result;
        }
        throw new Exception(ReturnCode.USERNAME_NO_EXIST.getMessage());
    }
}

