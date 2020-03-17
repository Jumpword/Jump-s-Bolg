package cn.jump.blog.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception caused by bad request.
 *
 * @author Jumping
 */
public class





BadRequestException extends BlogException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
