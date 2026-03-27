# 🏋️ 健身房管理系统 (Gym Management System)

一个功能完整的健身房预订和管理系统，采用前后端分离架构，支持场地预订、设备租赁、活动报名等功能。

## ✨ 项目简介

本项目是一个现代化的健身房管理系统，为用户提供便捷的场地和设备预订服务，同时为管理员提供高效的后台管理功能。系统支持多种运动场地（篮球、羽毛球、游泳等）和设备的在线预订，以及活动发布与报名功能。

## 🎯 主要功能

### 用户端功能
- 👤 用户注册与登录
- 🏟️ 场地预订（篮球场、羽毛球场、游泳池等）
- 🏋️ 设备租赁预订
- 🎉 活动报名参与
- 👤 个人信息管理
- 📋 预订记录查询
- 📱 移动端适配

### 管理员端功能
- 🔐 管理员注册与登录
- 📍 场地信息管理（增删改查）
- 🔧 设备信息管理
- 📢 活动信息发布与管理
- 📊 所有预订记录查看
- 👥 活动报名信息管理

## 🛠️ 技术栈

### 后端技术
- **框架**: Spring Boot 2.7.8
- **语言**: Java 17
- **数据库**: MySQL 8.0+
- **ORM框架**: MyBatis Plus 3.4.3
- **连接池**: Druid 1.2.15
- **身份验证**: JWT (jjwt 0.11.2)
- **加密**: Commons Codec (MD5)
- **构建工具**: Maven

### 前端技术
- **框架**: Vue 3
- **构建工具**: Vite 5.0.10
- **路由**: Vue Router 4.2.5
- **状态管理**: Vuex 4.0.2
- **UI组件**: Vant 4.8.2 (移动端组件库)
- **HTTP客户端**: Axios 1.6.5
- **移动端适配**: amfe-flexible + postcss-pxtorem
- **图表**: ECharts 5.5.0

## 📁 项目结构

```
GYM/
├── gym_back/              # 后端项目
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/mwh/gym_back/
│   │       │       ├── controller/    # 控制器层
│   │       │       ├── service/       # 业务逻辑层
│   │       │       ├── mapper/        # 数据访问层
│   │       │       ├── pojo/          # 实体类
│   │       │       └── utils/         # 工具类
│   │       └── resources/
│   │           └── application.yml    # 配置文件
│   └── pom.xml
│
└── gym_front/             # 前端项目
    ├── src/
    │   ├── views/        # 页面组件
    │   │   ├── user/     # 用户端页面
    │   │   └── admin/    # 管理员端页面
    │   ├── router/       # 路由配置
    │   ├── api/          # API接口
    │   ├── utils/        # 工具函数
    │   └── assets/       # 静态资源
    ├── public/
    └── package.json
```

## 🚀 快速开始

### 环境要求
- JDK 17+
- Node.js 16+
- MySQL 8.0+
- Maven 3.6+

### 数据库配置

1. 创建数据库：
```sql
CREATE DATABASE gym_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 修改后端配置文件 `gym_back/src/main/resources/application.yml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gym_db
    username: root
    password: your_password  # 修改为你的密码
```

### 后端启动

```bash
cd gym_back
mvn clean install
mvn spring-boot:run
```

后端服务将运行在 `http://localhost:8081`

### 前端启动

```bash
cd gym_front
npm install
npm run dev
```

前端服务将运行在 `http://localhost:5173`

## 📱 系统截图

### 用户端
- 首页展示各类运动场地
- 场地详情与预订
- 设备租赁
- 活动报名
- 个人中心

### 管理员端
- 场地管理
- 设备管理
- 活动管理
- 预订记录查看

## 🔐 安全特性

- JWT Token 身份认证
- 用户和管理员双角色权限管理
- Token 过期自动刷新
- 路由守卫保护
- 密码 MD5 加密存储

## 📄 API文档

后端API接口遵循RESTful规范，主要接口包括：

- `/user/*` - 用户相关接口
- `/admin/*` - 管理员相关接口
- `/venue/*` - 场地相关接口
- `/equip/*` - 设备相关接口
- `/activity/*` - 活动相关接口

详细的API文档请查看后端项目README。

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📝 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 👥 作者

- 项目作者: Marvin

## 📮 联系方式

如有问题或建议，请通过以下方式联系：
- 提交 Issue
- 发送邮件: 2217194863@qq.com

## 🙏 致谢

感谢以下开源项目：
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Vue.js](https://vuejs.org/)
- [Vant](https://vant-ui.github.io/)
- [MyBatis Plus](https://baomidou.com/)

---

⭐ 如果这个项目对你有帮助，请给个 Star！
