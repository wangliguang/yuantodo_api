package com.guang.yuantodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.enums.TodoTypeEnum;
import com.guang.yuantodo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @PostMapping("/update")
    @Transactional
    public String update(@Validated @RequestBody Todo todo) throws Exception {
        Integer row = todoMapper.updateById(todo);
        if (0 == row) {
            throw new Exception("该tId不存在");
        }
        return "";
    }

    @PostMapping("/delete")
    @Transactional
    public String delete(@RequestBody Todo todo, HttpServletResponse response) {
        todoMapper.delete(new QueryWrapper(todo));
        return "";
    }

    @GetMapping("")
    @Transactional
    public Todo queryById(Integer t_id, HttpServletResponse response) throws Exception {
        Todo todo = todoMapper.selectById(t_id);
        if (null == todo) {
            throw new Exception("该todo不存在");
        }
        return todo;
    }

    @GetMapping("/queryAll")
    public HashMap queryAll(HttpServletResponse response) {
        List<Todo> allTodoList = todoMapper.selectList(null);
        List<Todo> imUrTodoList = new ArrayList<>();
        List<Todo> imNoUrTodoList = new ArrayList<>();
        List<Todo> noImUrTodoList = new ArrayList<>();
        List<Todo> noImNoUrTodoList = new ArrayList<>();
        allTodoList.stream().forEach((todo) -> {
            if (todo.getType() == TodoTypeEnum.IM_UR) {
                imUrTodoList.add(todo);
            }
            if (todo.getType() == TodoTypeEnum.IM_noUR) {
                imNoUrTodoList.add(todo);
            }
            if (todo.getType() == TodoTypeEnum.noIM_UR) {
                noImUrTodoList.add(todo);
            }
            if (todo.getType() == TodoTypeEnum.noIM_noUR) {
                noImNoUrTodoList.add(todo);
            }});
        HashMap data = new HashMap();
        data.put("imUr", imUrTodoList);
        data.put("imNoUr", imNoUrTodoList);
        data.put("noImUr", noImUrTodoList);
        data.put("noImNoUr", noImNoUrTodoList);
        return data;
    }

}

