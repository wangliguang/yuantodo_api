package com.guang.yuantodo.controller;


import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.enums.TodoTypeEnum;
import com.guang.yuantodo.mapper.TodoMapper;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoMapper todoMapper;

    @RequestMapping("/create")
    public String create(Todo todo, HttpServletResponse response) {
        todoMapper.insert(todo);
        return todo.toString();
    }
}

