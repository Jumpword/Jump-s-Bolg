package cn.jump.blog.service;

import cn.jump.blog.model.domain.Attachment;
import cn.jump.blog.service.base.CrudService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <pre>
 *     附件业务逻辑接口
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/10
 */
public interface AttachmentService extends CrudService<Attachment, Long> {

    /**
     * 上传转发
     *
     * @param file    file
     * @param request request
     * @return Map
     */
    Map<String, String> upload(MultipartFile file, HttpServletRequest request);

    /**
     * 原生服务器上传
     *
     * @param file    file
     * @param request request
     * @return Map
     */
    Map<String, String> attachUpload(MultipartFile file, HttpServletRequest request);

    /**
     * 七牛云上传
     *
     * @param file    file
     * @param request request
     * @return Map
     */
    Map<String, String> attachQiNiuUpload(MultipartFile file, HttpServletRequest request);

    /**
     * 又拍云上传
     *
     * @param file    file
     * @param request request
     * @return Map
     */
    Map<String, String> attachUpYunUpload(MultipartFile file, HttpServletRequest request);

    /**
     * 七牛云删除附件
     *
     * @param key key
     * @return boolean
     */
    boolean deleteQiNiuAttachment(String key);

    /**
     * 又拍云删除附件
     *
     * @param fileName fileName
     * @return boolean
     */
    boolean deleteUpYunAttachment(String fileName);

}
