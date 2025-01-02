package org.example.laboratoryappointmentsystemspring.service;

import io.swagger.v3.core.util.Json;
import jakarta.validation.constraints.NotBlank;
import org.example.laboratoryappointmentsystemspring.Repository.AppointmentRepository;
import org.example.laboratoryappointmentsystemspring.Repository.CourseRepository;
import org.example.laboratoryappointmentsystemspring.Repository.LabRepository;
import org.example.laboratoryappointmentsystemspring.Repository.UserRepository;
import org.example.laboratoryappointmentsystemspring.dox.Appointment;
import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final AppointmentRepository appointmentRepository;
    private final CourseRepository courseRepository;
    private final LabRepository labRepository;
    private final UserRepository userRepository;

    public UserService(AppointmentRepository appointmentRepository, CourseRepository courseRepository, LabRepository labRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.courseRepository = courseRepository;
        this.labRepository = labRepository;
        this.userRepository = userRepository;
    }

    // 添加预约信息
    public void addAppointment(Integer uid, Integer lid, Integer cid, Integer week, Integer section, Integer day_of_week,
                               @NotBlank(message = "预约状态不能为空") String status,
                               @NotBlank(message = "预约详情不能为空") String details) {
        // 调用预约仓库的冲突检测方法进行冲突检测
        if (appointmentRepository.isConflict(lid, cid, week, section, day_of_week)) {
            throw new RuntimeException("预约冲突，无法添加该预约信息，请重新选择时间或其他相关信息");
        }
        appointmentRepository.addAppointment(uid, lid, cid, week, section, day_of_week, status, details);
    }




    // 删除预约信息（按照持久层定义的参数类型来传递参数）
    public void deleteAppointment(Integer appointmentId) {
        appointmentRepository.deleteByAppointmentId(appointmentId);
    }

    // 根据实验室名查找（处理返回值等，持久层返回的是Lab对象，按业务逻辑决定是否需要进一步处理返回结果）
    public Lab findByLabName(String labName) {
        return labRepository.findByLabName(labName);
    }

    // 根据账号Account获取用户信息（处理返回值等，持久层返回的是User对象，按业务逻辑使用返回的用户对象）
    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    // 获取用户的课程列表（假设通过用户id关联查询课程，需要根据实际业务逻辑和数据表关联关系完善）
    public List<Course> getUserCourses(Integer userId) {
        return (List<Course>) courseRepository.findByUid(userId);
    }

    // 获取用户的预约历史（假设通过用户id关联查询预约记录，同样需根据实际完善）
    public List<Appointment> getUserAppointments(Integer userId) {
    return (List<Appointment>) appointmentRepository.findByUid(userId);
    }

    //查找连续三周预约上课的老师
    public List<User> findTeacherByThreeWeeks() {
        return userRepository.findTeacherByThreeWeeks();
    }
    //根据周数天数节数查询可用实验室
    public List<String> findAvailableLab(Integer week,Integer day_of_week,Integer section) {
        return appointmentRepository.findAvailableLab(week,day_of_week,section);
    }

}