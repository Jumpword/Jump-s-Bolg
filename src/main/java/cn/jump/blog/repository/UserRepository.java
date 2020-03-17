package cn.jump.blog.repository;
import cn.jump.blog.model.domain.User;
import cn.jump.blog.repository.base.BaseRepository;
/**
 * <pre>
 *     用户持久层
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/03/14
 */
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * 根据用户名和密码查询
     *
     * @param userName userName
     * @param userPass userPass
     * @return User
     */
    User findByUserNameAndUserPass(String userName, String userPass);

    /**
     * 根据邮箱和密码查询
     *
     * @param userEmail userEmail
     * @param userPass  userPass
     * @return User
     */
    User findByUserEmailAndUserPass(String userEmail, String userPass);

    /**
     * 根据用户编号和密码查询
     *
     * @param userId   userId
     * @param userPass userpass
     * @return User
     */
    User findByUserIdAndUserPass(Long userId, String userPass);
}
