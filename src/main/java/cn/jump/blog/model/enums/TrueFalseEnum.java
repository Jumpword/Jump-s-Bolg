package cn.jump.blog.model.enums;

/**
 * <pre>
 *     true or false enum
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/07/16
 */
public enum TrueFalseEnum {

    /**
     * 真
     */
    TRUE("true"),

    /**
     * 假
     */
    FALSE("false");

    private String desc;

    TrueFalseEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
