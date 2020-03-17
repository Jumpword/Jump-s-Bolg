package cn.jump.blog.task;

import cn.jump.blog.model.domain.Post;
import cn.jump.blog.service.PostService;
import cn.jump.blog.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import static cn.jump.blog.model.dto.BlogConst.POSTS_VIEWS;

/**
 * @author : 張文平
 * @date : 2019/10/5
 */
@Slf4j
public class PostSyncTask {

    /**
     * 将缓存的图文浏览数写入数据库
     */
    public void postSync() {
        final PostService postService = SpringUtil.getBean(PostService.class);
        int count = 0;
        for (Long key : POSTS_VIEWS.keySet()) {
            Post post = postService.getByIdOfNullable(key);
            if (null != post) {
                post.setPostViews(post.getPostViews() + POSTS_VIEWS.get(key));
                postService.create(post);
                count++;
            }
        }
        log.info("The number of visits to {} posts has been updated", count);
        POSTS_VIEWS.clear();
    }
}
