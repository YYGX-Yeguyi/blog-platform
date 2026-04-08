package com.whiteabyss.blogcore.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("admin")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;
    private String password;
}