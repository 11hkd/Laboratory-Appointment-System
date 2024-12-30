-- users表
CREATE TABLE IF NOT EXISTS `users`(
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      username VARCHAR(255) NOT NULL,
                                      phone CHAR(11) NOT NULL,
                                      account CHAR(10) NOT NULL UNIQUE,
                                      password VARCHAR(255) NOT NULL,
                                      role ENUM('admin', 'teacher', 'superAdmin') NOT NULL,
                                      insert_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                      UNIQUE (username),
                                      UNIQUE (phone)
);

-- labs表
CREATE TABLE IF NOT EXISTS `labs` (
                                      id TINYINT AUTO_INCREMENT PRIMARY KEY,
                                      number TINYINT NOT NULL,
                                      information JSON NULL COMMENT '{"count","status","system","version","kind"}',
                                      news JSON NULL,
                                      name VARCHAR(255) NOT NULL,
                                      insert_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                      INDEX (number),
                                      INDEX (name)
);

-- courses表
CREATE TABLE IF NOT EXISTS `courses` (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         uid INT NOT NULL,
                                         lid TINYINT NOT NULL,
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

-- appointment表
CREATE TABLE IF NOT EXISTS `appointment` (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             uid INT NOT NULL,
                                             lid TINYINT NOT NULL,
                                             cid INT NOT NULL,
                                             week TINYINT UNSIGNED NOT NULL,
                                             section TINYINT UNSIGNED NOT NULL,
                                             day_of_week TINYINT UNSIGNED NOT NULL,
                                             status ENUM('pending', 'approved', 'rejected', 'canceled') NOT NULL DEFAULT 'pending',
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