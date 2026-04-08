package com.whiteabyss.blogcore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whiteabyss.blogcore.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}