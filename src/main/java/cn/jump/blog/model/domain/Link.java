package cn.jump.blog.model.domain;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <pre>
 *     友情链接
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/14
 */
@Data
@Entity
@Table(name = "jump_link")
public class Link implements Serializable {

    private static final long serialVersionUID = 5441686055841177588L;

    /**
     * 友情链接编号
     */
    @Id
    @GeneratedValue
    private Long linkId;

    /**
     * 友情链接名称
     */
    @NotEmpty(message = "友情链接名称不能为空！")
    private String linkName;

    /**
     * 友情链接地址
     */
    @NotEmpty(message = "友情链接地址不能为空！")
    private String linkUrl;

    /**
     * 友情链接头像
     */
    private String linkPic;

    /**
     * 友情链接描述
     */
    private String linkDesc;
}
