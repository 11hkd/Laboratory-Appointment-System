package org.example.laboratoryappointmentsystemspring.service;

import org.example.laboratoryappointmentsystemspring.dox.Appointment;
import org.example.laboratoryappointmentsystemspring.mapper.repository.AppointmentRepository;
import org.example.laboratoryappointmentsystemspring.mapper.repository.CourseRepository;
import org.example.laboratoryappointmentsystemspring.mapper.repository.LabRepository;
import org.example.laboratoryappointmentsystemspring.mapper.repository.UserRepository;

public class UserService {

    private AppointmentRepository appointmentRepository;
    private CourseRepository courseRepository;
    private LabRepository labRepository;
    private UserRepository userRepository;

    //查看自己课程
    public void viewCourse(String teacherId) {
        return appointmentRepository.findbyTeacherId(teacherId);
    }

    //添加预约信息
    public void addAppointment(String appointmentId, String labName, String labLocation, String labCapacity, String labType, String appointmentDate, String appointmentTime, String appointmentPurpose, String appointmentStatus) {
        return appointmentRepository.addAppointment(appointmentId, labName,labLocation,labCapacity,labType,appointmentDate,appointmentTime,appointmentPurpose,appointmentStatus);
    }

    //修改预约信息
    public void addAppointment(String appointmentId, String labName, String labLocation, String labCapacity, String labType, String appointmentDate, String appointmentTime, String appointmentPurpose, String appointmentStatus) {
        return appointmentRepository.updateAppointment(appointmentId, labName,labLocation,labCapacity,labType,appointmentDate,appointmentTime,appointmentPurpose,appointmentStatus);
    }

    //删除预约信息
    public void deleteAppointment(String appointmentId) {
        return appointmentRepository.deleteByAppointmentId(appointmentId);
    }

    //根据实验室名查找
    public void findByLabName(String labName) {
        return labRepository.findByLabName(labName);
    }

    //根据账号Account获取用户信息
    public void findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

}
