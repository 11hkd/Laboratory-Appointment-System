package org.example.laboratoryappointmentsystemspring.Repository;

import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    // 根据课程名查找
    @Query("SELECT * from courses where name=:courseName")
    public Course findByCourseName(String courseName);

    // 添加课程信息
    // 调整插入语句的字段与数据表中的字段对应，参数类型也需匹配数据表字段类型
    @Modifying
    @Transactional
    @Query("insert into courses(uid, lid, count, name, information, week, time) values(:uid, :lid, :count, :courseName, :information, :week, :time)")
    public void addCourse(Integer uid, Integer lid, Integer count, String courseName, String information, String week, String time);

    // 删除课程信息
    // 参数类型与数据表中主键类型一致（这里是根据课程名删除，实际更合理的可能是根据主键id删除，可根据业务需求调整）
    @Modifying
    @Transactional
    @Query("delete from courses where name=:courseName")
    public void deleteByCourseName(String courseName);

    // 修改课程信息
    // 使更新语句的字段与数据表结构对应，确保能正确更新相应字段的值
    @Modifying
    @Transactional
    @Query("update courses set uid=:uid, lid=:lid, count=:count, information=:information, week=:week, time=:time where name=:courseName")
    public void updateCourse(String courseName, Integer uid, Integer lid, Integer count, String information, String week, String time);
}