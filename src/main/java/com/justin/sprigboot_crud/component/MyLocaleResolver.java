package com.justin.sprigboot_crud.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 用来处理登录页面的国际化
 *  点击“中文”和“English”按钮
 *  编写完成以后要添加到Spring的容器中去
 */
public class MyLocaleResolver implements LocaleResolver {
    /*
    *   该方法用来处理国际化的请求
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取请求的参数
        String language = httpServletRequest.getParameter("l");
        //使用默认的区域解析器
        Locale locale = Locale.getDefault();
        //如果参数不为空就使用指定的区域解析器
        if(!StringUtils.isEmpty(language)){
            //请求参数分割成语言和国家代号
            String[] arr = language.split("_");
            locale = new Locale(arr[0],arr[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
