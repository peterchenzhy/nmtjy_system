package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.service.cache.EhcacheUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: PeterChen
 * @Date: 2019/2/10 15:09
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/test")
    public String test1(){
        return "hello this is test1 ";
    }

    @RequestMapping("/test/cache")
    public String test2() {
        Map<String,String > map  = (Map<String,String >) EhcacheUtils.getCache(cacheManager, Consts.COURSE_PROGRESS);
        map.forEach((k,v)->{
            log.warn(k);
            log.warn(v);
        });

        Integer a = 1 ;
        log.warn(map.get(a));

        return "hello this is test2 ";
    }
}
