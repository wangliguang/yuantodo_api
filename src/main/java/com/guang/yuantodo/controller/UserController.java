package com.guang.yuantodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guang.yuantodo.entity.User;
import com.guang.yuantodo.mapper.UserMapper;
import com.guang.yuantodo.requestbody.RequestBodyUser;
import com.guang.yuantodo.utils.JwtToken;
import com.guang.yuantodo.utils.response.CustomException;
import com.guang.yuantodo.utils.response.CustomHttpStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("注册")
    @PostMapping("/register")
    @Transactional
    public User register(@Validated @RequestBody RequestBodyUser body, HttpServletResponse response) throws Exception {
        User user = new User();
        user.setMobile(body.getMobile());
        user.setPassword(body.getPassword());
        boolean isExist = userMapper.exists(new QueryWrapper<>(user));
        if (isExist) {
            throw new Exception(CustomHttpStatus.USERNAME_EXIST.getMessage());
        }
        userMapper.insert(user);
        return user;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public User login(@Validated @RequestBody RequestBodyUser body, HttpServletRequest request) throws Exception {
        User user = new User();
        user.setMobile(body.getMobile());
        user.setPassword(body.getPassword());
        boolean isExist = userMapper.exists(new QueryWrapper<>(user));
        if (isExist) {
            User result = userMapper.selectOne(new QueryWrapper<>(user));
            result.setToken(JwtToken.createToken());
            return result;
        }
        throw new CustomException(CustomHttpStatus.USERNAME_NO_EXIST);
    }
}

