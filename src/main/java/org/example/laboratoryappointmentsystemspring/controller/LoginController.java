package org.example.laboratoryappointmentsystemspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.laboratoryappointmentsystemspring.component.JWTComponent;
import org.example.laboratoryappointmentsystemspring.dox.Login;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.example.laboratoryappointmentsystemspring.service.AdminService;
import org.example.laboratoryappointmentsystemspring.service.UserService;
import org.example.laboratoryappointmentsystemspring.vo.ResultVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.example.laboratoryappointmentsystemspring.vo.ResultVO;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/")
public class LoginController {
    private final UserService userService;
private final AdminService adminService;
    //    get请求，获取所有用户,当get/api/admin/users时，调用userService的listUsers方法，返回所有用户
    @Operation(summary = "获取所有用户", description = "获取所有用户")
    @GetMapping("getAllUsers")
    public ResultVO getUsers(){
        log.info("获取所有用户{}",adminService.findAllUser());
        return ResultVO.success(adminService.findAllUser());
    }
    //接口层调用组件层的方法，返回用户
    @Operation(summary = "根据账号密码拿用户", description = "根据账号密码拿用户")
    @GetMapping("user")
    public ResultVO getUser(@RequestBody Login login){
        log.info("login:{}",login);
        return ResultVO.success(adminService.findByAccountAndPassword(login.getAccount(), login.getPassword()));
    }
}
