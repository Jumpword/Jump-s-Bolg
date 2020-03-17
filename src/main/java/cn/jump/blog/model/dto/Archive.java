package cn.jump.blog.model.dto;

import cn.jump.blog.model.domain.Post;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     文章归档
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/20
 */
@Data
public class Archive implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    /**
     * 对应的文章数
     */
    private String count;

    /**
     * 对应的文章
     */
    private List<Post> posts;
}
