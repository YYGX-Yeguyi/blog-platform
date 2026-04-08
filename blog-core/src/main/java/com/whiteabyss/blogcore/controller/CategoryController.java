package com.whiteabyss.blogcore.controller;


import com.whiteabyss.blogcore.dto.Result;
import com.whiteabyss.blogcore.entity.Category;
import com.whiteabyss.blogcore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 获取所有分类（前台导航用）
    @GetMapping("/list")
    public Result list() {
        List<Category> list = categoryService.list();
        return Result.success(list);
    }
}