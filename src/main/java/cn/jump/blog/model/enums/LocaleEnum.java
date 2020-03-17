package cn.jump.blog.model.enums;

public enum LocaleEnum {

    /**
     * 简体中文
     */
    ZH_CN("zh_CN"),

    /**
     * 英文
     */
    EN_US("en_US");

    private String value;

    LocaleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
