package com.guang.yuantodo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.entity.User;
import com.guang.yuantodo.enums.TodoTypeEnum;
import com.guang.yuantodo.mapper.TodoMapper;
import com.guang.yuantodo.requestbody.RequestBodyCreateTodo;
import com.guang.yuantodo.requestbody.RequestBodyDeleteTodo;
import com.guang.yuantodo.requestbody.RequestBodyQueryTodoList;
import com.guang.yuantodo.requestbody.RequestBodyUpdateTodo;
import com.guang.yuantodo.utils.JwtToken;
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
    public Integer create(@Validated @RequestBody RequestBodyCreateTodo body, HttpServletRequest req) throws Exception {

        String token = req.getHeader("authorization");
        Integer uId = JwtToken.getUid(token);

        Todo todo = new Todo();
        todo.setType(body.getType());
        todo.setUId(uId);
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

    @ApiOperation("删除todo")
    @PostMapping("/delete")
    @Transactional
    public String delete(@Validated @RequestBody RequestBodyDeleteTodo body, HttpServletResponse response) throws Exception {
        Todo todo = new Todo();
        todo.setTId(body.getTId());
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
    @PostMapping("/queryTodoListByDateZone")
    public HashMap queryTodoListByDateZone(@Validated @RequestBody RequestBodyQueryTodoList body, HttpServletRequest req) throws Exception {

        String token = req.getHeader("authorization");
        Integer uId = JwtToken.getUid(token);


        Todo queryTodo = new Todo();
        queryTodo.setUId(uId);
        QueryWrapper queryWrapper = new QueryWrapper(queryTodo);
        queryWrapper.between("plan_time", body.getBeginDate(), body.getEndDate());
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

