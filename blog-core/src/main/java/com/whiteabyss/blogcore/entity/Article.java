package com.whiteabyss.blogcore.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("article")
public class Article {

        @TableId(type = IdType.AUTO)
        private Integer id;

        private String title;
        private String content;
        private String summary;
        private String coverUrl;
        private Integer categoryId;
        private Integer viewCount;
        private Integer likeCount;
        private Integer status;
        private Integer isTop;

        @TableField(fill = FieldFill.INSERT)
        private LocalDateTime createTime;

        @TableField(fill = FieldFill.INSERT_UPDATE)
        private LocalDateTime updateTime;

}
