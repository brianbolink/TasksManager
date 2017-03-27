##日程管理系统

----
###静态设计 : 

####事务类设计
1. **描述 (名称)**
2. **截止时间**
3. **分配给谁 (参与者)**
4. **谁创建的任务**
5. **是否重复 , 如何重复 ? 周期**
6. **是否提醒 ? 如何提醒 ? 短信 ? 还是邮件**
7. **重要性 (分为三个重要性等级)**
8. **是否紧急 (分为三个等级)**
9. 备注
10. 子任务
11. 评价
12. 标签
13. 关联内容

####项目类设计
1. 名称

####用户类设计
1. **用户名**
2. **密码**
3. 所属团队 (多个)

####团队
1. 团队名
2. 团队队长
2. 团队主页
3. 团队成员

----
####数据库设计 : 

####建库 : 
```
CREATE DATABASE `tasksmanager` /*!40100 DEFAULT CHARACTER SET utf8 */;
```

#####用户表 : 

1. ID (主键)
2. 用户名
3. 密码
4. 团队ID (外键)
```
CREATE TABLE `users` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `sex` tinyint(2) DEFAULT NULL,
  `birthday` YEAR DEFAULT 1970,
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `registeTime` int(16) DEFAULT NULL,
  `salt` varchar(8) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `tokenFailTime` int(16) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

#####事务表
1. ID (主键)
2. 名称
3. 描述
4. 创建者ID
6. 重要性 (0 不重要 , 1, 2, 3 重要)
7. 紧急性 (0 , 1, 2, 3)
8. 是否已完成 (状态)
```
CREATE TABLE `tasks` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `taskname` varchar(16) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `creatorID` int(16) NOT NULL,
  `importance` tinyint DEFAULT 0,
  `emergency` tinyint DEFAULT 0,
  `status` tinyint DEFAULT 0,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

#####事务\_参与者\_关联表
1. ID (主键)
2. 事务ID
3. 参与者ID
```
CREATE TABLE `executors_tasks_relationship` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `executorID` int(16) NOT NULL,
  `taskID` int(16) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

#####标签静态表 (只记录标签的名称)
1. ID (主键)
2. 名称
```
CREATE TABLE `lables` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
#####标签事务关联表
1. ID (主键)
2. 事务ID
3. 标签 ID
```
CREATE TABLE `lables_tasks_relationship` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `taskID` int(16) NOT NULL,
  `lableID` int(16) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
项目表
团队表
1. 团队 ID (主键)
2. 团队名称
2. 创建者 ID
3. 队长 ID
4. 描述

用户团队关联表
1. ID (主键)
2. 用户 ID
3. 团队 ID

评论会话表 (一个评论会话可能有)
1. ID (主键)

评论内容表
1. 评论ID
2. 评论内容
3. 发送者ID
4. 时间
5. 关联的事务 ID

---
###动态设计

####事务管理模块
1. **添加事务**
2. **删除事务**
3. **修改事务(完成事务)(事务分类)**
4. **查询事务**

####用户管理模块
1. **用户登陆**
2. **用户注册**
3. 用户找回密码

多用户管理
1. 多用户之间互相交流
2. 团队协作

用户体验支持模块 : 
1. 邮件发送服务 (事务提醒 / 用户注册 / 找回密码)
2. 短信发送服务 (事务提醒 / 用户注册 / 找回密码)
3. 

命名规范 : 
1. 驼峰命名
2. 所有 ID 两个字符全部大写

---
###最小可行性产品
第一阶段 : 只提供事务的增删改查 , 用户登陆注册
第二阶段 : 提供一个建议的列表执行顺序 (按照紧急性 / 重要性等)
第三阶段 : 提供评论功能
第四阶段 : 创建团队 , 加入团队功能
第五阶段 : 找回密码功能
...

---
具体 sql 语句模版测试 : 
1. 注册
```
INSERT INTO `users` (username, password, sex, birthday, email, phone, registeTime, salt, token, tokenFailTime) VALUES ('WangYihang', '123456', '1', '1997', 'wangyihanger@gmail.com', '15776624248', '1490591867', '', '', '1490791867');
```
2. 登陆
```
select * from users where username = 'WangYihang';
判断密码加密后的校验是否通过
```
3. 查询某个用户创建的所有 tasks
```
select * from tasks where creatorID = '1';
```
4. 根据标签查询该用户创建的所有 tasks
```
select ID from lables where name like '%keyword%';
select taskID from lables_tasks_relationship where lableID = 'ID'
select * from tasks where ID = 'taskID' and creator = 'WangYihang'
```
4. 查询所有用户 (管理员功能)
```
select * from users where username like '%keyword%'
```
