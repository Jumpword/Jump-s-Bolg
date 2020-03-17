package cn.jump.blog.service;

import cn.jump.blog.model.domain.Tag;
import cn.jump.blog.service.base.CrudService;

import java.util.List;

/**
 * <pre>
 *     标签业务逻辑接口
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/12
 */
public interface TagService extends CrudService<Tag, Long> {

    /**
     * 根据标签路径查询
     *
     * @param tagUrl tagUrl
     * @return Tag
     */
    Tag findByTagUrl(String tagUrl);

    /**
     * 根据标签名称查询
     *
     * @param tagName tagName
     * @return Tag
     */
    Tag findTagByTagName(String tagName);

    /**
     * 转换标签字符串为实体集合
     *
     * @param tagList tagList
     * @return List
     */
    List<Tag> strListToTagList(String tagList);
}
