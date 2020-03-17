package cn.jump.blog.web.controller.api;

import cn.jump.blog.exception.BadRequestException;
import cn.jump.blog.logging.Logger;
import cn.jump.blog.model.domain.Comment;
import cn.jump.blog.model.domain.Post;
import cn.jump.blog.model.dto.JsonResult;
import cn.jump.blog.model.enums.BlogPropertiesEnum;
import cn.jump.blog.model.enums.TrueFalseEnum;
import cn.jump.blog.service.CommentService;
import cn.jump.blog.service.PostService;
import cn.jump.blog.utils.OwoUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static cn.jump.blog.model.dto.BlogConst.OPTIONS;

/**
 * <pre>
 *     评论API
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/6
 */
@RestController
@RequestMapping(value = "/api/comments")
public class ApiCommentController {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    /**
     * 新增评论
     *
     * @param comment comment
     * @param postId  postId
     * @param request request
     *
     * @return JsonResult
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public JsonResult save(@Valid Comment comment,
                           @RequestParam(value = "postId") Long postId,
                           HttpServletRequest request) {
        try {
            Comment lastComment = null;
            final Post post = postService.fetchById(postId).orElse(new Post());
            comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()).toLowerCase());
            comment.setPost(post);
            comment.setCommentAuthorIp(ServletUtil.getClientIP(request));
            comment.setIsAdmin(0);
            comment.setCommentAuthor(HtmlUtil.escape(comment.getCommentAuthor()));
            if (StrUtil.isNotBlank(comment.getCommentAuthorEmail())) {
                comment.setCommentAuthorAvatarMd5(SecureUtil.md5(comment.getCommentAuthorEmail()));
            }
            if (comment.getCommentParent() > 0) {
                lastComment = commentService.fetchById(comment.getCommentParent()).orElse(new Comment());
                final StrBuilder buildContent = new StrBuilder("<a href='#comment-id-");
                buildContent.append(lastComment.getCommentId());
                buildContent.append("'>@");
                buildContent.append(lastComment.getCommentAuthor());
                buildContent.append("</a> ");
                buildContent.append(OwoUtil.markToImg(HtmlUtil.escape(comment.getCommentContent()).replace("&lt;br/&gt;", "<br/>")));
                comment.setCommentContent(buildContent.toString());
            } else {
                //将评论内容的字符专为安全字符
                comment.setCommentContent(OwoUtil.markToImg(HtmlUtil.escape(comment.getCommentContent()).replace("&lt;br/&gt;", "<br/>")));
            }
            if (StrUtil.isNotEmpty(comment.getCommentAuthorUrl())) {
                comment.setCommentAuthorUrl(URLUtil.normalize(comment.getCommentAuthorUrl()));
            }
            commentService.create(comment);
            if (StrUtil.equals(OPTIONS.get(BlogPropertiesEnum.NEW_COMMENT_NEED_CHECK.getProp()), TrueFalseEnum.TRUE.getDesc()) || OPTIONS.get(BlogPropertiesEnum.NEW_COMMENT_NEED_CHECK.getProp()) == null) {
                return new JsonResult(HttpStatus.OK.value(), "你的评论已经提交，待博主审核之后可显示。");
            } else {
                return new JsonResult(HttpStatus.OK.value(), "你的评论已经提交，刷新后即可显示。");
            }
        } catch (Exception e) {
            throw new BadRequestException("评论失败！", e);
        }
    }
}
