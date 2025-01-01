package org.example.laboratoryappointmentsystemspring.Interceptor;


import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.laboratoryappointmentsystemspring.component.JWTComponent;
import org.example.laboratoryappointmentsystemspring.exception.Code;
import org.example.laboratoryappointmentsystemspring.exception.xException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.example.laboratoryappointmentsystemspring.dox.User;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    private final JWTComponent jwtComponent;

    public AdminInterceptor(JWTComponent jwtComponent) {
        this.jwtComponent = jwtComponent;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        DecodedJWT decodeJWT = jwtComponent.decode(token);

        String uid=decodeJWT.getClaim("uid").asString();
        String role = decodeJWT.getClaim("role").asString();
        if(!User.ADMIN_ROLE.equals(role)) {
            throw xException.builder()
                    .code(Code.FORBIDDEN)
                    .build();
        }
        request.setAttribute("uid",uid);
        request.setAttribute("role",role);
        return true;
    }

}
