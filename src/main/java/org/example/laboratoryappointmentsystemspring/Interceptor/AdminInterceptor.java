package org.example.laboratoryappointmentsystemspring.Interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.laboratoryappointmentsystemspring.exception.Code;
import org.example.laboratoryappointmentsystemspring.exception.xException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(User.ADMIN.equals(request.getAttribute("role"))){
//            return true;
//        }
//        throw xException.builder().code(Code.FORBIDDEN).build();
//    }
        return true;
}
}
