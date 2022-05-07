package com.guang.yuantodo.controller;


import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
    public String create() {
        Todo todo = new Todo();
         todo.setContent("第一个todo");
         todoMapper.insert(todo);
        return todo.toString();
    }
}

