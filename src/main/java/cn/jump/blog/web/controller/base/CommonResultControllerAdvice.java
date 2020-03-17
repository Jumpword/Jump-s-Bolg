package cn.jump.blog.web.controller.base;

import cn.jump.blog.model.dto.JsonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

/**
 * Controller 评论结果的通知。
 *
 * @author 張文平
 */
@ControllerAdvice("cn.jump.blog.web.controller.api")
public class CommonResultControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {


    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer,
                                           MediaType contentType,
                                           MethodParameter returnType,
                                           ServerHttpRequest request,
                                           ServerHttpResponse response) {
        // Get return body
        Object returnBody = bodyContainer.getValue();

        if (returnBody instanceof JsonResult) {
            // If the return body is instance of JsonResult
            JsonResult jsonResult = (JsonResult) returnBody;
            response.setStatusCode(HttpStatus.resolve(jsonResult.getCode()));
            return;
        }

        // Normal return body
        HttpStatus okStatus = HttpStatus.OK;
        JsonResult jsonResult = new JsonResult(okStatus.value(), okStatus.getReasonPhrase(), returnBody);
        bodyContainer.setValue(jsonResult);
        response.setStatusCode(okStatus);
    }
}
