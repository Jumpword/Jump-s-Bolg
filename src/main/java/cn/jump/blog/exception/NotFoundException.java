package cn.jump.blog.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception of entity not found.
 *
 * @author Jumping
 */
public class NotFoundException extends BlogException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
