package com.guang.yuantodo.controller;


import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

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

    @PostMapping("/create")
    @Transactional
    public String create(@Validated @RequestBody Todo todo, HttpServletResponse response) {
        todoMapper.insert(todo);
        return "";
    }
}

