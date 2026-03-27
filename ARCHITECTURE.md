# 项目架构文档 (Project Architecture)

## 目录

- [系统架构](#系统架构)
- [技术架构](#技术架构)
- [模块划分](#模块划分)
- [数据库设计](#数据库设计)
- [接口设计](#接口设计)
- [安全设计](#安全设计)

## 系统架构

### 整体架构

本项目采用前后端分离的架构模式，前端和后端独立部署，通过 RESTful API 进行通信。

```
┌─────────────────────────────────────────────────────────────┐
│                         用户浏览器                              │
└────────────────────┬────────────────────────────────────────┘
                     │ HTTP/HTTPS
                     ▼
┌─────────────────────────────────────────────────────────────┐
│                      前端应用 (Vue 3)                          │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐          │
│  │  用户端页面  │  │  管理员页面  │  │  公共页面    │          │
│  └─────────────┘  └─────────────┘  └─────────────┘          │
└────────────────────┬────────────────────────────────────────┘
                     │ RESTful API
                     ▼
┌─────────────────────────────────────────────────────────────┐
│                    后端服务 (Spring Boot)                      │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐          │
│  │ Controller  │  │   Service   │  │   Mapper    │          │
│  │   层        │  │    层       │  │    层       │          │
│  └─────────────┘  └─────────────┘  └─────────────┘          │
└────────────────────┬────────────────────────────────────────┘
                     │ JDBC
                     ▼
┌─────────────────────────────────────────────────────────────┐
│                    数据库 (MySQL 8.0)                        │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐          │
│  │  用户数据    │  │  业务数据    │  │  订单数据    │          │
│  └─────────────┘  └─────────────┘  └─────────────┘          │
└─────────────────────────────────────────────────────────────┘
```

### 部署架构

```
┌─────────────────────────────────────────────────────────────┐
│                        Nginx (可选)                            │
│                    静态资源服务 + 反向代理                       │
└────────────────────┬────────────────────────────────────────┘
                     │
        ┌────────────┴────────────┐
        ▼                         ▼
┌──────────────┐         ┌──────────────┐
│  前端服务器    │         │  后端服务器    │
│  (Vue 3)     │         │ (Spring Boot) │
│  Port: 5173  │         │  Port: 8081   │
└──────────────┘         └──────┬───────┘
                                │
                        ┌───────┴───────┐
                        ▼               ▼
                   ┌─────────┐    ┌─────────┐
                   │  MySQL  │    │  Redis  │
                   │  3306   │    │  6379   │
                   └─────────┘    └─────────┘
```

## 技术架构

### 前端技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Vue | 3.3.11 | 前端框架 |
| Vite | 5.0.10 | 构建工具 |
| Vue Router | 4.2.5 | 路由管理 |
| Vuex | 4.0.2 | 状态管理 |
| Vant | 4.8.2 | UI组件库 |
| Axios | 1.6.5 | HTTP客户端 |
| amfe-flexible | 2.2.1 | 移动端适配 |
| postcss-pxtorem | 6.0.0 | px转rem |
| ECharts | 5.5.0 | 数据可视化 |
| Less | 4.2.0 | CSS预处理器 |

### 后端技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Spring Boot | 2.7.8 | 应用框架 |
| Java | 17 | 开发语言 |
| MyBatis Plus | 3.4.3 | ORM框架 |
| Druid | 1.2.15 | 数据库连接池 |
| MySQL Connector | 8.0.33 | MySQL驱动 |
| JWT (jjwt) | 0.11.2 | 身份认证 |
| Commons Codec | 1.15 | 密码加密 |
| Maven | 3.6+ | 项目管理 |

## 模块划分

### 前端模块

#### 用户端模块 (User Module)

```
views/user/
├── Login.vue              # 用户登录
├── Register.vue           # 用户注册
├── Information.vue        # 个人信息
├── UserSetting.vue        # 用户设置
├── mysportlog.vue         # 运动记录
├── ActivityRegisterInfo.vue  # 活动报名信息
├── Bookdetail/            # 场地预订详情
│   ├── UserBookInfo.vue      # 用户预订总览
│   ├── BookedInfo.vue        # 已完成预订
│   ├── BookingInfo.vue       # 进行中预订
│   └── UnBookedInfo.vue      # 未开始预订
└── Equipdetail/           # 设备预订详情
    ├── UserEquipBookInfo.vue  # 用户设备预订总览
    ├── EquipBookedInfo.vue    # 已完成租赁
    └── EquipBookingInfo.vue   # 进行中租赁
```

#### 管理员端模块 (Admin Module)

```
views/admin/
├── AdminLogin.vue              # 管理员登录
├── AdminRegister.vue           # 管理员注册
├── AdminDetail.vue             # 管理员主页
├── VenueInfo.vue               # 场地管理
├── AdminVenuedetail.vue        # 场地详情
├── EquipInfo.vue               # 设备管理
├── ActivitiesInfo.vue          # 活动管理
├── BookInfo.vue                # 预订管理
├── BookingAllInfo.vue          # 所有预订
├── BookedAllInfo.vue           # 已完成预订
├── UnBookedAllInfo.vue         # 未完成预订
└── ActivityRegisterAllInfo.vue # 活动报名信息
```

#### 公共模块 (Common Module)

```
views/
├── Home.vue              # 首页
├── Book.vue              # 场地预订
├── Equip.vue             # 设备租赁
├── EquipDetail.vue       # 设备详情
├── Activities.vue        # 活动页面
├── VenueDetail.vue       # 场地详情
└── Mode.vue             # 模式选择
```

### 后端模块

#### Controller 层 (控制器层)

```
controller/
├── UserLoginController.java          # 用户登录控制器
├── UserRegisterController.java       # 用户注册控制器
├── UserSettingController.java        # 用户设置控制器
├── AdminUserLoginController.java     # 管理员登录控制器
├── AdminUserRegisterController.java  # 管理员注册控制器
├── VenuesController.java             # 场地管理控制器
├── VenueBookingController.java       # 场地预订控制器
├── EquipController.java              # 设备管理控制器
├── EquipBookingController.java       # 设备预订控制器
├── ActivityController.java            # 活动管理控制器
└── ActivityRegisterController.java   # 活动报名控制器
```

#### Service 层 (业务逻辑层)

```
service/
├── UserLoginService.java
├── UserRegisterService.java
├── UserAttributeService.java
├── AdminUserLoginService.java
├── AdminUserRegisterService.java
├── VenuesService.java
├── VenueBookInfoService.java
├── EquipService.java
├── EquipBookInfoService.java
├── ActivityService.java
└── ActivityRegisterService.java
```

#### Mapper 层 (数据访问层)

```
mapper/
├── UserMapper.java
├── UserAttributeMapper.java
├── AdminMapper.java
├── VenueMapper.java
├── VenueBookInfoMapper.java
├── EquipMapper.java
├── EquipBookInfoMapper.java
├── ActivityMapper.java
└── ActivityRegisterInfoMapper.java
```

#### Pojo 层 (实体层)

```
pojo/
├── User.java                    # 用户实体
├── UserAttribute.java           # 用户属性实体
├── AdminUser.java               # 管理员实体
├── Venue.java                   # 场地实体
├── VenueBookInfo.java           # 场地预订实体
├── Equip.java                   # 设备实体
├── EquipBookInfo.java           # 设备预订实体
├── Activity.java                # 活动实体
├── ActivityRegisterInfo.java    # 活动报名实体
├── AuthenticationResult.java    # 认证结果实体
└── Result.java                  # 统一返回结果实体
```

## 数据库设计

### 数据库表结构

#### 1. 用户表 (user)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 用户ID | PRIMARY KEY, AUTO_INCREMENT |
| username | VARCHAR(50) | 用户名 | UNIQUE, NOT NULL |
| password | VARCHAR(100) | 密码(MD5加密) | NOT NULL |
| phone | VARCHAR(20) | 手机号 | |
| email | VARCHAR(100) | 邮箱 | |
| create_time | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

#### 2. 用户属性表 (user_attribute)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 属性ID | PRIMARY KEY, AUTO_INCREMENT |
| user_id | BIGINT | 用户ID | FOREIGN KEY |
| nickname | VARCHAR(50) | 昵称 | |
| avatar | VARCHAR(255) | 头像URL | |
| gender | TINYINT | 性别(0:未知,1:男,2:女) | |
| age | INT | 年龄 | |
| height | DECIMAL(5,2) | 身高(cm) | |
| weight | DECIMAL(5,2) | 体重(kg) | |

#### 3. 管理员表 (admin_user)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 管理员ID | PRIMARY KEY, AUTO_INCREMENT |
| username | VARCHAR(50) | 用户名 | UNIQUE, NOT NULL |
| password | VARCHAR(100) | 密码(MD5加密) | NOT NULL |
| create_time | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

#### 4. 场地表 (venue)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 场地ID | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(100) | 场地名称 | NOT NULL |
| type | VARCHAR(50) | 场地类型 | NOT NULL |
| description | TEXT | 描述 | |
| capacity | INT | 容量 | |
| price | DECIMAL(10,2) | 价格/小时 | |
| image | VARCHAR(255) | 图片URL | |
| status | TINYINT | 状态(0:关闭,1:开放) | DEFAULT 1 |
| create_time | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

#### 5. 场地预订表 (venue_book_info)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 预订ID | PRIMARY KEY, AUTO_INCREMENT |
| venue_id | BIGINT | 场地ID | FOREIGN KEY |
| user_id | BIGINT | 用户ID | FOREIGN KEY |
| book_date | DATE | 预订日期 | |
| start_time | TIME | 开始时间 | |
| end_time | TIME | 结束时间 | |
| status | TINYINT | 状态(0:已取消,1:已预订,2:已完成) | DEFAULT 1 |
| create_time | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

#### 6. 设备表 (equip)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 设备ID | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(100) | 设备名称 | NOT NULL |
| type | VARCHAR(50) | 设备类型 | NOT NULL |
| description | TEXT | 描述 | |
| price | DECIMAL(10,2) | 价格/小时 | |
| stock | INT | 库存数量 | |
| image | VARCHAR(255) | 图片URL | |
| status | TINYINT | 状态(0:下架,1:上架) | DEFAULT 1 |
| create_time | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

#### 7. 设备预订表 (equip_book_info)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 预订ID | PRIMARY KEY, AUTO_INCREMENT |
| equip_id | BIGINT | 设备ID | FOREIGN KEY |
| user_id | BIGINT | 用户ID | FOREIGN KEY |
| book_date | DATE | 预订日期 | |
| start_time | TIME | 开始时间 | |
| end_time | TIME | 结束时间 | |
| quantity | INT | 数量 | |
| status | TINYINT | 状态(0:已取消,1:已预订,2:已完成) | DEFAULT 1 |
| create_time | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

#### 8. 活动表 (activity)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 活动ID | PRIMARY KEY, AUTO_INCREMENT |
| title | VARCHAR(200) | 活动标题 | NOT NULL |
| description | TEXT | 描述 | |
| start_time | DATETIME | 开始时间 | |
| end_time | DATETIME | 结束时间 | |
| venue_id | BIGINT | 场地ID | FOREIGN KEY |
| max_participants | INT | 最大参与人数 | |
| current_participants | INT | 当前参与人数 | DEFAULT 0 |
| status | TINYINT | 状态(0:已结束,1:报名中,2:进行中) | DEFAULT 1 |
| create_time | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

#### 9. 活动报名表 (activity_register_info)

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 报名ID | PRIMARY KEY, AUTO_INCREMENT |
| activity_id | BIGINT | 活动ID | FOREIGN KEY |
| user_id | BIGINT | 用户ID | FOREIGN KEY |
| register_time | DATETIME | 报名时间 | DEFAULT CURRENT_TIMESTAMP |
| status | TINYINT | 状态(0:已取消,1:已报名,2:已参加) | DEFAULT 1 |

### ER图

```
┌─────────────┐         ┌─────────────────┐         ┌─────────────┐
│    user     │         │ user_attribute  │         │ venue       │
├─────────────┤         ├─────────────────┤         ├─────────────┤
│ id (PK)     │◄────────│ id (PK)         │         │ id (PK)     │
│ username    │         │ user_id (FK)    │         │ name        │
│ password    │         │ nickname        │         │ type        │
│ phone       │         │ avatar          │         │ description │
│ email       │         │ gender          │         │ capacity    │
│ create_time │         │ age             │         │ price       │
└─────────────┘         │ height          │         │ image       │
        │               │ weight          │         │ status      │
        │               └─────────────────┘         └─────────────┘
        │                        │                           │
        │                        │                           │
        │                        │                           │
        ▼                        ▼                           ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│venue_book_info  │    │equip_book_info  │    │   activity      │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ id (PK)         │    │ id (PK)         │    │ id (PK)         │
│ venue_id (FK)   │    │ equip_id (FK)   │    │ title           │
│ user_id (FK)    │    │ user_id (FK)    │    │ description     │
│ book_date       │    │ book_date       │    │ start_time      │
│ start_time      │    │ start_time      │    │ end_time        │
│ end_time        │    │ end_time        │    │ venue_id (FK)   │
│ status          │    │ quantity        │    │ max_participants│
│ create_time     │    │ status          │    │ current_...     │
└─────────────────┘    │ create_time     │    │ status          │
                       └─────────────────┘    │ create_time     │
                               │             └─────────────────┘
                               │                      │
                               │                      │
                               │                      ▼
                               │           ┌─────────────────────┐
                               │           │activity_register_info│
                               │           ├─────────────────────┤
                               │           │ id (PK)              │
                               │           │ activity_id (FK)     │
                               │           │ user_id (FK)         │
                               │           │ register_time        │
                               │           │ status               │
                               │           └─────────────────────┘
                               │
                               ▼
                       ┌─────────────┐
                       │   equip     │
                       ├─────────────┤
                       │ id (PK)     │
                       │ name        │
                       │ type        │
                       │ description │
                       │ price       │
                       │ stock       │
                       │ image       │
                       │ status      │
                       │ create_time │
                       └─────────────┘

┌─────────────┐
│ admin_user  │
├─────────────┤
│ id (PK)     │
│ username    │
│ password    │
│ create_time │
└─────────────┘
```

## 接口设计

### 接口规范

#### 请求格式

```json
{
  "param1": "value1",
  "param2": "value2"
}
```

#### 响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

#### 状态码说明

| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

### API接口列表

#### 用户相关接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 用户注册 | POST | /user/register | 用户注册 |
| 用户登录 | POST | /user/login | 用户登录 |
| 用户信息修改 | PUT | /user/setting | 修改用户信息 |
| 获取用户信息 | GET | /user/info | 获取用户信息 |

#### 管理员相关接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 管理员注册 | POST | /admin/register | 管理员注册 |
| 管理员登录 | POST | /admin/login | 管理员登录 |

#### 场地相关接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 获取场地列表 | GET | /venues | 获取所有场地 |
| 获取场地详情 | GET | /venues/{id} | 获取场地详情 |
| 添加场地 | POST | /venues | 添加场地 |
| 更新场地 | PUT | /venues | 更新场地 |
| 删除场地 | DELETE | /venues/{id} | 删除场地 |

#### 场地预订接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 创建预订 | POST | /venue/book | 创建场地预订 |
| 取消预订 | DELETE | /venue/book/{id} | 取消场地预订 |
| 查询用户预订 | GET | /venue/book/user/{userId} | 查询用户预订记录 |
| 查询所有预订 | GET | /venue/book/all | 查询所有预订记录 |

#### 设备相关接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 获取设备列表 | GET | /equip | 获取所有设备 |
| 获取设备详情 | GET | /equip/{id} | 获取设备详情 |
| 添加设备 | POST | /equip | 添加设备 |
| 更新设备 | PUT | /equip | 更新设备 |
| 删除设备 | DELETE | /equip/{id} | 删除设备 |

#### 设备预订接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 创建设备预订 | POST | /equip/book | 创建设备预订 |
| 取消设备预订 | DELETE | /equip/book/{id} | 取消设备预订 |
| 查询用户设备预订 | GET | /equip/book/user/{userId} | 查询用户设备预订 |

#### 活动相关接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 获取活动列表 | GET | /activity | 获取所有活动 |
| 获取活动详情 | GET | /activity/{id} | 获取活动详情 |
| 发布活动 | POST | /activity | 发布活动 |
| 更新活动 | PUT | /activity | 更新活动 |
| 删除活动 | DELETE | /activity/{id} | 删除活动 |

#### 活动报名接口

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 活动报名 | POST | /activity/register | 活动报名 |
| 取消报名 | DELETE | /activity/register/{id} | 取消活动报名 |
| 查询活动报名信息 | GET | /activity/register/activity/{activityId} | 查询活动报名信息 |

## 安全设计

### 认证机制

#### JWT Token 认证

1. **用户登录/管理员登录**
   - 用户提交用户名和密码
   - 后端验证用户名和密码
   - 验证成功后生成 JWT Token
   - 返回 Token 给前端

2. **Token 存储**
   - 用户 Token 存储在 localStorage 的 `token` 字段
   - 管理员 Token 存储在 localStorage 的 `Admintoken` 字段

3. **Token 使用**
   - 前端在请求头中携带 Token
   - 后端拦截器验证 Token 有效性
   - 验证通过后放行请求

4. **Token 过期**
   - Token 有效期为 1 小时
   - 过期后需要重新登录获取新 Token
   - 页面刷新超过 3 秒自动清除 Token

### 密码安全

1. **密码加密**
   - 使用 MD5 算法加密密码
   - 数据库中存储加密后的密码

2. **密码传输**
   - 使用 HTTPS 协议传输
   - 防止密码在传输过程中被窃取

### 权限控制

1. **路由守卫**
   - 前端路由守卫验证用户权限
   - 未登录用户自动跳转到登录页

2. **接口权限**
   - 后端接口验证用户 Token
   - 不同角色访问不同接口

3. **数据权限**
   - 用户只能访问自己的数据
   - 管理员可以访问所有数据

### 安全建议

1. 使用 HTTPS 协议
2. 定期更新依赖包
3. 设置合理的 Token 过期时间
4. 实现密码强度验证
5. 添加验证码功能
6. 实现登录失败次数限制
7. 记录操作日志
8. 定期备份数据库

---

**文档版本**: v1.0.0
**最后更新**: 2024-03-08
**维护者**: mwh
