package cn.jump.blog.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 *     主题信息
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/3
 */
@Data
public class Theme implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主题名称
     */
    private String themeName;

    /**
     * 是否支持设置
     */
    private boolean hasOptions;

    /**
     * 是否支持更新
     */
    private boolean hasUpdate;
}
