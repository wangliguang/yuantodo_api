package com.guang.yuantodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.enums.TodoTypeEnum;
import com.guang.yuantodo.mapper.TodoMapper;
import com.guang.yuantodo.utils.aop.AuthToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王立广
 * @since 2022-05-07
 */
@RestController
@RequestMapping("/api/todo")
@Api(tags = "todo表")
@AuthToken()
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoMapper todoMapper;

    @ApiOperation("新建todo")
    @PostMapping("/create")
    @Transactional
    public String create(TodoTypeEnum type, String content, HttpServletResponse response) {
        Todo todo = new Todo();
        todo.setType(type);
        todo.setContent(content);
        todo.setDone(0);
        todoMapper.insert(todo);
        return "";
    }

    @ApiOperation("更新todo")
    @PostMapping("/update")
    @Transactional
    public String update(TodoTypeEnum type, String content, Integer done, Integer t_id) throws Exception {
        Todo todo = new Todo();
        todo.setType(type);
        todo.setTId(t_id);
        todo.setContent(content);
        todo.setDone(done);
        Integer row = todoMapper.updateById(todo);
        if (0 == row) {
            throw new Exception("该tId不存在");
        }
        return "";
    }

    @ApiOperation("删除todo")
    @PostMapping("/delete")
    @Transactional
    public String delete(Integer t_id, HttpServletResponse response) throws Exception {
        Todo todo = new Todo();
        todo.setTId(t_id);
        Integer row = todoMapper.delete(new QueryWrapper(todo));
        if (0 == row) {
            throw new Exception("该tId不存在");
        }
        return "";
    }

    @ApiOperation("根据ID查询todo")
    @GetMapping("")
    @Transactional
    public Todo queryById(Integer t_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Todo todo = todoMapper.selectById(t_id);
        if (null == todo) {
            throw new Exception("该todo不存在");
        }
        return todo;
    }

    @ApiOperation("查询所有todo")
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

