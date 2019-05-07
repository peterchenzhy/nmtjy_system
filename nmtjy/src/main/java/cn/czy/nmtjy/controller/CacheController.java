package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.service.cache.CourseConfigCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CacheController
 *
 * @author PeterChen
 * @summary CacheController
 * @Copyright (c) 2019, Lianjia Group All Rights Reserved.
 * @Description CacheController
 * @since 2019-05-07 22:12
 */
@RestController
@RequestMapping("/admin")
public class CacheController {

    @Autowired
    private CourseConfigCacheService cacheService;


    @RequestMapping(value = "/clear/cache", method = RequestMethod.GET)
    public String clearConfigCache() {
        cacheService.clearConfigCache();
        return "清理configCache";
    }

    @RequestMapping(value = "/get/cache", method = RequestMethod.GET)
    public String getConfigCache() {
        return cacheService.getConfigCache();
    }


    @RequestMapping(value = "/load/cache", method = RequestMethod.GET)
    public String loadConfigCache() {
        cacheService.loadConfigService();
        return "loading ... configCache";
    }

}
