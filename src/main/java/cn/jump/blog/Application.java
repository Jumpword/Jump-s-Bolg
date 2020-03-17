package cn.jump.blog;

import cn.jump.blog.repository.base.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <pre>
 *     Jump's Blog run!
 * </pre>
 *
 * @author : 張文平
 * @date : 2019/07/14
 */
@Slf4j
@SpringBootApplication
@EnableCaching
//自动填充字段功能，为了自动为数据库某字段自动创建时间字段。
@EnableJpaAuditing
//说明操作持久层的接口所在位置，方便扫描
@EnableJpaRepositories(basePackages = "cn.jump.blog.repository", repositoryBaseClass = BaseRepositoryImpl.class)
public class Application {
    public static void main(String[] args) {
        log.info("应用程序启动");
        ApplicationContext context = SpringApplication.run(Application.class, args);
        //获取端口号
        String serverPort = context.getEnvironment().getProperty("server.port");
        log.info("Jump’s Blog started at http://127.0.0.1:" + serverPort);
    }
}
