package org.example.laboratoryappointmentsystemspring.Repository;

import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course,String> {
    //根据课程名查找
    @Query("SELECT * from course where courseName=:courseName")
    public Course findByCourseName(String courseName);

    //添加课程信息
    @Query("insert into course(courseName,teacherName,teacherId,teacherPhone,teacherEmail,teacherOffice,teacherOfficeHour) values(:courseName,:teacherName,:teacherId,:teacherPhone,:teacherEmail,:teacherOffice,:teacherOfficeHour)")
    public Course addCourse(String courseName,String teacherName,String teacherId,String teacherPhone,String teacherEmail,String teacherOffice,String teacherOfficeHour);

    //删除课程信息
    @Query("delete from course where courseName=:courseName")
    public void deleteByCourseName(String courseName);

    //修改课程信息
    @Query("update course set teacherName=:teacherName,teacherId=:teacherId,teacherPhone=:teacherPhone,teacherEmail=:teacherEmail,teacherOffice=:teacherOffice,teacherOfficeHour=:teacherOfficeHour where courseName=:courseName")
    public Course updateCourse(String courseName,String teacherName,String teacherId,String teacherPhone,String teacherEmail,String teacherOffice,String teacherOfficeHour);




}
