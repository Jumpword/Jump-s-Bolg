package cn.jump.blog.web.controller.api;

import cn.jump.blog.model.domain.User;
import cn.jump.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *     用户API
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/6
 */
@RestController
@RequestMapping(value = "/api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;

    /**
     * 获取博主信息
     *
     * <p>
     * result json:
     * <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": {
     *         "userId": ,
     *         "userDisplayName": "",
     *         "userEmail": "",
     *         "userAvatar": "",
     *         "userDesc": ""
     *     }
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping
    public User user() {
        return userService.findUser();
    }
}
