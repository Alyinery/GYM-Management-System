# 健身房管理系统 - 后端 (Gym Management System Backend)

健身房管理系统的后端服务，基于 Spring Boot 框架开发，提供 RESTful API 接口。

## 📋 项目简介

本后端项目为健身房管理系统提供完整的后端服务支持，包括用户管理、场地管理、设备管理、活动管理、预订管理等核心功能模块。

## 🛠️ 技术栈

- **框架**: Spring Boot 2.7.8
- **语言**: Java 17
- **数据库**: MySQL 8.0+
- **ORM框架**: MyBatis Plus 3.4.3
- **连接池**: Druid 1.2.15
- **身份验证**: JWT (jjwt 0.11.2)
- **加密**: Commons Codec (MD5)
- **构建工具**: Maven

## 📁 项目结构

```
gym_back/
├── src/
│   └── main/
│       ├── java/com/mwh/gym_back/
│       │   ├── GymBackApplication.java      # 主启动类
│       │   ├── controller/                   # 控制器层
│       │   │   ├── UserLoginController.java          # 用户登录
│       │   │   ├── UserRegisterController.java       # 用户注册
│       │   │   ├── UserSettingController.java        # 用户设置
│       │   │   ├── AdminUserLoginController.java     # 管理员登录
│       │   │   ├── AdminUserRegisterController.java  # 管理员注册
│       │   │   ├── VenuesController.java             # 场地管理
│       │   │   ├── VenueBookingController.java       # 场地预订
│       │   │   ├── EquipController.java              # 设备管理
│       │   │   ├── EquipBookingController.java       # 设备预订
│       │   │   ├── ActivityController.java            # 活动管理
│       │   │   └── ActivityRegisterController.java   # 活动报名
│       │   ├── service/                      # 业务逻辑层
│       │   │   ├── impl/                     # 服务实现类
│       │   │   ├── UserLoginService.java
│       │   │   ├── UserRegisterService.java
│       │   │   ├── UserAttributeService.java
│       │   │   ├── AdminUserLoginService.java
│       │   │   ├── AdminUserRegisterService.java
│       │   │   ├── VenuesService.java
│       │   │   ├── VenueBookInfoService.java
│       │   │   ├── EquipService.java
│       │   │   ├── EquipBookInfoService.java
│       │   │   ├── ActivityService.java
│       │   │   └── ActivityRegisterService.java
│       │   ├── mapper/                       # 数据访问层
│       │   │   ├── UserMapper.java
│       │   │   ├── UserAttributeMapper.java
│       │   │   ├── AdminMapper.java
│       │   │   ├── VenueMapper.java
│       │   │   ├── VenueBookInfoMapper.java
│       │   │   ├── EquipMapper.java
│       │   │   ├── EquipBookInfoMapper.java
│       │   │   ├── ActivityMapper.java
│       │   │   └── ActivityRegisterInfoMapper.java
│       │   ├── pojo/                         # 实体类
│       │   │   ├── User.java
│       │   │   ├── UserAttribute.java
│       │   │   ├── AdminUser.java
│       │   │   ├── Venue.java
│       │   │   ├── VenueBookInfo.java
│       │   │   ├── Equip.java
│       │   │   ├── EquipBookInfo.java
│       │   │   ├── Activity.java
│       │   │   ├── ActivityRegisterInfo.java
│       │   │   ├── AuthenticationResult.java
│       │   │   └── Result.java
│       │   └── utils/                        # 工具类
│       │       └── KeyGenerator.java
│       └── resources/
│           └── application.yml               # 配置文件
├── target/                                   # 编译输出目录
├── pom.xml                                   # Maven配置文件
└── .gitignore
```

## 🚀 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 数据库配置

1. 创建数据库：
```sql
CREATE DATABASE gym_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 修改配置文件 `src/main/resources/application.yml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gym_db
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: your_password  # 修改为你的密码

mybatis-plus:
  global-config:
    db-config:
      id-type: auto
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

jwt:
  secret: USPCzzD1coK0oJn25x6FWHXaisUuedL96+ioQ7O+nQQ=
  expirationMs: 3600000
```

### 安装与运行

1. 克隆项目：
```bash
git clone https://github.com/your-username/GYM.git
cd GYM/gym_back
```

2. 安装依赖：
```bash
mvn clean install
```

3. 运行项目：
```bash
mvn spring-boot:run
```

或者直接运行主类 `GymBackApplication.java`

4. 访问服务：
```
http://localhost:8081
```

## 📡 API 接口文档

### 基础信息

- **Base URL**: `http://localhost:8081`
- **认证方式**: JWT Token

### 用户相关接口

#### 用户注册
- **接口**: `POST /user/register`
- **参数**: username, password, phone, email
- **返回**: 注册结果

#### 用户登录
- **接口**: `POST /user/login`
- **参数**: username, password
- **返回**: JWT Token

#### 用户信息修改
- **接口**: `PUT /user/setting`
- **参数**: token, userInfo
- **返回**: 修改结果

### 管理员相关接口

#### 管理员注册
- **接口**: `POST /admin/register`
- **参数**: username, password
- **返回**: 注册结果

#### 管理员登录
- **接口**: `POST /admin/login`
- **参数**: username, password
- **返回**: JWT Token

### 场地相关接口

#### 获取所有场地
- **接口**: `GET /venues`
- **返回**: 场地列表

#### 获取场地详情
- **接口**: `GET /venues/{id}`
- **参数**: 场地ID
- **返回**: 场地详细信息

#### 添加场地
- **接口**: `POST /venues`
- **参数**: venueInfo
- **返回**: 添加结果

#### 更新场地
- **接口**: `PUT /venues`
- **参数**: venueInfo
- **返回**: 更新结果

#### 删除场地
- **接口**: `DELETE /venues/{id}`
- **参数**: 场地ID
- **返回**: 删除结果

### 场地预订接口

#### 创建预订
- **接口**: `POST /venue/book`
- **参数**: venueId, userId, bookTime
- **返回**: 预订结果

#### 取消预订
- **接口**: `DELETE /venue/book/{id}`
- **参数**: 预订ID
- **返回**: 取消结果

#### 查询用户预订
- **接口**: `GET /venue/book/user/{userId}`
- **参数**: 用户ID
- **返回**: 用户预订列表

### 设备相关接口

#### 获取所有设备
- **接口**: `GET /equip`
- **返回**: 设备列表

#### 获取设备详情
- **接口**: `GET /equip/{id}`
- **参数**: 设备ID
- **返回**: 设备详细信息

#### 添加设备
- **接口**: `POST /equip`
- **参数**: equipInfo
- **返回**: 添加结果

#### 更新设备
- **接口**: `PUT /equip`
- **参数**: equipInfo
- **返回**: 更新结果

#### 删除设备
- **接口**: `DELETE /equip/{id}`
- **参数**: 设备ID
- **返回**: 删除结果

### 设备预订接口

#### 创建设备预订
- **接口**: `POST /equip/book`
- **参数**: equipId, userId, bookTime
- **返回**: 预订结果

#### 取消设备预订
- **接口**: `DELETE /equip/book/{id}`
- **参数**: 预订ID
- **返回**: 取消结果

### 活动相关接口

#### 获取所有活动
- **接口**: `GET /activity`
- **返回**: 活动列表

#### 获取活动详情
- **接口**: `GET /activity/{id}`
- **参数**: 活动ID
- **返回**: 活动详细信息

#### 发布活动
- **接口**: `POST /activity`
- **参数**: activityInfo
- **返回**: 发布结果

#### 更新活动
- **接口**: `PUT /activity`
- **参数**: activityInfo
- **返回**: 更新结果

#### 删除活动
- **接口**: `DELETE /activity/{id}`
- **参数**: 活动ID
- **返回**: 删除结果

### 活动报名接口

#### 活动报名
- **接口**: `POST /activity/register`
- **参数**: activityId, userId
- **返回**: 报名结果

#### 取消报名
- **接口**: `DELETE /activity/register/{id}`
- **参数**: 报名ID
- **返回**: 取消结果

#### 查询活动报名信息
- **接口**: `GET /activity/register/activity/{activityId}`
- **参数**: 活动ID
- **返回**: 报名用户列表

## 🔐 认证说明

所有需要认证的接口都需要在请求头中携带 JWT Token：

```
Authorization: Bearer {token}
```

Token 有效期为 1 小时，过期后需要重新登录获取新 Token。

## 📊 数据库表结构

### 用户表 (user)
- id: 用户ID
- username: 用户名
- password: 密码（MD5加密）
- phone: 手机号
- email: 邮箱
- create_time: 创建时间

### 管理员表 (admin_user)
- id: 管理员ID
- username: 用户名
- password: 密码（MD5加密）
- create_time: 创建时间

### 场地表 (venue)
- id: 场地ID
- name: 场地名称
- type: 场地类型
- description: 描述
- capacity: 容量
- price: 价格
- image: 图片URL

### 场地预订表 (venue_book_info)
- id: 预订ID
- venue_id: 场地ID
- user_id: 用户ID
- book_time: 预订时间
- status: 预订状态
- create_time: 创建时间

### 设备表 (equip)
- id: 设备ID
- name: 设备名称
- type: 设备类型
- description: 描述
- price: 价格
- stock: 库存
- image: 图片URL

### 设备预订表 (equip_book_info)
- id: 预订ID
- equip_id: 设备ID
- user_id: 用户ID
- book_time: 预订时间
- status: 预订状态
- create_time: 创建时间

### 活动表 (activity)
- id: 活动ID
- title: 活动标题
- description: 描述
- start_time: 开始时间
- end_time: 结束时间
- max_participants: 最大参与人数
- current_participants: 当前参与人数
- create_time: 创建时间

### 活动报名表 (activity_register_info)
- id: 报名ID
- activity_id: 活动ID
- user_id: 用户ID
- register_time: 报名时间
- status: 报名状态

## 🔧 开发说明

### 代码规范

- 遵循阿里巴巴 Java 开发规范
- 使用 Lombok 简化代码
- 统一使用 Result 类封装返回结果

### 分层架构

- **Controller层**: 处理HTTP请求，参数校验
- **Service层**: 业务逻辑处理
- **Mapper层**: 数据库操作
- **Pojo层**: 实体类定义

### 异常处理

使用全局异常处理器统一处理异常，返回友好的错误信息。

## 📝 更新日志

### v1.0.0 (2024-03-08)
- 初始版本发布
- 完成用户和管理员功能
- 完成场地和设备管理
- 完成活动管理功能
- 完成预订功能

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

## 📄 许可证

MIT License

## 👥 作者

mwh

## 📮 联系方式

如有问题，请提交 Issue。
