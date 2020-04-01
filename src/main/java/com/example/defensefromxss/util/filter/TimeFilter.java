package com.example.defensefromxss.util.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

// 过滤器
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long startTime = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        long endTime = new Date().getTime();
        System.out.println("time filter:" + (endTime - startTime));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
