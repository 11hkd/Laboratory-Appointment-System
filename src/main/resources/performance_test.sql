# -- 查询1：获取特定用户的所有课程
# EXPLAIN SELECT * FROM courses
#         WHERE uid = [特定用户ID];
#
# -- 查询2：获取特定实验室的所有预约
# EXPLAIN SELECT * FROM appointment
#         WHERE lid = [特定实验室ID];
#
# -- 查询3：获取特定课程在特定周的预约
# EXPLAIN SELECT * FROM appointment
#         WHERE cid = [特定课程ID] AND week = [特定周数];
#
# -- 查询4：获取特定用户的课程及其相关实验室信息
# EXPLAIN SELECT * FROM courses
#                           JOIN labs ON courses.lid = labs.id
#         WHERE courses.uid = [特定用户ID];
#
# -- 查询5：获取特定实验室的预约及其相关用户和课程信息
# EXPLAIN SELECT * FROM appointment
#                           JOIN users ON appointment.uid = users.id
#                           JOIN courses ON appointment.cid = courses.id
#         WHERE appointment.lid = [特定实验室ID];
#
# -- 查询6：获取特定周内特定状态的预约及其相关课程和实验室信息
# EXPLAIN SELECT * FROM appointment
#                           JOIN courses ON appointment.cid = courses.id
#                           JOIN labs ON courses.lid = labs.id
#         WHERE appointment.week = [特定周数] AND appointment.status = [特定状态];
#
# -- 查询7：插入一条新的预约记录
# EXPLAIN INSERT INTO appointment (uid, lid, cid, week, section, day_of_week, status, details)
#         VALUES ([用户ID], [实验室ID], [课程ID], [周数], [节数], [周几], [特定状态], [详细信息JSON]);
#
# -- 查询8：更新特定预约的状态
# EXPLAIN UPDATE appointment
#         SET status = [新状态]
#     WHERE id = [预约ID];