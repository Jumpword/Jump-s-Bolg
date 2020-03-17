package cn.jump.blog.repository;

import cn.jump.blog.model.domain.Options;
import cn.jump.blog.repository.base.BaseRepository;

/**
 * <pre>
 *     系统设置持久层
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/14
 */
public interface OptionsRepository extends BaseRepository<Options, String> {

    /**
     * 根据key查询单个option
     *
     * @param key key
     * @return Options
     */
    Options findOptionsByOptionName(String key);
}
