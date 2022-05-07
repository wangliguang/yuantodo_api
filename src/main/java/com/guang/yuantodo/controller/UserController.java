package com.guang.yuantodo.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.entity.User;
import com.guang.yuantodo.mapper.TodoMapper;
import com.guang.yuantodo.mapper.UserMapper;
import com.guang.yuantodo.utils.response.ResultData;
import com.guang.yuantodo.utils.response.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    @Transactional
    public User register(@Validated @RequestBody User user, HttpServletResponse response) throws Exception {
        boolean isExist = userMapper.exists(new QueryWrapper<>(user));
        if (isExist) {
            throw new Exception(ReturnCode.USERNAME_EXIST.getMessage());
        }
        userMapper.insert(user);
        return user;
    }

    @PostMapping("/login")
    @Transactional
    public String login(@Validated User user, HttpServletResponse response) {
        return "";
    }
}

