package com.guang.yuantodo.service.impl;

import com.guang.yuantodo.entity.Todo;
import com.guang.yuantodo.mapper.TodoMapper;
import com.guang.yuantodo.service.ITodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王立广
 * @since 2022-05-07
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements ITodoService {

}
