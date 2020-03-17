package cn.jump.blog.web.controller.api;

import cn.jump.blog.exception.NotFoundException;
import cn.jump.blog.model.domain.Post;
import cn.jump.blog.model.enums.PostTypeEnum;
import cn.jump.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *     页面API
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/6
 */
@RestController
@RequestMapping(value = "/api/pages")
public class ApiPageController {

    @Autowired
    private PostService postService;

    /**
     * 获取单个页面
     *
     * <p>
     * result json:
     * <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": {
     *         "postId": ,
     *         "user": {},
     *         "postTitle": "",
     *         "postType": "",
     *         "postContentMd": "",
     *         "postContent": "",
     *         "postUrl": "",
     *         "postSummary": ,
     *         "categories": [],
     *         "tags": [],
     *         "comments": [],
     *         "postThumbnail": "",
     *         "postDate": "",
     *         "postUpdate": "",
     *         "postStatus": 0,
     *         "postViews": 0,
     *         "allowComment": 1,
     *         "customTpl": ""
     *     }
     * }
     *     </pre>
     * </p>
     *
     * @param postId postId
     *
     * @return JsonResult
     */
    @GetMapping(value = "/{postId}")
    public Post pages(@PathVariable(value = "postId") Long postId) {
        final Post post = postService.findByPostId(postId, PostTypeEnum.POST_TYPE_PAGE.getDesc());
        if (post == null) {
            throw new NotFoundException("Post with id: " + postId + " was not found").setErrorData(postId);
        }

        // Cache views
        postService.cacheViews(postId);

        return post;
    }
}
