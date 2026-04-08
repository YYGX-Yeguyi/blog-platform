package com.whiteabyss.blogcore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whiteabyss.blogcore.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}