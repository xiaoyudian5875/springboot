package com.example.springboot.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.bean.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 业务层，处理具体业务
 *
 */
@Service//业务注解
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
