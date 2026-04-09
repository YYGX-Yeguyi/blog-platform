```markdown
# 个人博客系统 - 后端

基于 SpringBoot + MyBatis-Plus + JWT 的个人博客后端 API

## 技术栈

- Java 17
- Spring Boot 2.7.18
- MyBatis-Plus 3.5.9
- MySQL 8.0
- JWT (jjwt 0.9.1)
- BCrypt 密码加密

## 项目结构

```
blog-platform/
├── blog-core/           # 后端代码
│   ├── controller/      # 控制器
│   ├── service/         # 业务层
│   ├── mapper/          # 数据访问层
│   ├── entity/          # 实体类
│   ├── dto/             # 数据传输对象
│   ├── config/          # 配置类
│   ├── interceptor/     # 拦截器
│   ├── annotation/      # 自定义注解
│   └── utils/           # 工具类
└── blog-ui/             # 前端（待开发）
```

## 数据库设计

- `user` - 管理员用户表
- `category` - 文章分类表
- `article` - 文章表

## 接口列表

| 模块 | 方法 | 路径 | 说明 | 认证 |
|------|------|------|------|------|
| 认证 | POST | `/api/auth/login` | 用户登录 | 否 |
| 分类 | GET | `/api/category/list` | 获取分类列表 | 否 |
| 文章 | GET | `/api/article/list` | 分页获取文章列表 | 否 |
| 文章 | GET | `/api/article/detail/{id}` | 获取文章详情 | 否 |
| 文章 | POST | `/api/article/save` | 发布/更新文章 | 是 |
| 文章 | DELETE | `/api/article/delete/{id}` | 删除文章 | 是 |

## 快速启动

### 1. 创建数据库

```sql
CREATE DATABASE blog_db;
-- 执行 docs/schema.sql 创建表结构
```

### 2. 修改配置

编辑 `application.properties`：

```properties
spring.datasource.username=root
spring.datasource.password=你的密码
```

### 3. 启动项目

运行 `BlogCoreApplication.java`

### 4. 测试登录

```bash
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "123456"
}
```

## 待完成

- [ ] 前端 Vue3 页面
- [ ] 文章更新接口
- [ ] 按分类筛选
- [ ] 评论功能

## 作者

YYGX-Yeguyi
```

---
