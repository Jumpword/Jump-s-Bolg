package cn.jump.blog.web.controller.front;

import cn.jump.blog.model.domain.Category;
import cn.jump.blog.model.domain.Post;
import cn.jump.blog.model.enums.BlogPropertiesEnum;
import cn.jump.blog.service.CategoryService;
import cn.jump.blog.service.PostService;
import cn.jump.blog.web.controller.core.BaseController;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static cn.jump.blog.model.dto.BlogConst.OPTIONS;
import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * <pre>
 *     前台文章分类控制器
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/26
 */
@Controller
@RequestMapping(value = "categories")
public class FrontCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    /**
     * 分类列表页面
     *
     * @param model model
     * @return String
     */
    @GetMapping
    public String categories(Model model) {
        final List<Category> categories = categoryService.listAll();
        model.addAttribute("categories", categories);
        return this.render("categories");
    }

    /**
     * 根据分类路径查询文章
     *
     * @param model   model
     * @param cateUrl cateUrl
     * @return string
     */
    @GetMapping(value = "{cateUrl}")
    public String categories(Model model,
                             @PathVariable("cateUrl") String cateUrl) {
        return this.categories(model, cateUrl, 1, Sort.by(DESC, "postDate"));
    }

    /**
     * 根据分类目录查询所有文章 分页
     *
     * @param model   model
     * @param cateUrl 分类目录路径
     * @param page    页码
     * @return String
     */
    @GetMapping("{cateUrl}/page/{page}")
    public String categories(Model model,
                             @PathVariable("cateUrl") String cateUrl,
                             @PathVariable("page") Integer page,
                             @SortDefault(sort = "postDate", direction = DESC) Sort sort) {
        final Category category = categoryService.findByCateUrl(cateUrl);
        if (null == category) {
            return this.renderNotFound();
        }
        int size = 10;
        if (StrUtil.isNotBlank(OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()))) {
            size = Integer.parseInt(OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()));
        }
        final Pageable pageable = PageRequest.of(page - 1, size, sort);
        final Page<Post> posts = postService.findPostByCategories(category, pageable);
        final int[] rainbow = PageUtil.rainbow(page, posts.getTotalPages(), 3);
        model.addAttribute("is_categories", true);
        model.addAttribute("posts", posts);
        model.addAttribute("rainbow", rainbow);
        model.addAttribute("category", category);
        return this.render("category");
    }
}
