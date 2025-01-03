# -- 测试一：基于 `users` 表主键查询性能测试
# -- 目的：测试通过主键（id）查询用户记录时的性能情况，查看是否能高效地利用主键索引直接定位到记录。
# EXPLAIN SELECT * FROM `users` WHERE id = 1;
# # 成功走到索引！！！！
#
# -- 测试二：基于 `labs` 表索引字段查询性能测试
# -- 目的：测试通过已建立索引的字段（number）查询实验室记录时，索引是否被有效利用，评估查询性能。
# EXPLAIN SELECT * FROM `labs` WHERE number = 2;
# # 成功走到索引！！！！
#
# -- 测试三：关联查询涉及外键及索引的性能测试（以 `courses` 表关联 `users` 表和 `labs` 表为例）
# -- 目的：测试在多表关联查询（通过外键关联）场景下，索引是否能帮助优化查询性能，查看关联操作的执行效率。
# EXPLAIN SELECT c.*
#         FROM `courses` c
#                  JOIN `users` u ON c.uid = u.id
#                  JOIN `labs` l ON c.lid = l.id
#         WHERE u.id = 1 AND l.number = 1;
# # 成功走到索引！！！！
#
# -- 测试四：基于 `appointment` 表复合唯一索引查询性能测试
# -- 目的：测试在 `appointment` 表中，根据具有唯一约束（UNIQUE）的多个字段（week, day_of_week, section）进行查询时，索引利用情况及性能表现。
# EXPLAIN SELECT * FROM `appointment`
#         WHERE week = 1 AND day_of_week = 2 AND section = 3;
#
# -- 测试五：全表扫描情况测试（以 `labs` 表为例）
# -- 目的：测试在未使用索引的情况下（比如查询非索引字段）进行全表扫描的性能表现，对比有索引查询的差异，了解全表扫描对性能的影响。
# EXPLAIN SELECT * FROM `labs` WHERE name LIKE '%test%';
#
#
# -- 测试六：基于 `users` 表主键查询性能测试
# -- 目的：测试通过主键（id）查询用户记录时的性能情况，查看是否能高效地利用主键索引直接定位到记录。
# EXPLAIN SELECT * FROM `users` WHERE id = 1;
#
# -- 测试七：基于 `labs` 表索引字段查询性能测试
# -- 目的：测试通过已建立索引的字段（number）查询实验室记录时，索引是否被有效利用，评估查询性能。
# EXPLAIN SELECT * FROM `labs` WHERE number = 2;
#
# -- 测试八：关联查询涉及外键及索引的性能测试（以 `courses` 表关联 `users` 表和 `labs` 表为例）
# -- 目的：测试在多表关联查询（通过外键关联）场景下，索引是否能帮助优化查询性能，查看关联操作的执行效率。
# EXPLAIN SELECT c.*
#         FROM `courses` c
#                  JOIN `users` u ON c.uid = u.id
#                  JOIN `labs` l ON c.lid = l.id
#         WHERE u.id = 1 AND l.number = 1;
#
# -- 测试九：基于 `appointment` 表复合唯一索引查询性能测试
# -- 目的：测试在 `appointment` 表中，根据具有唯一约束（UNIQUE）的多个字段（week, day_of_week, section）进行查询时，索引利用情况及性能表现。
# EXPLAIN SELECT * FROM `appointment`
#         WHERE week = 1 AND day_of_week = 2 AND section = 3;
#
# -- 测试十：全表扫描情况测试（以 `labs` 表为例）
# -- 目的：测试在未使用索引的情况下（比如查询非索引字段）进行全表扫描的性能表现，对比有索引查询的差异，了解全表扫描对性能的影响。
# EXPLAIN SELECT * FROM `labs` WHERE name LIKE '%test%';
#
# -- 测试十一：基于 `courses` 表 `name` 字段索引查询性能（假设 `name` 字段后续添加了索引情况）
# -- 目的：测试如果为 `courses` 表的 `name` 字段添加索引后，通过该字段查询课程记录时索引的利用效率及性能状况。
# -- 先假设为 `name` 字段添加索引（实际中若未添加可先执行此语句创建索引，若已存在可忽略）
# CREATE INDEX idx_name ON `courses` (name);
# EXPLAIN SELECT * FROM `courses` WHERE name = '数学';
#
# -- 测试十二：多条件组合查询 `appointment` 表性能测试（涉及非唯一索引字段与其他字段组合）
# -- 目的：测试在 `appointment` 表中，当使用非唯一索引字段（如 `cid`）与其他字段（如 `status`）组合查询时，索引的综合利用情况以及整体查询性能表现。
# EXPLAIN SELECT * FROM `appointment`
#         WHERE cid = 1 AND status = 'pending';
#
# -- 测试十三：关联查询且数据量影响测试（以 `courses` 表关联 `users` 表并模拟大数据量情况）
# -- 目的：在有外键关联的 `courses` 表和 `users` 表进行关联查询场景下，通过模拟增大数据量（使用临时表插入大量数据示例，实际可根据真实数据情况调整），观察随着数据量增多对查询性能的影响以及索引的作用效果。
# -- 先创建临时表模拟大量用户数据插入到 `users` 表（
# CREATE TEMPORARY TABLE temp_users AS
# SELECT *
# FROM `users`
# LIMIT 1000;
#
# -- 插入大量课程数据到 `courses` 表（同样示例插入1000条，可按需改变），关联 `temp_users` 表的用户数据
# INSERT INTO `courses` (uid, lid, count, name, information, week, time)
# SELECT u.id, FLOOR(RAND() * 10) + 1, FLOOR(RAND() * 10) + 1, CONCAT('课程', FLOOR(RAND() * 100)),
#        '{"hours": 30, "experimental_hours": 10, "semester": 1, "grade": "A", "class": "101"}',
#        '{"week": [1, 3, 5]}',
#        '2024-12-25 14:00'
# FROM temp_users u;
#
# -- 执行关联查询并查看执行计划分析性能
# EXPLAIN SELECT c.*
#         FROM `courses` c
#                  JOIN `users` u ON c.uid = u.id
#         WHERE u.id IN (SELECT id FROM temp_users);
#
# -- 测试九：排序操作对 `labs` 表查询性能影响测试
# -- 目的：测试在 `labs` 表查询结果进行排序操作（以 `name` 字段排序为例）时，是否会影响性能以及数据库如何处理这种排序情况，查看索引对排序的优化作用。
# EXPLAIN SELECT * FROM `labs`
#         ORDER BY name;
#
# -- 测试十：分组统计查询对 `courses` 表性能影响测试
# -- 目的：测试在 `courses` 表进行分组统计查询（以按 `name` 字段分组统计课程数量为例）时的性能表现，查看数据库如何执行分组操作以及索引是否能辅助优化。
# EXPLAIN SELECT name, COUNT(*)
#         FROM `courses`
#         GROUP BY name;