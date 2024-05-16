package com.vueblog.service.impl;

import com.vueblog.entity.Blog;
import com.vueblog.mapper.BlogMapper;
import com.vueblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
