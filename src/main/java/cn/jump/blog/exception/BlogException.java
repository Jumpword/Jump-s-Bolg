package cn.jump.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Base exception of the project.
 *
 * @author Jumping
 */
public abstract class BlogException extends RuntimeException {

    /**
     * Error errorData.
     */
    private Object errorData;

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public BlogException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
