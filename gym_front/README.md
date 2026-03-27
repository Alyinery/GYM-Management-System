# 健身房管理系统 - 前端 (Gym Management System Frontend)

健身房管理系统的前端应用，基于 Vue 3 + Vite 开发，提供移动端友好的用户界面。

## 📋 项目简介

本前端项目为健身房管理系统提供完整的用户界面，包括用户端和管理员端两个子系统，支持场地预订、设备租赁、活动报名等功能，采用移动端优先的设计理念。

## 🛠️ 技术栈

- **框架**: Vue 3
- **构建工具**: Vite 5.0.10
- **路由**: Vue Router 4.2.5
- **状态管理**: Vuex 4.0.2
- **UI组件**: Vant 4.8.2 (移动端组件库)
- **HTTP客户端**: Axios 1.6.5
- **移动端适配**: amfe-flexible + postcss-pxtorem
- **图表**: ECharts 5.5.0
- **开发语言**: JavaScript

## 📁 项目结构

```
gym_front/
├── public/                          # 公共静态资源
│   ├── basketball_1.jpg
│   └── favicon.ico
├── src/
│   ├── api/                         # API接口封装
│   │   └── user.js
│   ├── assets/                      # 静态资源
│   │   ├── imgs/                    # 图片资源
│   │   │   ├── carousels/           # 轮播图
│   │   │   ├── sporttype/           # 运动类型图片
│   │   │   └── ...
│   │   ├── base.css                 # 基础样式
│   │   ├── main.css                 # 主样式
│   │   └── logo.svg
│   ├── router/                      # 路由配置
│   │   └── index.js
│   ├── style/                       # 样式文件
│   │   └── vant.less
│   ├── utils/                       # 工具函数
│   │   └── request.js               # Axios请求封装
│   ├── views/                       # 页面组件
│   │   ├── user/                    # 用户端页面
│   │   │   ├── Login.vue            # 登录页
│   │   │   ├── Register.vue         # 注册页
│   │   │   ├── Information.vue      # 个人信息
│   │   │   ├── UserSetting.vue      # 用户设置
│   │   │   ├── mysportlog.vue       # 运动记录
│   │   │   ├── ActivityRegisterInfo.vue  # 活动报名信息
│   │   │   ├── Bookdetail/          # 场地预订详情
│   │   │   │   ├── UserBookInfo.vue
│   │   │   │   ├── BookedInfo.vue
│   │   │   │   ├── BookingInfo.vue
│   │   │   │   └── UnBookedInfo.vue
│   │   │   └── Equipdetail/         # 设备预订详情
│   │   │       ├── UserEquipBookInfo.vue
│   │   │       ├── EquipBookedInfo.vue
│   │   │       └── EquipBookingInfo.vue
│   │   ├── admin/                   # 管理员端页面
│   │   │   ├── AdminLogin.vue       # 管理员登录
│   │   │   ├── AdminRegister.vue    # 管理员注册
│   │   │   ├── AdminDetail.vue      # 管理员主页
│   │   │   ├── VenueInfo.vue        # 场地管理
│   │   │   ├── AdminVenuedetail.vue # 场地详情
│   │   │   ├── EquipInfo.vue        # 设备管理
│   │   │   ├── ActivitiesInfo.vue   # 活动管理
│   │   │   ├── BookInfo.vue         # 预订管理
│   │   │   ├── BookingAllInfo.vue   # 所有预订
│   │   │   ├── BookedAllInfo.vue    # 已完成预订
│   │   │   ├── UnBookedAllInfo.vue  # 未完成预订
│   │   │   └── ActivityRegisterAllInfo.vue  # 活动报名信息
│   │   ├── Home.vue                 # 首页
│   │   ├── Book.vue                 # 场地预订
│   │   ├── Equip.vue                # 设备租赁
│   │   ├── EquipDetail.vue          # 设备详情
│   │   ├── Activities.vue           # 活动页面
│   │   ├── VenueDetail.vue          # 场地详情
│   │   └── Mode.vue                 # 模式选择
│   ├── App.vue                      # 根组件
│   └── main.js                      # 入口文件
├── .gitignore
├── index.html                       # HTML模板
├── jsconfig.json                    # JS配置
├── package.json                     # 依赖配置
├── postcss.config.cjs               # PostCSS配置
├── vite.config.js                   # Vite配置
└── README.md
```

## 🚀 快速开始

### 环境要求

- Node.js 16+
- npm 或 yarn 或 pnpm

### 安装依赖

```bash
npm install
```

### 开发环境运行

```bash
npm run dev
```

应用将在 `http://localhost:5173` 启动

### 生产环境构建

```bash
npm run build
```

构建产物将输出到 `dist` 目录

### 预览生产构建

```bash
npm run preview
```

## 📱 功能模块

### 用户端功能

#### 首页 (Home)
- 轮播图展示
- 运动类型分类
- 热门场地推荐
- 最新活动展示

#### 用户认证
- 用户登录
- 用户注册
- Token 自动管理
- 路由守卫保护

#### 场地预订
- 场地列表浏览
- 场地详情查看
- 在线预订场地
- 预订记录管理
- 预订状态跟踪

#### 设备租赁
- 设备列表浏览
- 设备详情查看
- 在线租赁设备
- 租赁记录管理
- 租赁状态跟踪

#### 活动报名
- 活动列表浏览
- 活动详情查看
- 在线报名活动
- 报名记录管理

#### 个人中心
- 个人信息查看
- 个人信息修改
- 运动记录查看
- 账户设置

### 管理员端功能

#### 管理员认证
- 管理员登录
- 管理员注册
- Token 自动管理
- 路由守卫保护

#### 场地管理
- 场地列表查看
- 场地信息添加
- 场地信息编辑
- 场地信息删除
- 场地详情查看

#### 设备管理
- 设备列表查看
- 设备信息添加
- 设备信息编辑
- 设备信息删除

#### 活动管理
- 活动列表查看
- 活动信息发布
- 活动信息编辑
- 活动信息删除

#### 预订管理
- 所有预订记录查看
- 已完成预订查看
- 未完成预订查看
- 预订详情查看

#### 活动报名管理
- 活动报名信息查看
- 报名用户列表

## 🔧 配置说明

### Vite 配置 (vite.config.js)

```javascript
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8081',
        changeOrigin: true
      }
    }
  }
})
```

### 移动端适配

项目使用 `amfe-flexible` 和 `postcss-pxtorem` 实现移动端适配：

- 设计稿宽度: 750px
- 自动转换 px 为 rem
- 支持不同屏幕尺寸

### 路由配置

路由采用 Hash 模式，包含路由守卫：

- 公开路由: 首页、登录、注册等
- 用户路由: 需要用户 Token
- 管理员路由: 需要管理员 Token

### API 请求封装

使用 Axios 封装 HTTP 请求：

- 统一请求拦截器
- 统一响应拦截器
- Token 自动携带
- 错误统一处理

## 🎨 UI 组件

项目使用 Vant 4 组件库，主要使用的组件包括：

- van-button: 按钮
- van-form: 表单
- van-field: 输入框
- van-card: 卡片
- van-list: 列表
- van-tab: 标签页
- van-dialog: 对话框
- van-toast: 轻提示
- van-nav-bar: 导航栏
- van-tabbar: 标签栏
- van-swipe: 轮播
- van-image: 图片
- van-cell: 单元格

## 🔐 认证与权限

### Token 管理

- 用户 Token: 存储在 localStorage 的 `token` 字段
- 管理员 Token: 存储在 localStorage 的 `Admintoken` 字段
- Token 过期时间: 1小时
- 页面刷新超过3秒自动清除 Token

### 路由守卫

- 公开路由: 无需认证
- 用户路由: 需要用户 Token
- 管理员路由: 需要管理员 Token
- 未认证自动跳转到登录页

## 📊 状态管理

使用 Vuex 进行状态管理，主要管理：

- 用户信息
- 管理员信息
- Token 状态
- 路由状态

## 🎯 开发规范

### 组件命名

- 页面组件: 大驼峰命名 (如: Home.vue)
- 业务组件: 大驼峰命名 (如: UserCard.vue)

### 样式规范

- 使用 Less 预处理器
- 组件样式使用 scoped
- 遵循 BEM 命名规范

### 代码规范

- 使用 ESLint 进行代码检查
- 遵循 Vue 3 风格指南
- 使用 Composition API

## 📝 更新日志

### v1.0.0 (2024-03-08)
- 初始版本发布
- 完成用户端所有功能
- 完成管理员端所有功能
- 实现移动端适配
- 完成路由和权限管理

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建特性分支
3. 提交更改
4. 推送到分支
5. 开启 Pull Request

## 📄 许可证

MIT License

## 👥 作者

mwh

## 📮 联系方式

如有问题，请提交 Issue。

## 🔗 相关链接

- [Vue 3 文档](https://vuejs.org/)
- [Vant 文档](https://vant-ui.github.io/)
- [Vite 文档](https://vitejs.dev/)
- [Vue Router 文档](https://router.vuejs.org/)
- [Vuex 文档](https://vuex.vuejs.org/)
