package org.example.laboratoryappointmentsystemspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.example.laboratoryappointmentsystemspring.service.AdminService;
import org.example.laboratoryappointmentsystemspring.vo.ResultVO;
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
    public ResponseEntity<ResultVO> addUser(@RequestParam String username,
                                            @RequestParam String password,
                                            @RequestParam String role,
                                            @RequestParam String phone,
                                            @RequestParam String account) {
        adminService.addUser(username, password, role, phone, account);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultVO.ok());
    }

    // 删除用户账号
    @Operation(summary = "删除用户账号", description = "根据用户名删除用户账号")
    @DeleteMapping("/users/{username}")
    public ResponseEntity<ResultVO> deleteUser(@PathVariable String username) {
        adminService.deleteUser(username);
        return ResponseEntity.ok(ResultVO.ok());
    }

    // 查找用户账号
    @Operation(summary = "查找用户账号", description = "根据用户名查找用户账号")
    @GetMapping("/users/{username}")
    public ResponseEntity<ResultVO> findUser(@PathVariable String username) {
        User user = adminService.findUser(username);
        if (user!= null) {
            return ResponseEntity.ok(ResultVO.success(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应用户账号"));
    }

    // 添加实验室信息
    @Operation(summary = "添加实验室信息", description = "添加新的实验室信息")
    @PostMapping("/labs")
    public ResponseEntity<ResultVO> addLab(@RequestParam String labName,
                                           @RequestParam String number,
                                           @RequestParam String information,
                                           @RequestParam String news) {
        adminService.addLab(labName, number, information, news);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultVO.ok());
    }

    // 删除实验室信息
    @Operation(summary = "删除实验室信息", description = "根据实验室名删除实验室信息")
    @DeleteMapping("/labs/{labName}")
    public ResponseEntity<ResultVO> deleteLab(@PathVariable String labName) {
        adminService.deleteLab(labName);
        return ResponseEntity.ok(ResultVO.ok());
    }

    // 查找实验室信息
    @Operation(summary = "查找实验室信息", description = "根据实验室名查找实验室信息")
    @GetMapping("/labs/{labName}")
    public ResponseEntity<ResultVO> findLab(@PathVariable String labName) {
        Lab lab = adminService.findLab(labName);
        if (lab!= null) {
            return ResponseEntity.ok(ResultVO.success(lab));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应实验室信息"));
    }

    // 添加课程信息
    @Operation(summary = "添加课程信息", description = "添加新的课程信息")
    @PostMapping("/courses")
    public ResponseEntity<ResultVO> addCourse(@RequestParam Integer uid,
                                              @RequestParam Integer lid,
                                              @RequestParam Integer count,
                                              @RequestParam String courseName,
                                              @RequestParam String information,
                                              @RequestParam String week,
                                              @RequestParam String time) {
        adminService.addCourse(uid, lid, count, courseName, information, week, time);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultVO.ok());
    }

    // 删除课程信息
    @Operation(summary = "删除课程信息", description = "根据课程名删除课程信息")
    @DeleteMapping("/courses/{courseName}")
    public ResponseEntity<ResultVO> deleteCourse(@PathVariable String courseName) {
        adminService.deleteCourse(courseName);
        return ResponseEntity.ok(ResultVO.ok());
    }

    // 查找课程信息
    @Operation(summary = "查找课程信息", description = "根据课程名查找课程信息")
    @GetMapping("/courses/{courseName}")
    public ResponseEntity<ResultVO> findCourse(@PathVariable String courseName) {
        Course course = adminService.findCourse(courseName);
        if (course!= null) {
            return ResponseEntity.ok(ResultVO.success(course));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应课程信息"));
    }

    //更新公告信息
    @Operation(summary = "更新公告信息", description = "根据实验室名更新公告信息")
    @PostMapping("/labs/news")
    public ResponseEntity<ResultVO> updateNews(@RequestParam String labName,
                                               @RequestParam String news) {
        adminService.updateNews(labName, news);
        return ResponseEntity.ok(ResultVO.ok());
    }

}