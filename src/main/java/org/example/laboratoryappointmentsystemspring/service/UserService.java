package org.example.laboratoryappointmentsystemspring.service;

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

    // 查看自己课程（假设这里是查找教师相关课程，根据之前的持久层定义调整方法调用及返回值处理）
    public List<Appointment> viewCourse(String teacherId) {
        return (List<Appointment>) appointmentRepository.findByTeacherId(teacherId);
    }

    // 添加预约信息（根据更新后的AppointmentRepository中addAppointment方法的定义调整参数传递）
    public void addAppointment(Integer uid, Integer lid, Integer cid, Integer week, Integer section, Integer day_of_week, String status, String details) {
        appointmentRepository.addAppointment(uid, lid, cid, week, section, day_of_week, status, details);
    }

    // 修改预约信息（同样依据持久层对应方法调整参数传递等）
    public void updateAppointment(Integer appointmentId, Integer uid, Integer lid, Integer cid, Integer week, Integer section, Integer day_of_week, String status, String details) {
        appointmentRepository.updateAppointment(appointmentId, uid, lid, cid, week, section, day_of_week, status, details);
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

    // 以下可以根据业务需求添加更多与用户相关的服务方法，比如查找用户课程列表、获取用户预约历史等，以下是简单示例

    // 获取用户的课程列表（假设通过用户id关联查询课程，需要根据实际业务逻辑和数据表关联关系完善）
    public List<Course> getUserCourses(Integer userId) {
        // 这里可能需要调用CourseRepository相关方法，根据用户id查找对应的课程，具体实现根据业务逻辑补充完整
        return null;
    }

    // 获取用户的预约历史（假设通过用户id关联查询预约记录，同样需根据实际完善）
    public List<Appointment> getUserAppointments(Integer userId) {
        // 调用AppointmentRepository相关方法，按用户id查找预约记录，此处代码待完善
        return null;
    }
}