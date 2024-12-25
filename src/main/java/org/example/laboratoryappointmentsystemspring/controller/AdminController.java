package org.example.laboratoryappointmentsystemspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.example.laboratoryappointmentsystemspring.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@Tag(name = "管理员接口")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 添加用户账号
    @Operation(summary = "添加用户账号", description = "添加新用户账号")
    @PostMapping("/users")
    public ResponseEntity<Void> addUser(@RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam String role,
                                        @RequestParam String phone,
                                        @RequestParam String account) {
        adminService.addUser(username, password, role, phone, account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 删除用户账号
    @Operation(summary = "删除用户账号", description = "根据用户名删除用户账号")
    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@RequestParam String username) {
        adminService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 修改用户账号
    @Operation(summary = "修改用户账号", description = "根据用户名修改用户账号信息")
    @PutMapping("/users")
    public ResponseEntity<Void> updateUser(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam String role) {
        adminService.updateUser(username, password, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 查找用户账号
    @Operation(summary = "查找用户账号", description = "根据用户名查找用户账号")
    @GetMapping("/users")
    public ResponseEntity<User> findUser(@RequestParam String username) {
        User user = adminService.findUser(username);
        if (user!= null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 添加实验室信息
    @Operation(summary = "添加实验室信息", description = "添加新的实验室信息")
    @PostMapping("/labs")
    public ResponseEntity<Void> addLab(@RequestParam String labName,
                                       @RequestParam String number,
                                       @RequestParam String information,
                                       @RequestParam String news) {
        adminService.addLab(labName, number, information, news);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 删除实验室信息
    @Operation(summary = "删除实验室信息", description = "根据实验室名删除实验室信息")
    @DeleteMapping("/labs")
    public ResponseEntity<Void> deleteLab(@RequestParam String labName) {
        adminService.deleteLab(labName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 修改实验室信息
    @Operation(summary = "修改实验室信息", description = "根据实验室名修改实验室信息")
    @PutMapping("/labs")
    public ResponseEntity<Void> updateLab(@RequestParam String labName,
                                          @RequestParam String number,
                                          @RequestParam String information,
                                          @RequestParam String news) {
        adminService.updateLab(labName, number, information, news);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 查找实验室信息
    @Operation(summary = "查找实验室信息", description = "根据实验室名查找实验室信息")
    @GetMapping("/labs")
    public ResponseEntity<Lab> findLab(@RequestParam String labName) {
        Lab lab = adminService.findLab(labName);
        if (lab!= null) {
            return new ResponseEntity<>(lab, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 添加课程信息
    @Operation(summary = "添加课程信息", description = "添加新的课程信息")
    @PostMapping("/courses")
    public ResponseEntity<Void> addCourse(@RequestParam Integer uid,
                                          @RequestParam Integer lid,
                                          @RequestParam Integer count,
                                          @RequestParam String courseName,
                                          @RequestParam String information,
                                          @RequestParam String week,
                                          @RequestParam String time) {
        adminService.addCourse(uid, lid, count, courseName, information, week, time);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 删除课程信息
    @Operation(summary = "删除课程信息", description = "根据课程名删除课程信息")
    @DeleteMapping("/courses")
    public ResponseEntity<Void> deleteCourse(@RequestParam String courseName) {
        adminService.deleteCourse(courseName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 修改课程信息
    @Operation(summary = "修改课程信息", description = "根据课程名修改课程信息")
    @PutMapping("/courses")
    public ResponseEntity<Void> updateCourse(@RequestParam String courseName,
                                             @RequestParam Integer uid,
                                             @RequestParam Integer lid,
                                             @RequestParam Integer count,
                                             @RequestParam String information,
                                             @RequestParam String week,
                                             @RequestParam String time) {
        adminService.updateCourse(courseName, uid, lid, count, information, week, time);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 查找课程信息
    @Operation(summary = "查找课程信息", description = "根据课程名查找课程信息")
    @GetMapping("/courses")
    public ResponseEntity<Course> findCourse(@RequestParam String courseName) {
        Course course = adminService.findCourse(courseName);
        if (course!= null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}