package com.whiteabyss.blogcore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.whiteabyss.blogcore.entity.Category;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}