package cn.jump.blog.web.interceptor;

import cn.jump.blog.model.enums.BlogPropertiesEnum;
import cn.jump.blog.model.enums.LocaleEnum;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static cn.jump.blog.model.dto.BlogConst.OPTIONS;

/**
 * 区域设置 即系统国际化配置
 * @author : 張文平
 * @version : 1.0
 * @date : 2018年09月08日
 *
 */
@Slf4j
@Component
public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入到系统国际化配置拦截器，");
        final Object attribute = request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if (null != attribute) {
            return true;
        }
        if (StrUtil.equals(LocaleEnum.EN_US.getValue(), OPTIONS.get(BlogPropertiesEnum.BLOG_LOCALE.getProp()))) {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en", "US"));
        } else {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("zh", "CN"));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
