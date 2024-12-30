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
import org.example.laboratoryappointmentsystemspring.service.UserService;
import org.example.laboratoryappointmentsystemspring.vo.ResultVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/")
public class LoginController {
    private final UserService userService;

    //    get请求，获取所有用户,当get/api/admin/users时，调用userService的listUsers方法，返回所有用户
    @GetMapping("users")
    public Object getUsers(){
        return ResultVO.success(userService.getAllUser());
    }
    //接口层调用组件层的方法，返回用户
    @GetMapping("user/{account}")
//    传一个可变参数，获取用户，当get/api/admin/user/1001时，调用userService的getUserByAccount方法，返回用户
    public ResultVO getUser(@PathVariable String account){
        return ResultVO.success(userService.findByAccount(account));
    }
}
