package org.example.laboratoryappointmentsystemspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.laboratoryappointmentsystemspring.dox.Course;
import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.example.laboratoryappointmentsystemspring.service.AdminService;
import org.example.laboratoryappointmentsystemspring.service.UserService;
import org.example.laboratoryappointmentsystemspring.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admins")
@Tag(name = "管理员接口")
public class AdminController {

    private  AdminService adminService;
    private  UserService userService;
    private  PathMatcher pathMatcher;

    public AdminController(AdminService adminService, UserService userService, PathMatcher pathMatcher) {
        this.adminService = adminService;
        this.userService = userService;
        this.pathMatcher = pathMatcher;
    }

    // 返回所有用户信息
    @Operation(summary = "获取所有用户", description = "获取所有用户")
    @GetMapping("getAllUsers")
    public ResultVO getUsers(){
        return ResultVO.success(adminService.findAllUser());
    }

    // 添加用户账号
    @Operation(summary = "添加用户账号并返回所有信息", description = "添加新用户账号")
    @PostMapping("/users")
    public ResultVO addUser(@RequestParam String username,
                                            @RequestParam String password,
                                            @RequestParam String role,
                                            @RequestParam String phone,
                                            @RequestParam String account) {
        adminService.addUser(username, password, role, phone, account);
        return ResultVO.success(adminService.findAllUser());
    }

    // 删除用户账号
    @Operation(summary = "删除用户账号并返回所有信息", description = "根据用户名删除用户账号")
    @DeleteMapping("/users/{username}")
    public ResultVO deleteUser(@PathVariable String username) {
        adminService.deleteUser(username);
        return ResultVO.success(adminService.findAllUser());
    }

    // 查找用户账号
    @Operation(summary = "查找用户账号", description = "根据用户名查找用户账号")
    @GetMapping("/users/{username}")
    public ResultVO findUser(@PathVariable String username) {
        User user = adminService.findUser(username);
        if (user!= null) {
            return ResultVO.success(user);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应用户账号");
    }

    // 添加实验室信息
    @Operation(summary = "添加实验室信息并返回所有信息", description = "添加新的实验室信息")
    @PostMapping("/labs")
    public ResultVO addLab(@RequestBody Lab lab) {
        adminService.addLab(lab.getName(), String.valueOf(lab.getNumber()), lab.getInformation(),lab.getNews());
        return ResultVO.success(adminService.findAllLab());
    }

    // 删除实验室信息
    @Operation(summary = "删除实验室信息并返回所有信息", description = "根据实验室名删除实验室信息")
    @DeleteMapping("/labs/{labName}")
    public ResultVO deleteLab(@PathVariable String labName) {
        adminService.deleteLab(labName);
        return ResultVO.success(adminService.findAllLab());
    }

    // 查找实验室信息
    @Operation(summary = "查找实验室信息", description = "根据实验室名查找实验室信息")
    @GetMapping("/labs/{labName}")
    public ResultVO findLab(@PathVariable String labName) {
        Lab lab = adminService.findLab(labName);
        if (lab!= null) {
            return ResultVO.success(lab);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应实验室信息");
    }

    // 添加课程信息
    @Operation(summary = "添加课程信息并返回所有信息", description = "添加新的课程信息")
    @PostMapping("/courses")
    public ResultVO addCourse(@RequestBody Course course) {
        adminService.addCourse(course.getUid(), course.getLid(), course.getCount(), String.valueOf(course.getCount()), course.getInformation(), course.getWeek(), course.getTime());
        return ResultVO.success(adminService.findAllCourse());
    }

    // 删除课程信息
    @Operation(summary = "删除课程信息并返回所有信息", description = "根据课程名删除课程信息")
    @DeleteMapping("/courses/{courseName}")
    public ResultVO deleteCourse(@PathVariable String courseName) {
        adminService.deleteCourse(courseName);
        return ResultVO.success(adminService.findAllCourse());
    }

    // 查找课程信息
    @Operation(summary = "查找课程信息", description = "根据课程名查找课程信息")
    @GetMapping("/courses/{courseName}")
    public ResultVO findCourse(@PathVariable String courseName) {
        Course course = adminService.findCourse(courseName);
        if (course!= null) {
            return ResultVO.success(course);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应课程信息");
    }

    // 更新实验室公告
    @Operation(summary = "更新实验室公告", description = "根据实验室名更新实验室公告")
    @PostMapping("/labs/{labName}")
    public ResultVO updateNews(@PathVariable String labName, @RequestBody String news) {
        adminService.updateNews(labName, news);
        return ResultVO.success(adminService.findLab(labName));
    }
}