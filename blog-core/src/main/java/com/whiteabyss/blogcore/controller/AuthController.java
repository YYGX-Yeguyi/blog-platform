package com.whiteabyss.blogcore.controller;


import com.whiteabyss.blogcore.dto.LoginDTO;
import com.whiteabyss.blogcore.dto.Result;
import com.whiteabyss.blogcore.entity.User;
import com.whiteabyss.blogcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        // 先简单实现：查询用户名匹配
        User user = userService.lambdaQuery()
                .eq(User::getUsername, loginDTO.getName())
                .one();

        if (user == null) {
            return Result.error("用户不存在");
        }

        // 注意：密码加密暂时跳过，直接比对明文
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            return Result.error("密码错误");
        }

        // TODO: 生成 JWT 并返回
        return Result.success(200, user);
    }
}