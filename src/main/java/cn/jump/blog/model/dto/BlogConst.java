package cn.jump.blog.model.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     公共常量
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/05/29
 */
public class BlogConst {

    /**
     * 所有设置选项（key,value）
     */
    public static Map<String, String> OPTIONS = new HashMap<>();

    /**
     * OwO表情
     */
    public static Map<String, String> OWO = new HashMap<>();

    /**
     * 所有主题
     */
    public static List<Theme> THEMES = new ArrayList<>();

    /**
     * user_session
     */
    public static String USER_SESSION_KEY = "user_session";

    /**
     * 文章阅读数缓存
     */
    public static Map<Long, Long> POSTS_VIEWS = new HashMap<>();
}
