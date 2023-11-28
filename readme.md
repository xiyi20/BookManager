基于javaGui开发的图书管理系统
=
推荐jdk版本21  
https://www.oracle.com/java/technologies/downloads/#java21  
其他版本可能会出行界面显示异常等问题  
使用gui开发的图形化用户界面,可视化操作mysql数据表  
目前所有功能均可正常使用,以后可能还会继续添加和完善  
如果您想复刻此项目,以下是所有建议:  
```
CREATE TABLE  
    `book` (  
        `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,  
        `book_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,  
        `type_id` int DEFAULT NULL,  
        `author` varchar(255) DEFAULT NULL,  
        `publish` varchar(255) DEFAULT NULL,  
        `price` double(10, 0) DEFAULT NULL,  
        `number` int DEFAULT NULL,  
        `status` int DEFAULT NULL,  
        `remark` varchar(255) DEFAULT NULL,  
        PRIMARY KEY (`id`)  
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb3
```
```
CREATE TABLE  
    `borrowdetail` (  
        `id` varchar(255) NOT NULL,  
        `user_id` int DEFAULT NULL,  
        `user_name` varchar(255) DEFAULT NULL,  
        `book_id` int DEFAULT NULL,  
        `book_name` varchar(255) DEFAULT NULL,  
        `status` int DEFAULT NULL,  
        `borrow_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,  
        `return_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,  
        PRIMARY KEY (`id`)  
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb3
```
```
CREATE TABLE  
    `user` (  
        `id` int NOT NULL,  
        `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,  
        `password` varchar(255) DEFAULT NULL,  
        `role` int DEFAULT NULL,  
        `sex` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,  
        `phone` char(11) DEFAULT NULL,  
        PRIMARY KEY (`id`)  
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb3
```
