package org.example.laboratoryappointmentsystemspring.service;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.laboratoryappointmentsystemspring.Repository.AppointmentRepository;
import org.example.laboratoryappointmentsystemspring.Repository.CourseRepository;
import org.example.laboratoryappointmentsystemspring.Repository.LabRepository;
import org.example.laboratoryappointmentsystemspring.Repository.UserRepository;
import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AdminService {

    private final AppointmentRepository appointmentRepository;
    private final CourseRepository courseRepository;
    private final LabRepository labRepository;
    private final UserRepository userRepository;

    // 添加用户账号（根据更新后的UserRepository.addUser方法调整参数传递，返回值设为void符合插入操作逻辑）
    // 添加参数校验逻辑，确保传入的用户名、账号、密码等符合业务要求，不为空
    public void addUser(@NotBlank(message = "用户名不能为空") String username,
                        @NotBlank(message = "账号不能为空") String account,
                        @NotBlank(message = "密码不能为空") String password,
                        @NotBlank(message = "角色") String role,
                        @NotBlank(message = "电话不能为空") String phone) {
        userRepository.addUser(username, account, password, role, phone);
    }

    // 删除用户账号（调用UserRepository.deleteByUsername方法，参数传递正确，返回值为void符合删除操作）
    public void deleteUser(@NotBlank(message = "用户名不能为空") String username) {
        userRepository.deleteByUsername(username);
    }

    // 返回所有用户信息
    public Iterable<User> findAllUser() {
        return userRepository.findAllUser();
    }

    // 查找用户账号（调用UserRepository.findByUsername方法，返回查找到的User对象，方便后续业务逻辑使用）
    public User findUser(@NotBlank(message = "用户名不能为空") String username) {
        return userRepository.findByUsername(username);
    }

    // 添加实验室信息（依据LabRepository.addLab方法的参数要求传递参数，返回值为void，执行插入操作）
    // 添加参数校验，比如实验室名称不能为空等
    public void addLab(@NotBlank(message = "实验室名称不能为空") String name,
                       int number,
                       @NotBlank(message = "实验室信息不能为空") String information,
                       @NotBlank(message = "实验室新闻不能为空") String news) {
        labRepository.addLab(name, number, information, news);
    }

    // 删除实验室信息（调用LabRepository.deleteByLabName方法，正确传递参数，返回值为void执行删除）
    public void deleteLab(@NotBlank(message = "实验室名称不能为空") String labName) {
        labRepository.deleteByLabName(labName);
    }

    // 查找实验室信息（调用LabRepository.findByLabName方法，返回Lab对象便于后续业务使用）
    public Lab findLab(@NotBlank(message = "实验室名称不能为空") String labName) {
        return labRepository.findByLabName(labName);
    }

    // 获取所有实验室信息
    public Iterable<Lab> findAllLab() {
        return labRepository.findAllLabs();
    }

    // 以下可以根据业务需求添加更多管理员相关的操作方法，比如管理课程信息、管理预约信息等，以下是简单示例

    // 添加课程信息（假设根据业务逻辑调用CourseRepository.addCourse方法，需按其参数要求传递）
    // 添加参数校验，比如课程名不能为空等
    public void addCourse(Integer uid,
                          Integer lid,
                          Integer count,
                          @NotBlank(message = "课程名不能为空") String courseName,
                          @NotBlank(message = "课程信息不能为空") String information,
                          @NotBlank(message = "周次信息不能为空") String week,
                          @NotBlank(message = "时间信息不能为空") String time) {
        courseRepository.addCourse(uid, lid, count, courseName, information, week, time);
    }

    // 删除课程信息（调用CourseRepository.deleteByCourseName方法，传递正确参数，返回值为void执行删除）
    public void deleteCourse(@NotBlank(message = "课程名不能为空") String courseName) {
        courseRepository.deleteByCourseName(courseName);
    }

    // 查找课程信息（调用CourseRepository.findByCourseName方法，返回查找到的Course对象供后续业务使用）
    public Course findCourse(@NotBlank(message = "课程名不能为空") String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    // 获取所有课程信息
    public Iterable<Course> findAllCourse() {
        return courseRepository.findAllCourses();
    }

    // 更新公告信息
    public void updateNews(@NotBlank(message = "实验室名称不能为空") String labName,
                           @NotBlank(message = "新闻内容不能为空") String news) {
        labRepository.updateNews(labName, news);
    }

    // 根据用户账号查找（修改返回值为User类型，更符合查找用户的逻辑，添加参数校验和异常处理）
    public String findByAccountAndPassword(@NotBlank(message = "账号不能为空") String account,
                                         @NotBlank(message = "密码不能为空") String password) {
        try {
            return userRepository.findByAccountAndPassword(account, password);
        } catch (Exception e) {
            log.info("根据账号和密码查找用户出现异常: " + e.getMessage());
            return null;
        }
    }

    // 查找预约表中预约次数最多的用户
    public int findMostAppointmentUser() {
        return appointmentRepository.findMostAppointmentUser();
    }
}