package org.example.laboratoryappointmentsystemspring.service;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.example.laboratoryappointmentsystemspring.Repository.AppointmentRepository;
import org.example.laboratoryappointmentsystemspring.Repository.CourseRepository;
import org.example.laboratoryappointmentsystemspring.Repository.LabRepository;
import org.example.laboratoryappointmentsystemspring.Repository.UserRepository;
import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AppointmentRepository appointmentRepository;
    private final CourseRepository courseRepository;
    private final LabRepository labRepository;
    private final UserRepository userRepository;

    // 添加用户账号（根据更新后的UserRepository.addUser方法调整参数传递，返回值设为void符合插入操作逻辑）
    public void addUser(String username, String password, String role, String phone, String account) {
        userRepository.addUser(username, phone, account, password, role);
    }

    // 删除用户账号（调用UserRepository.deleteByUsername方法，参数传递正确，返回值为void符合删除操作）
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }



    // 查找用户账号（调用UserRepository.findByUsername方法，返回查找到的User对象，方便后续业务逻辑使用）
    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    // 添加实验室信息（依据LabRepository.addLab方法的参数要求传递参数，返回值为void，执行插入操作）
    public void addLab(String labName, String number, String information, String news) {
        labRepository.addLab(Integer.parseInt(number), information, news, labName);
    }

    // 删除实验室信息（调用LabRepository.deleteByLabName方法，正确传递参数，返回值为void执行删除）
    public void deleteLab(String labName) {
        labRepository.deleteByLabName(labName);
    }



    // 查找实验室信息（调用LabRepository.findByLabName方法，返回Lab对象便于后续业务使用）
    public Lab findLab(String labName) {
        return labRepository.findByLabName(labName);
    }

    // 以下可以根据业务需求添加更多管理员相关的操作方法，比如管理课程信息、管理预约信息等，以下是简单示例

    // 添加课程信息（假设根据业务逻辑调用CourseRepository.addCourse方法，需按其参数要求传递）
    public void addCourse(Integer uid, Integer lid, Integer count, String courseName, String information, String week, String time) {
        courseRepository.addCourse(uid, lid, count, courseName, information, week, time);
    }

    // 删除课程信息（调用CourseRepository.deleteByCourseName方法，传递正确参数，返回值为void执行删除）
    public void deleteCourse(String courseName) {
        courseRepository.deleteByCourseName(courseName);
    }

    // 查找课程信息（调用CourseRepository.findByCourseName方法，返回查找到的Course对象供后续业务使用）
    public Course findCourse(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }
}