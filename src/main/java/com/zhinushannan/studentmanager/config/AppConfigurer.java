package com.zhinushannan.studentmanager.config;


import com.zhinushannan.studentmanager.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author zhinushannan
 * @date 2020/12/26 11:37
 * @subject
 */
@Configuration
public class AppConfigurer implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置所有 url 都拦截
        registry.addInterceptor(userInterceptor).addPathPatterns("/**")
                // 登录页面不拦截
                .excludePathPatterns("/login")
                .excludePathPatterns("/authenticate")
                // 关于我们  不拦截
                .excludePathPatterns("/about/zhinushannan.html")
                // 静态资源不拦截
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/img/**");
    }
}
