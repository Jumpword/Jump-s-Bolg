package cn.jump.blog.repository;

import cn.jump.blog.model.domain.Tag;
import cn.jump.blog.repository.base.BaseRepository;

/**
 * <pre>
 *     标签持久层
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/12
 */
public interface TagRepository extends BaseRepository<Tag, Long> {

    /**
     * 根据标签路径查询，用于验证是否已经存在该路径
     *
     * @param tagUrl tagUrl
     * @return Tag
     */
    Tag findTagByTagUrl(String tagUrl);

    /**
     * 根据标签名称查询
     *
     * @param tagName 标签名
     * @return Tag
     */
    Tag findTagByTagName(String tagName);
}
