package org.example.laboratoryappointmentsystemspring.service;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.example.laboratoryappointmentsystemspring.mapper.repository.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private AppointmentRepository appointmentRepository;
    private CourseRepository courseRepository;
    private LabRepository labRepository;
    private UserRepository userRepository;

    //添加用户账号
    public void addUser(String username, String password, String role) {
        return UserRepository.addUser(username, password, role);
    }

    //删除用户账号
    public void deleteUser(String username) {
        return UserRepository.deleteByUsername(username);
    }

    //修改用户账号
    public void updateUser(String username, String password, String role) {
        return UserRepository.updateUser(username, password, role);
    }

    //查找用户账号
    public void findUser(String username) {
        return UserRepository.findByUsername(username);
    }

    //添加实验室信息
    public void addLab(String labName, String labLocation, String labCapacity) {
        return LabRepository.addLab(labName, labLocation, labCapacity);
    }

    //删除实验室信息
    public void deleteLab(String labName) {
        return LabRepository.deleteByLabName(labName);
    }

    //修改实验室信息
    public void updateLab(String labName, String labLocation, String labCapacity) {
        return LabRepository.updateLab(labName, labLocation, labCapacity);
    }

    //查找实验室信息
    public void findLab(String labName) {
        return LabRepository.findByLabName(labName);
    }

}