package cn.jump.blog.config;

import cn.jump.blog.model.freemarker.method.RandomMethod;
import cn.jump.blog.model.freemarker.method.RecentCommentsMethod;
import cn.jump.blog.model.freemarker.method.RecentPostsMethod;
import cn.jump.blog.model.freemarker.tag.ArticleTagDirective;
import cn.jump.blog.model.freemarker.tag.CommonTagDirective;
import cn.jump.blog.service.OptionsService;
import cn.jump.blog.service.UserService;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <pre>
 *     FreeMarker配置
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/26
 */
@Slf4j
@Configuration
public class FreeMarkerAutoConfiguration {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonTagDirective commonTagDirective;

    @Autowired
    private ArticleTagDirective articleTagDirective;

    @Autowired
    private RandomMethod randomMethod;

    @Autowired
    private RecentPostsMethod recentPostsMethod;

    @Autowired
    private RecentCommentsMethod recentCommentsMethod;

    @PostConstruct
    public void setSharedVariable() {
        log.info("进入到freemarker设置");
        try {
            log.info("自定义freemarker标签");
            //自定义标签
            configuration.setSharedVariable("commonTag", commonTagDirective);
            configuration.setSharedVariable("articleTag", articleTagDirective);
            configuration.setSharedVariable("options", optionsService.findAllOptions());
            configuration.setSharedVariable("user", userService.findUser());
            configuration.setSharedVariable("randomMethod", randomMethod);
            configuration.setSharedVariable("recentPostsMethod", recentPostsMethod);
            configuration.setSharedVariable("recentCommentsMethod", recentCommentsMethod);
        } catch (TemplateModelException e) {
            log.error("Custom tags failed to load：{}", e.getMessage());
        }
    }
}
