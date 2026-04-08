
package com.whiteabyss.blogcore.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whiteabyss.blogcore.entity.Category;
import com.whiteabyss.blogcore.mapper.CategoryMapper;
import com.whiteabyss.blogcore.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl
        extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {
}
