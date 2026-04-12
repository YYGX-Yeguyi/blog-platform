package com.whiteabyss.blogcore.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whiteabyss.blogcore.annotation.LoginRequired;
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
    @LoginRequired
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
    @LoginRequired
    public Result delete(@PathVariable Integer id) {
        boolean exists = articleService.lambdaQuery()
                .eq(Article::getId, id)
                .exists();

        if (!exists) {
            return Result.error("文章不存在");
        }
        boolean removed = articleService.removeById(id);
        if (removed) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @PutMapping("/update")
    @LoginRequired
    public Result update(@RequestBody Article article) {
        // 1. 检查 id 是否为空
        if (article.getId() == null) {
            return Result.error("文章ID不能为空");
        }

        // 2. 检查文章是否存在
        Article existingArticle = articleService.getById(article.getId());
        if (existingArticle == null) {
            return Result.error("文章不存在");
        }

        // 3. 只更新允许修改的字段（防止覆盖不该覆盖的字段）
        existingArticle.setTitle(article.getTitle());
        existingArticle.setContent(article.getContent());
        existingArticle.setSummary(article.getSummary());
        existingArticle.setCategoryId(article.getCategoryId());
        existingArticle.setStatus(article.getStatus());
        existingArticle.setCoverUrl(article.getCoverUrl());
        existingArticle.setIsTop(article.getIsTop()); 

        // 4. 执行更新（注意：用 updateById，不是 saveOrUpdate）
        articleService.updateById(existingArticle);

        return Result.success();
    }
}