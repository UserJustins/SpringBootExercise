package com.justin.sprigboot_crud.config;

import com.justin.sprigboot_crud.component.LoginInterceptor;
import com.justin.sprigboot_crud.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 编写到这里的时候，遇到的问题就是静态资源不能被映射了
 * 使用WebMvcConfigurationSupport后SpringBoot的自动配置就不再生效了
 *  原因就是：WebMvcAutoConfiguration自动配置类上使用了这个注解@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
 *  解决：只有自己重新的定义资源的映射路径
 */
@Configuration
public class MyMVCConfiguration extends WebMvcConfigurationSupport {


    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
        super.addViewControllers(registry);

    }
    /*自己指定静态资源的映射路径*/
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
    /*添加自定义的区域解析器*/
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
    /*配置登录拦截器*/
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/login","/webjars/**","/asserts/**");
        /*
        * 使用WebMvcConfigurationSupport以上配置将静态资源全部拦截了
        * .excludePathPatterns("/index.html", "/", "/user/login");
        * 这样配置的时候静态资源就被拦截了，没办法我就只能这样的进行配置了
        * .excludePathPatterns("/index.html", "/", "/user/login","/webjars/**","/asserts/**")
        *
        */
    }

}
