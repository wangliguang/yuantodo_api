package com.guang.yuantodo.service.impl;

import com.guang.yuantodo.entity.User;
import com.guang.yuantodo.mapper.UserMapper;
import com.guang.yuantodo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
