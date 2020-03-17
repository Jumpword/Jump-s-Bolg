package cn.jump.blog.service.impl;

import cn.jump.blog.model.domain.Link;
import cn.jump.blog.repository.LinkRepository;
import cn.jump.blog.service.LinkService;
import cn.jump.blog.service.base.AbstractCrudService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 *     友情链接业务逻辑实现类
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/14
 */
@Service
public class LinkServiceImpl extends AbstractCrudService<Link, Long> implements LinkService {

    private static final String LINKS_CACHE_KEY = "'link'";

    private static final String LINKS_CACHE_NAME = "links";

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        super(linkRepository);
        this.linkRepository = linkRepository;
    }

    /**
     * 新增/修改友情链接
     *
     * @param link link
     * @return Link
     */
    @Override
    @CacheEvict(value = LINKS_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public Link create(Link link) {
        return super.create(link);
    }

    /**
     * 移除友情链接
     *
     * @param linkId linkId
     * @return Link
     */
    @Override
    @CacheEvict(value = LINKS_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public Link removeById(Long linkId) {
        return super.removeById(linkId);
    }

    /**
     * 查询所有友情链接
     *
     * @return List
     */
    @Override
    @Cacheable(value = LINKS_CACHE_NAME, key = LINKS_CACHE_KEY)
    public List<Link> listAll() {
        return super.listAll();
    }

}
