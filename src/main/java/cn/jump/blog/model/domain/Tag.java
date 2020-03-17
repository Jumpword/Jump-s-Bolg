package cn.jump.blog.model.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     文章标签
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/12
 */
@Data
@Entity
@Table(name = "jump_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = -7501342327884372194L;

    /**
     * 标签编号
     */
    @Id
    @GeneratedValue
    private Long tagId;

    /**
     * 标签名称
     */
    @NotEmpty(message = "标签名称不能为空！")
    private String tagName;

    /**
     * 标签路径
     */
    @NotEmpty(message = "标签路径不能为空！")
    private String tagUrl;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();
}
