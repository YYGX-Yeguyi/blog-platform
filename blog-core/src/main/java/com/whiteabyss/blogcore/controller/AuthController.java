package com.whiteabyss.blogcore.controller;

import com.whiteabyss.blogcore.dto.LoginDTO;
import com.whiteabyss.blogcore.dto.Result;
import com.whiteabyss.blogcore.entity.User;
import com.whiteabyss.blogcore.service.UserService;
import com.whiteabyss.blogcore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        // 查询用户
        User user = userService.lambdaQuery()
                .eq(User::getUsername, loginDTO.getUsername())
                .one();

        if (user == null) {
            return Result.error("用户不存在");
        }

        // 验证密码（BCrypt 匹配）
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return Result.error("密码错误");
        }

        // 生成 token
        String token = jwtUtil.generateToken(user.getId());

        // 返回 token
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("userId", user.getId());
        data.put("username", user.getUsername());

        return Result.success(data);
    }
}