package cn.jump.blog.web.controller.front;

import cn.jump.blog.model.domain.Comment;
import cn.jump.blog.model.domain.Gallery;
import cn.jump.blog.model.domain.Post;
import cn.jump.blog.model.dto.ListPage;
import cn.jump.blog.model.enums.*;
import cn.jump.blog.service.CommentService;
import cn.jump.blog.service.GalleryService;
import cn.jump.blog.service.PostService;
import cn.jump.blog.utils.CommentUtil;
import cn.jump.blog.web.controller.core.BaseController;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static cn.jump.blog.model.dto.BlogConst.OPTIONS;

/**
 * <pre>
 *     前台内置页面，自定义页面控制器
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/26
 */
@Controller
public class FrontPageController extends BaseController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    /**
     * 跳转到图库页面
     *
     * @return 模板路径/themes/{theme}/gallery
     */
    @GetMapping(value = "/gallery")
    public String gallery(Model model) {
        final List<Gallery> galleries = galleryService.listAll();
        model.addAttribute("galleries", galleries);
        return this.render("gallery");
    }

    /**
     * 友情链接
     *
     * @return 模板路径/themes/{theme}/links
     */
    @GetMapping(value = "/links")
    public String links() {
        return this.render("links");
    }

    /**
     * 渲染自定义页面
     *
     * @param postUrl 页面路径
     * @param model   model
     * @return 模板路径/themes/{theme}/post
     */
    @GetMapping(value = "/p/{postUrl}")
    public String getPage(@PathVariable(value = "postUrl") String postUrl,
                          @RequestParam(value = "cp", defaultValue = "1") Integer cp,
                          Model model) {
        final Post post = postService.findByPostUrl(postUrl, PostTypeEnum.POST_TYPE_PAGE.getDesc());
        if (null == post || !post.getPostStatus().equals(PostStatusEnum.PUBLISHED.getCode())) {
            return this.renderNotFound();
        }
        List<Comment> comments = null;
        if (StrUtil.equals(OPTIONS.get(BlogPropertiesEnum.NEW_COMMENT_NEED_CHECK.getProp()), TrueFalseEnum.TRUE.getDesc()) || OPTIONS.get(BlogPropertiesEnum.NEW_COMMENT_NEED_CHECK.getProp()) == null) {
            comments = commentService.findCommentsByPostAndCommentStatus(post, CommentStatusEnum.PUBLISHED.getCode());
        } else {
            comments = commentService.findCommentsByPostAndCommentStatusNot(post, CommentStatusEnum.RECYCLE.getCode());
        }
        //默认显示10条
        int size = 10;
        if (StrUtil.isNotBlank(OPTIONS.get(BlogPropertiesEnum.INDEX_COMMENTS.getProp()))) {
            size = Integer.parseInt(OPTIONS.get(BlogPropertiesEnum.INDEX_COMMENTS.getProp()));
        }
        //评论分页
        final ListPage<Comment> commentsPage = new ListPage<Comment>(CommentUtil.getComments(comments), cp, size);
        final int[] rainbow = PageUtil.rainbow(cp, commentsPage.getTotalPage(), 3);
        model.addAttribute("is_page", true);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentsPage);
        model.addAttribute("commentsCount", comments.size());
        model.addAttribute("rainbow", rainbow);
        postService.cacheViews(post.getPostId());

        //如果设置了自定义模板，则渲染自定义模板
        if (StrUtil.isNotEmpty(post.getCustomTpl())) {
            return this.render(post.getCustomTpl());
        }
        return this.render("page");
    }
}
