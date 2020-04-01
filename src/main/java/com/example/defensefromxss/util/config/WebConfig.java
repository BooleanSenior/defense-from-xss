package com.example.defensefromxss.util.config;


import com.example.defensefromxss.util.filter.TimeFilter;

import com.example.defensefromxss.util.filter.XssFilter;
import com.example.defensefromxss.util.filter.XssHttpServletRequestWrapperNew;
import com.example.defensefromxss.util.interceptor.TimeInterceptor;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration // 配置类（配置过滤器、拦截器）
public class WebConfig implements WebMvcConfigurer {

    // 配置自定义过滤器为bean到spring IOC容器中
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TimeFilter()); // 注册自定义过滤器

        List<String> URLS = new ArrayList<>();
        URLS.add("/*");
        filterRegistrationBean.setUrlPatterns(URLS);// 配置需要拦截的资源

        return filterRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }


    // 配置spring拦截器（interceptor）1.继承WebMvcConfigurer 接口 2.实现addInterceptors方法
//    @Autowired
//    private TimeInterceptor timeInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor); // 注册拦截器
//    }
}
