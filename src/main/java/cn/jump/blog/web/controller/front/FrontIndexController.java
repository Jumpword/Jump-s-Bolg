package cn.jump.blog.web.controller.front;

import cn.jump.blog.model.domain.Post;
import cn.jump.blog.model.enums.BlogPropertiesEnum;
import cn.jump.blog.service.PostService;
import cn.jump.blog.web.controller.core.BaseController;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
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

import static cn.jump.blog.model.dto.BlogConst.OPTIONS;
import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * <pre>
 *     前台首页控制器
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/26
 */
@Slf4j
@Controller
@RequestMapping(value = {"/", "index"})
public class FrontIndexController extends BaseController {

    @Autowired
    private PostService postService;


    /**
     * 请求首页
     *
     * @param model model
     * @return 模板路径
     */
    @GetMapping
    public String index(Model model) {
        return this.index(model, 1, Sort.by(DESC, "postDate"));
    }

    /**
     * 首页分页
     *
     * @param model model
     * @param page  当前页码
     * @return 模板路径/themes/{theme}/index
     */
    @GetMapping(value = "page/{page}")
    public String index(Model model,
                        @PathVariable(value = "page") Integer page,
                        @SortDefault(sort = "postDate", direction = DESC) Sort sort) {
        //默认显示10条
        int size = 10;
        if (StrUtil.isNotBlank(OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()))) {
            size = Integer.parseInt(OPTIONS.get(BlogPropertiesEnum.INDEX_POSTS.getProp()));
        }
        //所有文章数据，分页
        final Pageable pageable = PageRequest.of(page - 1, size, sort);
        final Page<Post> posts = postService.findPostByStatus(pageable);
        if (null == posts) {
            return this.renderNotFound();
        }
        final int[] rainbow = PageUtil.rainbow(page, posts.getTotalPages(), 3);
        model.addAttribute("is_index", true);
        model.addAttribute("posts", posts);
        model.addAttribute("rainbow", rainbow);
        return this.render("index");
    }
}
