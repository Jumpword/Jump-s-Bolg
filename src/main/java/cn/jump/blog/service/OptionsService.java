package cn.jump.blog.service;

import cn.jump.blog.model.domain.Options;
import cn.jump.blog.service.base.CrudService;

import java.util.Map;

/**
 * <pre>
 *     系统设置业务逻辑接口
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/14
 */
public interface OptionsService extends CrudService<Options, String> {

    /**
     * 保存单个设置选项
     *
     * @param key   key
     * @param value value
     */
    void saveOption(String key, String value);

    /**
     * 保存多个设置选项
     *
     * @param options options
     */
    void saveOptions(Map<String, String> options);

    /**
     * 获取所有设置选项
     *
     * @return Map
     */
    Map<String, String> findAllOptions();

    /**
     * 根据key查询单个设置
     *
     * @param key key
     * @return String
     */
    String findOneOption(String key);
}
