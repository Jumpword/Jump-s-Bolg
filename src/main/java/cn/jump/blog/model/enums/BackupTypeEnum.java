package cn.jump.blog.model.enums;

/**
 * <pre>
 *     备份类型enum
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/07/22
 */
public enum BackupTypeEnum {

    /**
     * 资源文件
     */
    RESOURCES("resources"),

    /**
     * 数据库
     */
    DATABASES("databases"),

    /**
     * 文章
     */
    POSTS("posts");

    private String desc;

    BackupTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
