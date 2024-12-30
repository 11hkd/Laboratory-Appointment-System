package org.example.laboratoryappointmentsystemspring.component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com. auth0.jwt. algorithms. Algorithm;
import jakarta.annotation.PostConstruct;
import org.example.laboratoryappointmentsystemspring.exception.Code;
import org.example.laboratoryappointmentsystemspring.exception.xException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class JWTComponent {
    //引入algorithm中的HMAC256算法
    //    JWT是一个JSON对象，这个对象是一个字符串，这个字符串是由三部分组成的，第一部分是头部，第二部分是载荷，第三部分是签名
//    对这个JWT进行签名算法，用户带着JWT过来，签个名，然后用户带走，到其他界面用户再根据同样的算法检查这个签名对不对
//    来检测JWT这个JWT是不是被篡改过
    @Value("hkd")
    private String secretKey;
    private Algorithm algorithm;//这是一个算法对象
    @PostConstruct//这个注解是在构造函数之后执行的
    public void init(){
       //字符串形式初始化算法对象
        algorithm = Algorithm.HMAC256(secretKey);
    }

    public String encode(Map<String,Object>map){//这个map是要放到JWT中的信息
        LocalDateTime time = LocalDateTime.now().plusDays(1);//这个是过期时间
//        过期时间为一天
        return JWT.create()
                .withPayload(map)
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm);//这个是签名
//        对初始化的algorithm进行签名
    }
    public DecodedJWT decode(String token){
        try{
            return JWT.require(algorithm).build().verify(token);//这个是验证签名
        } catch (TokenExpiredException | SignatureVerificationException e){
            if(e instanceof SignatureVerificationException) {
                throw xException.builder().code(Code.FORBIDDEN).build();
            }
            throw xException.builder().code(Code.TOKEN_EXPIRED).build();
        }
    }
}