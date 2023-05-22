package com.ai.th.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.ai.th.common.Constant;
import com.ai.th.exception.ServiceException;
import com.ai.th.pojo.Student;

import com.ai.th.service.StudentService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentJwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StudentService studentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constant.CODE_401, "请重新登陆");
        }
        //获取token中的userid
        String userId;

        try {
            userId = JWT.decode(token).getAudience().get(0);

        } catch (JWTDecodeException e) {
            throw new ServiceException(Constant.CODE_401, "用户不存在请重新登录");
        }
        //根据token中的id查询数据库
        Student student =  studentService.getById(userId);

        if (student == null) {
            throw new ServiceException(Constant.CODE_401, "用户不存在请重新登录");
        }
        //用户密码加签验证 token
        if (student.getPassword() != null) {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(student.getPassword())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new ServiceException(Constant.CODE_401, "请重新登录");
            }
        }
        return true;
    }
}
