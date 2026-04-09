package com.whiteabyss.blogcore.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("category")
public class Category {

    private Integer id;
    private String name;
    private Integer sortOrder;
    private LocalDateTime createTime ;

}
