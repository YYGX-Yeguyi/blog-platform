package com.whiteabyss.blogcore.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("category")
public class Category {

    private int id;
    private String name;
    private int sortOrder;
    private LocalDateTime createTime ;

}
