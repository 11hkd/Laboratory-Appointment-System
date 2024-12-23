package org.example.laboratoryappointmentsystemspring.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.laboratoryappointmentsystemspring.component.JWTComponent;
import org.example.laboratoryappointmentsystemspring.dox.Login;
import org.example.laboratoryappointmentsystemspring.dox.User;
import org.example.laboratoryappointmentsystemspring.service.UserService;
import org.example.laboratoryappointmentsystemspring.vo.ResultVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/url/")
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    //private final ObjectMapper objectMapper;//这个是用来把对象转换成json字符串的
    private final JWTComponent jwtComponent;

    @PostMapping("login")
    public ResultVO login(@RequestBody Login login, HttpServletResponse resp) {
        User user = userService.findByAccount(login.getAccount()); //这个是根据账号查找用户

        if (user == null || !passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            return ResultVO.error(401, "用户名或密码错误");
        }

        String jwt = jwtComponent.encode(Map.of("uid", user.getId(), "role", user.getRole(),"depId"));
        resp.addHeader("token", jwt);
        resp.addHeader("role", user.getRole());

        return ResultVO.success(Map.of("user", user));//返回用户信息
    }
}