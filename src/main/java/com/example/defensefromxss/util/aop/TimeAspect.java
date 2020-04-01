package com.example.defensefromxss.util.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

// 切面类
@Aspect
@Component
public class TimeAspect {
//    @Before() // 之前
//    @After()    // 之后
//    @AfterThrowing  // 抛出异常执行
//    @Around()   // 包含以上三种

    @Around("execution(* com.example.defensefromxss.controller.LoginController.*(..))") // 配置环绕，并且配置切入点表达式
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();// 方法参数的数组
        for (Object arg : args) {
            System.out.println("arg is "+ arg);
        }
        long start = new Date().getTime();
        Object object = pjp.proceed();// 调用被拦截的controller方法
        System.out.println("耗时："+(new Date().getTime() - start));
        System.out.println("time aspect end");
        return object;
    }
}
