package cn.jump.blog.web.interceptor;
import cn.jump.blog.model.enums.BlogPropertiesEnum;
import cn.jump.blog.model.enums.TrueFalseEnum;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.jump.blog.model.dto.BlogConst.OPTIONS;

/**
 * <pre>
 *     博客初始化拦截器
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/28
 */
@Slf4j
@Component
public class InstallInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("进入到安装拦截器");
        //判断是否安装过 未安装重定向到 /install 遇到return false 退出
        if (StrUtil.equals(TrueFalseEnum.TRUE.getDesc(), OPTIONS.get(BlogPropertiesEnum.IS_INSTALL.getProp()))) {
            log.info("判断是否已经安装，如果已经安装则返回true");
            return true;
        }

        /*重定向到该路由*/
        log.info("如果没有安装则重定向到安装控制器");
        response.sendRedirect("/install");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
