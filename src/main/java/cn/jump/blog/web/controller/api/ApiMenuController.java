package cn.jump.blog.web.controller.api;

import cn.jump.blog.model.domain.Menu;
import cn.jump.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <pre>
 *     菜单API
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/6
 */
@RestController
@RequestMapping(value = "/api/menus")
public class ApiMenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取所有菜单
     *
     * <p>
     * result json:
     * <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": [
     *         {
     *             "menuId": ,
     *             "menuName": "",
     *             "menuUrl": "",
     *             "menuSort": ,
     *             "menuIcon": "",
     *             "menuTarget":
     *         }
     *     ]
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping
    public List<Menu> menus() {
        return menuService.listAll();
    }
}
