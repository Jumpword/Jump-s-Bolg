package cn.jump.blog.model.enums;

/**
 * <pre>
 *     返回结果enum
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/07/14
 */
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(1),

    /**
     * 失败
     */
    FAIL(0);

    Integer code;

    ResultCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
