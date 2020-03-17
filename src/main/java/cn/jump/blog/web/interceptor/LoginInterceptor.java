package cn.jump.blog.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.jump.blog.model.dto.BlogConst.USER_SESSION_KEY;

/**
 * <pre>
 *     后台登录控制器
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/05/13
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入到登录过滤器!");
        final Object obj = request.getSession().getAttribute(USER_SESSION_KEY);
        //如果user不为空则放行
        if (null != obj) {
            return true;
        }
        //否则拦截并跳转到登录
        response.sendRedirect("/admin/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
