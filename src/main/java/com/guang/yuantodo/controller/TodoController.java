package com.guang.yuantodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.entity.User;
import com.guang.yuantodo.enums.TodoTypeEnum;
import com.guang.yuantodo.mapper.TodoMapper;
import com.guang.yuantodo.requestbody.RequestBodyCreateTodo;
import com.guang.yuantodo.requestbody.RequestBodyDragTodo;
import com.guang.yuantodo.requestbody.RequestBodyTodo;
import com.guang.yuantodo.requestbody.RequestBodyUpdateTodo;
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
import java.util.Date;
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
    public Integer create(@Validated @RequestBody RequestBodyCreateTodo body, HttpServletResponse response) {
        Todo todo = new Todo();
        todo.setType(body.getType());
        todo.setContent(body.getContent());
        todo.setSort(body.getSort());
        todo.setDone(0);
        todoMapper.insert(todo);
        return todo.getTId();

    }

    @ApiOperation("更新todo")
    @PostMapping("/update")
    @Transactional
    public String update(@Validated @RequestBody RequestBodyUpdateTodo body) throws Exception {
        Todo todo = new Todo();
        todo.setType(body.getType());
        todo.setTId(body.getT_id());
        todo.setContent(body.getContent());
        todo.setDone(body.getDone());
        todo.setSort(body.getSort());
        Integer row = todoMapper.updateById(todo);
        if (0 == row) {
            throw new Exception("该tId不存在");
        }
        return "";
    }

    @ApiOperation("拖动位置")
    @PostMapping("/dragSort")
    @Transactional
    public String dragSort(@Validated @RequestBody RequestBodyDragTodo body) throws Exception {
        Todo sourceTodo = new Todo();
        sourceTodo.setTId(body.getSourceTodoId());
        sourceTodo.setType(body.getSourceTodoType());
        sourceTodo.setSort(body.getSourceTodoSort());
        Integer sourceRow = todoMapper.updateById(sourceTodo);
        if (0 == sourceRow) {
            throw new Exception("该tId不存在");
        }

        Todo DestTodo = new Todo();
        DestTodo.setTId(body.getDestTodoId());
        DestTodo.setType(body.getDestTodoType());
        DestTodo.setSort(body.getDestTodoSort());
        Integer destRow = todoMapper.updateById(sourceTodo);
        if (0 == destRow) {
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
    public Todo queryById(Integer t_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Todo todo = todoMapper.selectById(t_id);
        if (null == todo) {
            throw new Exception("该todo不存在");
        }
        return todo;
    }

    @ApiOperation("查询所有todo")
    @PostMapping("/queryAll")
    public HashMap queryAll(@Validated @RequestBody RequestBodyTodo body, HttpServletResponse response) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("create_time", body.getBeginDate(), body.getEndDate());
        queryWrapper.orderByAsc("sort");
        List<Todo> allTodoList = todoMapper.selectList(queryWrapper);
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

