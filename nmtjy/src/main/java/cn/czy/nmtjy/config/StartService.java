package cn.czy.nmtjy.config;

import cn.czy.nmtjy.service.cache.CourseConfigCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: PeterChen
 * @Date: 2019/3/16 19:05
 * @Version 1.0
 */
@Component
public class StartService implements ApplicationRunner {

    @Autowired
    private CourseConfigCacheService cacheService;
    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        cacheService.loadConfigService();
    }
}
