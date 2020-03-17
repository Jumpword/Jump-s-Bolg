package cn.jump.blog.web.controller.core;

import cn.jump.blog.model.domain.*;
import cn.jump.blog.model.dto.JsonResult;
import cn.jump.blog.model.dto.LogsRecord;
import cn.jump.blog.model.enums.*;
import cn.jump.blog.service.*;
import cn.jump.blog.utils.MarkdownUtils;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.jump.blog.model.dto.BlogConst.OPTIONS;

/**
 * <pre>
 *     博客初始化控制器
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/28
 */
@Slf4j
@Controller
@RequestMapping(value = "/install")
public class InstallController {

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogsService logsService;

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private Configuration configuration;

    /**
     * 渲染安装页面
     *
     * @param model model
     * @return 模板路径
     */
    @GetMapping
    public String install(Model model) {
        log.info("进入到安装控制器");
        try {
            //判断是否安装过 如若安装过则添加一个model为true，名为isInstall
            if (StrUtil.equals(TrueFalseEnum.TRUE.getDesc(), OPTIONS.get(BlogPropertiesEnum.IS_INSTALL.getProp()))) {
                log.info("判断是否已经安装，如果安装了，则将isInstall变量设置为true");
                model.addAttribute("isInstall", true);
            } else {
                model.addAttribute("isInstall", false);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("开始将model数据传给指定视图install.ftl");
        /*将数据传给/common/install.ftl视图*/
        return "common/install";
    }

    /**
     * 执行安装
     *
     * @param blogLocale      系统语言
     * @param userName        用户名
     * @param userDisplayName 用户名显示名
     * @param userEmail       用户邮箱
     * @param userPwd         用户密码
     * @param request         request
     * @return JsonResult
     */
    @PostMapping(value = "/do")
    @ResponseBody
    public JsonResult doInstall(@RequestParam("blogLocale") String blogLocale,
                                @RequestParam("blogTitle") String blogTitle,
                                @RequestParam("blogUrl") String blogUrl,
                                @RequestParam("userName") String userName,
                                @RequestParam("userDisplayName") String userDisplayName,
                                @RequestParam("userEmail") String userEmail,
                                @RequestParam("userPwd") String userPwd,
                                HttpServletRequest request) {
     log.info("开始执行安装，进入到此方法是由前端的if标签去控制的，由表单的action指定跳转到此方法");
        try {
            if (StrUtil.equals(TrueFalseEnum.TRUE.getDesc(), OPTIONS.get(BlogPropertiesEnum.IS_INSTALL.getProp()))) {
                return new JsonResult(ResultCodeEnum.FAIL.getCode(), "该博客已初始化，不能再次安装！");
            }
            //创建新的用户
            final User user = new User();
            user.setUserName(userName);
            if (StrUtil.isBlank(userDisplayName)) {
                userDisplayName = userName;
            }
            user.setUserDisplayName(userDisplayName);
            user.setUserEmail(userEmail);
            user.setUserPass(SecureUtil.md5(userPwd));
            userService.create(user);

            //默认分类
            final Category category = new Category();
            category.setCateName("未分类");
            category.setCateUrl("default");
            category.setCateDesc("未分类");
            categoryService.create(category);

            //第一篇文章
            final Post post = new Post();
            final List<Category> categories = new ArrayList<>();
            categories.add(category);
            post.setPostTitle("Hello Jump!");
            post.setPostContentMd("# Hello Jump!\n" +
                    "欢迎使用Jump进行创作，删除这篇文章后赶紧开始吧。");
            post.setPostContent(MarkdownUtils.renderMarkdown(post.getPostContentMd()));
            post.setPostSummary("欢迎使用Jump进行创作，删除这篇文章后赶紧开始吧。");
            post.setPostStatus(0);
            post.setPostUrl("hello-jump");
            post.setUser(user);
            post.setCategories(categories);
            post.setAllowComment(AllowCommentEnum.ALLOW.getCode());
            post.setPostThumbnail("/static/jump-frontend/images/thumbnail/thumbnail-" + RandomUtil.randomInt(1, 9) + ".jpg");
            postService.create(post);

            //第一个评论
            final Comment comment = new Comment();
            comment.setPost(post);
            comment.setCommentAuthor("jumping");
            comment.setCommentAuthorEmail("632741793@qq.com");
            comment.setCommentAuthorUrl("https://jumpword.cn");
            comment.setCommentAuthorIp("127.0.0.1");
            comment.setCommentAuthorAvatarMd5(SecureUtil.md5("632741793@qq.com"));
            comment.setCommentContent("欢迎，欢迎！");
            comment.setCommentStatus(0);
            comment.setCommentAgent("Mozilla/5.0 (Macintosh; Intel Iphone XR X ) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.162 Safari/537.36");
            comment.setIsAdmin(0);
            commentService.create(comment);

            final Map<String, String> options = new HashMap<>();
            options.put(BlogPropertiesEnum.IS_INSTALL.getProp(), TrueFalseEnum.TRUE.getDesc());
            options.put(BlogPropertiesEnum.BLOG_LOCALE.getProp(), blogLocale);
            options.put(BlogPropertiesEnum.BLOG_TITLE.getProp(), blogTitle);
            options.put(BlogPropertiesEnum.BLOG_URL.getProp(), blogUrl);
            options.put(BlogPropertiesEnum.THEME.getProp(), "material");
            options.put(BlogPropertiesEnum.BLOG_START.getProp(), DateUtil.format(DateUtil.date(), "yyyy-MM-dd"));
            options.put(BlogPropertiesEnum.SMTP_EMAIL_ENABLE.getProp(), TrueFalseEnum.FALSE.getDesc());
            options.put(BlogPropertiesEnum.NEW_COMMENT_NOTICE.getProp(), TrueFalseEnum.FALSE.getDesc());
            options.put(BlogPropertiesEnum.COMMENT_PASS_NOTICE.getProp(), TrueFalseEnum.FALSE.getDesc());
            options.put(BlogPropertiesEnum.COMMENT_REPLY_NOTICE.getProp(), TrueFalseEnum.FALSE.getDesc());
            options.put(BlogPropertiesEnum.ATTACH_LOC.getProp(), AttachLocationEnum.SERVER.getDesc());
            optionsService.saveOptions(options);

            //更新日志
            logsService.save(LogsRecord.INSTALL, "安装成功，欢迎使用Jump。", request);

            final Menu menuIndex = new Menu();
            menuIndex.setMenuName("首页");
            menuIndex.setMenuUrl("/");
            menuIndex.setMenuSort(1);
            menuIndex.setMenuIcon(" ");
            menuService.create(menuIndex);

            final Menu menuArchive = new Menu();
            menuArchive.setMenuName("归档");
            menuArchive.setMenuUrl("/archives");
            menuArchive.setMenuSort(2);
            menuArchive.setMenuIcon(" ");
            menuService.create(menuArchive);

            OPTIONS.clear();
            OPTIONS = optionsService.findAllOptions();
            configuration.setSharedVariable("options", OPTIONS);
            configuration.setSharedVariable("user", userService.findUser());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new JsonResult(ResultCodeEnum.FAIL.getCode(), e.getMessage());
        }
        return new JsonResult(ResultCodeEnum.SUCCESS.getCode(), "安装成功！");
    }
}
