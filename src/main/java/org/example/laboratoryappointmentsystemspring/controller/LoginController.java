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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
@Tag(name = "登录接口")
public class LoginController {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JWTComponent jwtComponent;
@Operation(summary = "登录接口")
    @PostMapping("login")
    public ResultVO login(@RequestBody Login login, HttpServletResponse resp) {
        User user = userService.findByAccount(login.getAccount());

        if (user == null || (login.getPassword()).equals(user.getPassword())) {
            return ResultVO.error(401, "用户名或密码错误");
        }

        // 使用HashMap来创建Map实例并添加键值对，替代Map.of方法
        Map<String, Object> map = new HashMap<>();
        map.put("uid", user.getId());
        map.put("role", user.getRole());
        // 这里原代码中"depId"没有对应的值，如果需要可以补充正确逻辑获取对应值后添加，这里先按原样添加键
        map.put("depId", null);

        String jwt = jwtComponent.encode(map);
        resp.addHeader("token", jwt);
        resp.addHeader("role", user.getRole());

        // 同样使用HashMap来创建返回的Map实例
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("user", user);
        return ResultVO.success(resultMap);
    }
}