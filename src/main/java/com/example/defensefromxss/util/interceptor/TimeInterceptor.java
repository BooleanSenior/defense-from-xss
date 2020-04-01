package com.example.defensefromxss.util.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

// 拦截器
@Component
public class TimeInterceptor implements HandlerInterceptor {

    // 进入controller之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = request.getParameter("username");
        System.out.println("username"+username);
        System.out.println("preHandle");
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName()); // 类名
        System.out.println(((HandlerMethod)handler).getMethod().getName()); // 方法名
        request.setAttribute("startTime",new Date().getTime());
        return true; // 是否调用后续方法（controller接口方法、postHandle、afterCompletion）
    }

    // controller接口方法正常执行才会执行postHandle方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("拦截器耗时："+(new Date().getTime() - startTime));
        System.out.println("postHandle");
    }
    // controller接口方法正常执行、抛出异常都会执行afterCompletion方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("拦截器耗时："+(new Date().getTime() - startTime));
        System.out.println("afterCompletion");
        System.out.println("ex is "+ex);
    }
}
