package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.bean.User;

/**
 * 业务处理接口 被controller层引用
 */
public interface UserService extends IService<User> {

}
