package com.guang.yuantodo.service.impl;

import com.guang.yuantodo.entity.Todos;
import com.guang.yuantodo.mapper.TodosMapper;
import com.guang.yuantodo.service.ITodosService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王立广
 * @since 2022-05-06
 */
@Service
public class TodosServiceImpl extends ServiceImpl<TodosMapper, Todos> implements ITodosService {

}
