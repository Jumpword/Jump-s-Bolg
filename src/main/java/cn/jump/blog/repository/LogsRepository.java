package cn.jump.blog.repository;

import cn.jump.blog.model.domain.Logs;
import cn.jump.blog.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <pre>
 *     日志持久层
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/19
 */
public interface LogsRepository extends BaseRepository<Logs, Long> {

    /**
     * 查询最新的五条数据
     *
     * @return List
     */
    @Query(value = "SELECT * FROM jump_logs ORDER BY log_created DESC LIMIT 5", nativeQuery = true)
    List<Logs> findTopFive();
}
