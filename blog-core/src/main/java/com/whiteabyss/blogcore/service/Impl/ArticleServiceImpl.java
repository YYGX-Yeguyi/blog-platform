package com.whiteabyss.blogcore.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whiteabyss.blogcore.entity.Article;
import com.whiteabyss.blogcore.mapper.ArticleMapper;
import com.whiteabyss.blogcore.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl
        extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
}
