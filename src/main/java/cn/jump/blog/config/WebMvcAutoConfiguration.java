package cn.jump.blog.config;

import cn.jump.blog.filter.CorsFilter;
import cn.jump.blog.web.interceptor.ApiInterceptor;
import cn.jump.blog.web.interceptor.InstallInterceptor;
import cn.jump.blog.web.interceptor.LocaleInterceptor;
import cn.jump.blog.web.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * <pre>
 *     拦截器，资源路径配置
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/2
 */
@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.jump.blog.web.controller")
@PropertySource(value = "classpath:application.yaml", ignoreResourceNotFound = true, encoding = "UTF-8")
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private InstallInterceptor installInterceptor;

    @Autowired
    private ApiInterceptor apiInterceptor;

    @Autowired
    private LocaleInterceptor localeInterceptor;

    /**
     * 注册拦截器
     *拦截器机制 值有访问到设置好的路由才会进入到拦截器
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       log.info("注册登录拦截器1");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin.*")
                .addPathPatterns("/admin/**")
                .addPathPatterns("/backup/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/getLogin")
                .excludePathPatterns("/static/**");
        log.info("注册安装拦截器2");
        registry.addInterceptor(installInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/install")
                .excludePathPatterns("/install/do")
                .excludePathPatterns("/static/**");
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/api/**");
        log.info("注册国际化拦截器");
        registry.addInterceptor(localeInterceptor)
                .addPathPatterns("/admin.*")
                .addPathPatterns("/admin/**")
                .addPathPatterns("/install");
        registry.addInterceptor(localeChangeInterceptor())
                .addPathPatterns("/install");
    }

    /**
     * 配置静态资源路径
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("配置静态资源路径!");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/themes/");
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + System.getProperties().getProperty("user.home") + "/jump/upload/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/jump-backend/images/favicon.ico");
        registry.addResourceHandler("/backup/**")
                .addResourceLocations("file:///" + System.getProperties().getProperty("user.home") + "/jump/backup/");
    }

//    /**
//     * 跨域
//     *
//     * @param registry registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedHeaders("*")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST")
//                .exposedHeaders("access-control-allow-headers",
//                        "access-control-allow-methods",
//                        "access-control-allow-origin",
//                        "access-control-max-age",
//                        "X-Frame-Options",
//                        "token")
//                .allowCredentials(false).maxAge(3600);
//    }

    /**
     * Creates a CorsFilter.
     *
     * @return Cors filter registration bean
     */
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        log.info("将FilterRegistrationBean<CorsFilter>注入到Spring容器当中!");
        FilterRegistrationBean<CorsFilter> corsFilter = new FilterRegistrationBean<>();

        corsFilter.setOrder(Ordered.HIGHEST_PRECEDENCE);
        corsFilter.setFilter(new CorsFilter());
        corsFilter.addUrlPatterns("/api/*");

        return corsFilter;
    }

    /**
     * 国际化设置
     *
     * @return LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        log.info("将LocaleResolver注入到容器当中，国际化设置!");
        final SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }

    /**
     * 国际化参数拦截器
     *
     * @return LocaleChangeInterceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        log.info("将LocaleChangeInterceptor注入到Spring容器当中，国际化参数拦截器！");
        final LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
}
