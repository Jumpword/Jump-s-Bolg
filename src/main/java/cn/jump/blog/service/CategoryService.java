package cn.jump.blog.service;

import cn.jump.blog.model.domain.Category;
import cn.jump.blog.service.base.CrudService;

import java.util.List;

/**
 * <pre>
 *     分类业务逻辑接口
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/30
 */
public interface CategoryService extends CrudService<Category, Long> {

    /**
     * 根据分类目录路径查询，用于验证是否已经存在该路径
     *
     * @param cateUrl cateUrl
     * @return category
     */
    Category findByCateUrl(String cateUrl);

    /**
     * 根据分类名称查询
     *
     * @param cateName 分类名称
     * @return Category
     */
    Category findByCateName(String cateName);

    /**
     * 将分类字符串集合转化为Category泛型集合
     *
     * @param strings strings
     * @return List
     */
    List<Category> strListToCateList(List<String> strings);
}
