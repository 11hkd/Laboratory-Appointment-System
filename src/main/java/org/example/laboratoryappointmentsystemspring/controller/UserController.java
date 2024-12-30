package org.example.laboratoryappointmentsystemspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.laboratoryappointmentsystemspring.dox.Appointment;
import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.example.laboratoryappointmentsystemspring.service.UserService;
import org.example.laboratoryappointmentsystemspring.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "用户接口")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 添加预约信息
    @Operation(summary = "添加预约信息并返回所有用户信息", description = "添加新的预约信息")
    @PostMapping("/appointments")
    public ResultVO addAppointment(@RequestBody Appointment appointment) {
        userService.addAppointment(appointment.getUid(), appointment.getLid(), appointment.getCid(), appointment.getWeek(), appointment.getSection(), appointment.getDay_of_week(), appointment.getStatus(), appointment.getDetails());
        return ResultVO.success(userService.getUserAppointments(appointment.getUid()));
    }

    // 删除预约信息
    @Operation(summary = "删除预约信息并返回所有信息", description = "根据预约ID删除预约记录")
    @DeleteMapping("/appointments/{appointmentId}")
    public ResultVO deleteAppointment(@PathVariable Integer appointmentId) {
        userService.deleteAppointment(appointmentId);
        return ResultVO.success(userService.getUserAppointments(appointmentId));
    }

    // 根据实验室名查找实验室
    @Operation(summary = "根据实验室名查找实验室", description = "根据传入的实验室名查找对应的实验室信息")
    @GetMapping("/labs/{labName}")
    public ResultVO findLabByName(@PathVariable String labName) {
        Lab lab = userService.findByLabName(labName);
        if (lab!= null) {
            return ResultVO.success(lab);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应实验室信息");
    }

    // 根据账号获取用户信息
    @Operation(summary = "根据账号获取用户信息", description = "根据传入的账号查找对应的用户信息")
    @GetMapping("/{account}")
    public ResultVO findUserByAccount(@PathVariable String account) {
        User user = userService.findByAccount(account);
        if (user!= null) {
            return ResultVO.success(user);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应用户信息");
    }

    // 获取用户的课程列表
    @Operation(summary = "获取用户的课程列表", description = "根据传入的用户ID，获取该用户对应的课程列表信息")
    @GetMapping("/{userId}/courses")
    public ResultVO getUserCourses(@PathVariable Integer userId) {
        List<Course> courses = userService.getUserCourses(userId);
        if (courses!= null &&!courses.isEmpty()) {
            return ResultVO.success(courses);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到该用户的课程信息");
    }

    // 获取用户的预约历史
    @Operation(summary = "获取用户的预约历史", description = "根据传入的用户ID，获取该用户的预约历史记录")
    @GetMapping("/{userId}/appointments")
    public ResultVO getUserAppointments(@PathVariable Integer userId) {
        List<Appointment> appointments = userService.getUserAppointments(userId);
        if (appointments!= null &&!appointments.isEmpty()) {
            return ResultVO.success(appointments);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到该用户的预约历史信息");
    }
}