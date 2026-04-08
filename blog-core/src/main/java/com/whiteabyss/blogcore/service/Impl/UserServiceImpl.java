package com.whiteabyss.blogcore.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whiteabyss.blogcore.entity.Article;
import com.whiteabyss.blogcore.entity.User;
import com.whiteabyss.blogcore.mapper.ArticleMapper;
import com.whiteabyss.blogcore.mapper.UserMapper;
import com.whiteabyss.blogcore.service.ArticleService;
import com.whiteabyss.blogcore.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {
}
