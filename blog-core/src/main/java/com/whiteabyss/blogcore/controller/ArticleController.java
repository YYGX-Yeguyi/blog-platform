package com.whiteabyss.blogcore.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whiteabyss.blogcore.dto.Result;
import com.whiteabyss.blogcore.entity.Article;
import com.whiteabyss.blogcore.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 分页查询文章列表（前台）
    @GetMapping("/list")
    public Result list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer categoryId) {
        Page<Article> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        // 只查询已发布的文章
        wrapper.eq(Article::getStatus, 1);
        wrapper.orderByDesc(Article::getIsTop);
        wrapper.orderByDesc(Article::getCreateTime);

        if (categoryId != null) {
            wrapper.eq(Article::getCategoryId, categoryId);
        }

        Page<Article> result = articleService.page(pageParam, wrapper);
        return Result.success(result);
    }

    // 根据ID查询文章详情
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        // 浏览次数+1
        article.setViewCount(article.getViewCount() + 1);
        articleService.updateById(article);
        return Result.success(article);
    }

    // 发布或更新文章（需要登录）
    @PostMapping("/save")
    public Result save(@RequestBody Article article) {
        if (article.getId() == null) {
            article.setViewCount(0);
            article.setLikeCount(0);
        }
        articleService.saveOrUpdate(article);
        return Result.success();
    }

    // 删除文章
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        articleService.removeById(id);
        return Result.success();
    }
}