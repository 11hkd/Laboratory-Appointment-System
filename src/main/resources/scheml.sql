CREATE TABLE `users` (
     id INT PRIMARY KEY,
     username VARCHAR(255) NOT NULL,
     phone CHAR(11) NOT NULL,
     account CHAR(10) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     role ENUM('admin', 'teacher', 'superAdmin') NOT NULL,
     insert_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     UNIQUE (username),
     UNIQUE (phone),
     UNIQUE (account)
);

CREATE TABLE `labs` (
    id TINYINT AUTO_INCREMENT PRIMARY KEY,
    number TINYINT NOT NULL,
    information JSON NULL COMMENT '{"count","status","system","version","kind"}',   # 添加分类属性 拓展
    news JSON NULL,
    name VARCHAR(255) NOT NULL,
    insert_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX (number),
    INDEX (name)
);

CREATE TABLE `courses` (
   id INT AUTO_INCREMENT PRIMARY KEY,
   uid INT NOT NULL,
   lid INT NOT NULL,
   count INT NOT NULL,
   name VARCHAR(255) NOT NULL,
   information JSON NULL COMMENT '{"hours","Experimental_hours","semester","grade","class"}',
   week JSON,
   time VARCHAR(255),
   insert_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (uid) REFERENCES `users`(id),
   FOREIGN KEY (lid) REFERENCES `labs`(id)
);

CREATE TABLE `appointment` (
   id INT AUTO_INCREMENT PRIMARY KEY,
   uid INT NOT NULL,
   lid INT NOT NULL,
   cid INT NOT NULL,
   week TINYINT UNSIGNED NOT NULL, # 第几周
   section TINYINT UNSIGNED NOT NULL , # 第几节
   day_of_week TINYINT UNSIGNED NOT NULL,  # 周几
   status ENUM('pending', 'approved', 'rejected', 'canceled') NOT NULL DEFAULT 'pending',  # 待处理 通过 拒绝 取消
   details JSON NULL COMMENT '{
        "reason": "预约原因",
        "duration": {"start": "第几周", "end": "第几周"},
        "participants": "预约人"
    }',
   insert_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (`uid`) REFERENCES `users`(`id`),
   FOREIGN KEY (`lid`) REFERENCES `labs`(`id`),
   FOREIGN KEY (`cid`) REFERENCES `courses`(`id`),
   UNIQUE (week,day_of_week,section),
   index (cid)
);

