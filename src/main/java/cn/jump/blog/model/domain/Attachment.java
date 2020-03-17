package cn.jump.blog.model.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *     附件
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/10
 */
//自动生成Getter、Setter方法
@Data
//实体类和数据库表做映射
@Entity
//创建数据库表名为"jump_attachment"
@Table(name = "jump_attachment")
//自动生成时间字段
@EntityListeners(AuditingEntityListener.class)
public class Attachment implements Serializable {

    private static final long serialVersionUID = 3060117944880138064L;

    /**
     * 附件编号
     */
    @Id
    @GeneratedValue
    private Long attachId;

    /**
     * 附件名
     */
    private String attachName;

    /**
     * 附件路径
     */
    private String attachPath;

    /**
     * 附件缩略图路径
     */
    private String attachSmallPath;

    /**
     * 附件类型
     */
    private String attachType;

    /**
     * 附件后缀
     */
    private String attachSuffix;

    /**
     * 上传时间
     */
    @CreatedDate
    //配合@EntityListeners注解使用
    private Date attachCreated;

    /**
     * 附件大小
     */
    private String attachSize;

    /**
     * 附件长宽
     */
    private String attachWh;

    /**
     * 附件地址
     */
    private String attachLocation;

    /**
     * 附件来源，0：上传，1：外部链接
     */
    private Integer attachOrigin = 0;
}
