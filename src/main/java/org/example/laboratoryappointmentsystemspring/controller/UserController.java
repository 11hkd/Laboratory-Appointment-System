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

    // 查看教师课程（根据教师ID）
    @Operation(summary = "查看教师课程", description = "根据传入的教师ID，获取该教师对应的课程信息列表")
    @GetMapping("/{teacherId}/courses")
    public ResponseEntity<ResultVO> viewTeacherCourses(@PathVariable String teacherId) {
        List<Appointment> courses = userService.viewCourse(teacherId);
        if (courses!= null &&!courses.isEmpty()) {
            return ResponseEntity.ok(ResultVO.success(courses));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应教师的课程信息"));
    }

    // 添加预约信息
    @Operation(summary = "添加预约信息", description = "添加新的预约信息")
    @PostMapping("/appointments")
    public ResponseEntity<ResultVO> addAppointment(@RequestParam Integer uid,
                                                   @RequestParam Integer lid,
                                                   @RequestParam Integer cid,
                                                   @RequestParam Integer week,
                                                   @RequestParam Integer section,
                                                   @RequestParam Integer day_of_week,
                                                   @RequestParam String status,
                                                   @RequestParam String details) {
        userService.addAppointment(uid, lid, cid, week, section, day_of_week, status, details);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultVO.ok());
    }

    // 修改预约信息
    @Operation(summary = "修改预约信息", description = "根据预约ID等信息修改预约内容")
    @PutMapping("/appointments/{appointmentId}")
    public ResponseEntity<ResultVO> updateAppointment(@PathVariable Integer appointmentId,
                                                      @RequestParam Integer uid,
                                                      @RequestParam Integer lid,
                                                      @RequestParam Integer cid,
                                                      @RequestParam Integer week,
                                                      @RequestParam Integer section,
                                                      @RequestParam Integer day_of_week,
                                                      @RequestParam String status,
                                                      @RequestParam String details) {
        userService.updateAppointment(appointmentId, uid, lid, cid, week, section, day_of_week, status, details);
        return ResponseEntity.ok(ResultVO.ok());
    }

    // 删除预约信息
    @Operation(summary = "删除预约信息", description = "根据预约ID删除预约记录")
    @DeleteMapping("/appointments/{appointmentId}")
    public ResponseEntity<ResultVO> deleteAppointment(@PathVariable Integer appointmentId) {
        userService.deleteAppointment(appointmentId);
        return ResponseEntity.ok(ResultVO.ok());
    }

    // 根据实验室名查找实验室
    @Operation(summary = "根据实验室名查找实验室", description = "根据传入的实验室名查找对应的实验室信息")
    @GetMapping("/labs/{labName}")
    public ResponseEntity<ResultVO> findLabByName(@PathVariable String labName) {
        Lab lab = userService.findByLabName(labName);
        if (lab!= null) {
            return ResponseEntity.ok(ResultVO.success(lab));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应实验室信息"));
    }

    // 根据账号获取用户信息
    @Operation(summary = "根据账号获取用户信息", description = "根据传入的账号查找对应的用户信息")
    @GetMapping("/{account}")
    public ResponseEntity<ResultVO> findUserByAccount(@PathVariable String account) {
        User user = userService.findByAccount(account);
        if (user!= null) {
            return ResponseEntity.ok(ResultVO.success(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应用户信息"));
    }

    // 获取用户的课程列表（假设通过用户ID）
    @Operation(summary = "获取用户的课程列表", description = "根据传入的用户ID，获取该用户对应的课程列表信息")
    @GetMapping("/{userId}/courses")
    public ResponseEntity<ResultVO> getUserCourses(@PathVariable Integer userId) {
        List<Course> courses = userService.getUserCourses(userId);
        if (courses!= null &&!courses.isEmpty()) {
            return ResponseEntity.ok(ResultVO.success(courses));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到该用户的课程信息"));
    }

    // 获取用户的预约历史（假设通过用户ID）
    @Operation(summary = "获取用户的预约历史", description = "根据传入的用户ID，获取该用户的预约历史记录")
    @GetMapping("/{userId}/appointments")
    public ResponseEntity<ResultVO> getUserAppointments(@PathVariable Integer userId) {
        List<Appointment> appointments = userService.getUserAppointments(userId);
        if (appointments!= null &&!appointments.isEmpty()) {
            return ResponseEntity.ok(ResultVO.success(appointments));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到该用户的预约历史信息"));
    }
}