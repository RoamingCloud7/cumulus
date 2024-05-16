package com.vueblog.service.impl;

import com.vueblog.entity.User;
import com.vueblog.mapper.UserMapper;
import com.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
