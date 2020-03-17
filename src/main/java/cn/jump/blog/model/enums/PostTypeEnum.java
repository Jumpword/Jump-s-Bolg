package cn.jump.blog.model.enums;

/**
 * <pre>
 *     文章类型enum
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/07/1
 */
public enum PostTypeEnum {

    /**
     * 文章
     */
    POST_TYPE_POST("post"),

    /**
     * 页面
     */
    POST_TYPE_PAGE("page");

    private String desc;

    PostTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
