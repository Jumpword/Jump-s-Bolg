package cn.jump.blog.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception caused by accessing forbidden resources.
 *
 * @author Jumping
 */
public class ForbiddenException extends BlogException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
