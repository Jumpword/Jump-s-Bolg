package cn.jump.blog.web.controller.api;

import cn.jump.blog.model.domain.Link;
import cn.jump.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <pre>
 *     友情链接API
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/06/6
 */
@RestController
@RequestMapping(value = "/api/links")
public class ApiLinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 获取所有友情链接
     *
     * <p>
     * result json:
     * <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": [
     *         {
     *             "linkId": ,
     *             "linkName": "",
     *             "linkUrl": "",
     *             "linkPic": "",
     *             "linkDesc": ""
     *         }
     *     ]
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping
    public List<Link> links() {
        return linkService.listAll();
    }
}
