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
@RequestMapping("/api/admins/")
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
    @PostMapping(value = "users")
    public ResultVO addUser(@RequestBody User user) {
        adminService.addUser(user.getAccount(),user.getUsername(), user.getPassword(),user.getRole(),user.getPhone());
        return ResultVO.success(adminService.findAllUser());
    }

    // 删除用户账号
    @Operation(summary = "根据删除用户ID删除账号并返回所有信息", description = "根据用户ID删除用户账号")
    @DeleteMapping("deleteUser/{userID}")
    public ResultVO deleteUser(@PathVariable String userID) {
        adminService.deleteUser(userID);
        return ResultVO.success(adminService.findAllUser());
    }

    // 查找用户账号
    @Operation(summary = "查找用户账号", description = "根据用户名查找用户账号")
    @GetMapping("users/{username}")
    public ResultVO findUser(@PathVariable String username) {
        User user = adminService.findUser(username);
        if (user!= null) {
            return ResultVO.success(user);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应用户账号");
    }

    // 添加实验室信息
    @Operation(summary = "添加实验室信息并返回所有信息", description = "添加新的实验室信息")
    @PostMapping("labs")
    public ResultVO addLab(@RequestBody Lab lab) {
        adminService.addLab(lab.getName(),lab.getNumber(), lab.getInformation(),lab.getNews());
        return ResultVO.success(adminService.findAllLab());
    }

    // 删除实验室信息
    @Operation(summary = "删除实验室信息并返回所有信息", description = "根据实验室名删除实验室信息")
    @DeleteMapping("deleteLabs/{labId}")
    public ResultVO deleteLab(@PathVariable int labId) {
        adminService.deleteLab(labId);
        return ResultVO.success(adminService.findAllLab());
    }

    // 查找实验室信息
    @Operation(summary = "查找实验室信息", description = "根据实验室名查找实验室信息")
    @GetMapping("labs/{labName}")
    public ResultVO findLab(@PathVariable String labName) {
        Lab lab = adminService.findLab(labName);
        if (lab!= null) {
            return ResultVO.success(lab);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应实验室信息");
    }

    // 添加课程信息
    @Operation(summary = "添加课程信息并返回所有信息", description = "添加新的课程信息")
    @PostMapping("addCourses")
    public ResultVO addCourse(@RequestBody Course course) {
        adminService.addCourse(course.getUid(), course.getLid(), course.getCount(), course.getName(), course.getInformation(), course.getWeek(), course.getTime());
        return ResultVO.success(adminService.findAllCourse());
    }

    // 删除课程信息
    @Operation(summary = "删除课程信息并返回所有信息", description = "根据课程名删除课程信息")
    @DeleteMapping("deleteCourses/{courseId}")
    public ResultVO deleteCourse(@PathVariable String courseId) {
        adminService.deleteCourse(courseId);
        return ResultVO.success(adminService.findAllCourse());
    }

    // 查找课程信息
    @Operation(summary = "查找课程信息", description = "根据课程名查找课程信息")
    @GetMapping("courses/{courseName}")
    public ResultVO findCourse(@PathVariable String courseName) {
        Course course = adminService.findCourse(courseName);
        if (course!= null) {
            return ResultVO.success(course);
        }
        return ResultVO.error(HttpStatus.NOT_FOUND.value(), "未找到对应课程信息");
    }

    // 更新实验室公告
    @Operation(summary = "更新实验室公告", description = "根据实验室名更新实验室公告")
    @PostMapping("updateLabs/{labName}")
    public ResultVO updateNews(@PathVariable String labName, @RequestBody String news) {
        adminService.updateNews(labName, news);
        return ResultVO.success(adminService.findLab(labName));
    }

    //获取所有实验室信息
    @Operation(summary = "获取所有实验室信息", description = "获取所有实验室信息")
    @GetMapping("getAllLabs")
    public ResultVO getLabs(){
        return ResultVO.success(adminService.findAllLab());
    }

    //查找预约次数最多的用户
    @Operation(summary = "查找预约次数最多的用户,授予工作狂称号", description = "查找预约次数最多的用户")
    @GetMapping("getMostAppointmentsUser")
    public ResultVO getMostAppointmentsUser(){
        return ResultVO.success(adminService.findById(adminService.findMostAppointmentUser()));
    }

    //查找连续三周预约上课的老师
    @Operation(summary = "查找连续三周预约上课的老师", description = "查找连续三周预约上课的老师")
    @GetMapping("getThreeWeeksTeacher")
    public ResultVO getThreeWeeksTeacher(){
        return ResultVO.success(userService.findTeacherByThreeWeeks());
    }
    //返回实验室所有公告
    @Operation(summary = "返回实验室所有公告", description = "返回实验室所有公告")
    @GetMapping("getAllLabNews")
    public ResultVO getLabNews(){
        return ResultVO.success(adminService.findAllNews());
    }
}