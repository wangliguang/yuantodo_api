package com.guang.yuantodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/delete")
    @Transactional
    public String delete(@RequestBody Todo todo, HttpServletResponse response) {
        todoMapper.delete(new QueryWrapper(todo));
        return "";
    }

    @GetMapping("/queryAll")
    public List<Todo> queryAll(HttpServletResponse response) {
        List<Todo> todoList = todoMapper.selectList(null);
        HashMap resultMap = new HashMap();
        return todoList;
    }

}

