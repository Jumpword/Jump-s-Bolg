package cn.jump.blog.web.interceptor;

import cn.jump.blog.model.dto.JsonResult;
import cn.jump.blog.model.enums.BlogPropertiesEnum;
import cn.jump.blog.model.enums.TrueFalseEnum;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

import static cn.jump.blog.model.dto.BlogConst.OPTIONS;

/**
 * <pre>
 *     API接口拦截器，用户可自己选择关闭或者开启
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/28
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "token";

    private final ObjectMapper objectMapper;

    public ApiInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StrUtil.equals(TrueFalseEnum.TRUE.getDesc(), OPTIONS.get(BlogPropertiesEnum.API_STATUS.getProp()))) {
            if (StrUtil.equals(request.getHeader(TOKEN), OPTIONS.get(BlogPropertiesEnum.API_TOKEN.getProp()))) {
                return true;
            } else {
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                JsonResult result = new JsonResult(HttpStatus.BAD_REQUEST.value(), "Invalid Token");
                response.getWriter().write(objectMapper.writeValueAsString(result));
                return false;
            }
        }
        response.sendRedirect("/404");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
