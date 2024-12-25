package org.example.laboratoryappointmentsystemspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.laboratoryappointmentsystemspring.dox.Appointment;
import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.example.laboratoryappointmentsystemspring.service.UserService;
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

    // 查看教师课程（根据教师ID）
    @Operation(summary = "接口", description = "根据传入的教师ID，获取该教师对应的课程信息列表")
    @GetMapping("/{teacherId}/courses")
    public ResponseEntity<List<Appointment>> viewTeacherCourses(@PathVariable String teacherId) {
        List<Appointment> courses = userService.viewCourse(teacherId);
        if (courses!= null) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 添加预约信息
    @Operation(summary = "接口")
    @PostMapping("/appointments")
    public ResponseEntity<Void> addAppointment(@RequestParam Integer uid,
                                               @RequestParam Integer lid,
                                               @RequestParam Integer cid,
                                               @RequestParam Integer week,
                                               @RequestParam Integer section,
                                               @RequestParam Integer day_of_week,
                                               @RequestParam String status,
                                               @RequestParam String details) {
        userService.addAppointment(uid, lid, cid, week, section, day_of_week, status, details);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 修改预约信息
    @Operation(summary = "接口")
    @PutMapping("/appointments/{appointmentId}")
    public ResponseEntity<Void> updateAppointment(@PathVariable Integer appointmentId,
                                                  @RequestParam Integer uid,
                                                  @RequestParam Integer lid,
                                                  @RequestParam Integer cid,
                                                  @RequestParam Integer week,
                                                  @RequestParam Integer section,
                                                  @RequestParam Integer day_of_week,
                                                  @RequestParam String status,
                                                  @RequestParam String details) {
        userService.updateAppointment(appointmentId, uid, lid, cid, week, section, day_of_week, status, details);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 删除预约信息
    @Operation(summary = "接口")
    @DeleteMapping("/appointments/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer appointmentId) {
        userService.deleteAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 根据实验室名查找实验室
    @Operation(summary = "接口")
    @GetMapping("/labs/{labName}")
    public ResponseEntity<Lab> findLabByName(@PathVariable String labName) {
        Lab lab = userService.findByLabName(labName);
        if (lab!= null) {
            return new ResponseEntity<>(lab, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 根据账号获取用户信息
    @Operation(summary = "接口")
    @GetMapping("/{account}")
    public ResponseEntity<User> findUserByAccount(@PathVariable String account) {
        User user = userService.findByAccount(account);
        if (user!= null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 获取用户的课程列表（假设通过用户ID）
    @GetMapping("/{userId}/courses")
    public ResponseEntity<List<Course>> getUserCourses(@PathVariable Integer userId) {
        List<Course> courses = userService.getUserCourses(userId);
        if (courses!= null) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 获取用户的预约历史（假设通过用户ID）
    @GetMapping("/{userId}/appointments")
    public ResponseEntity<List<Appointment>> getUserAppointments(@PathVariable Integer userId) {
        List<Appointment> appointments = userService.getUserAppointments(userId);
        if (appointments!= null) {
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}