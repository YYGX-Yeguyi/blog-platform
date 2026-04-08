package com.whiteabyss.blogcore.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Catagory {

    private int id;
    private String name;
    private int sortOrder;
    private LocalDateTime createTime ;

}
