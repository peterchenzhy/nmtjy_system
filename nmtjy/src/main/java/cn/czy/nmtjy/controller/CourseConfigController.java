package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.model.po.CourseConfigPo;
import cn.czy.nmtjy.service.CourseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: PeterChen
 * @Date: 2019/2/24 17:06
 * @Version 1.0
 */
@RestController
public class CourseConfigController {

    @Autowired
    private CourseConfigService courseConfigService;

    @RequestMapping(value = "/course/config/{configType}", method = RequestMethod.GET)
    public List<CourseConfigPo> getCourseConfigByType(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId,
                                                      @PathVariable("configType") String configType) {
        return courseConfigService.getConfigByConfigType(configType);
    }

}
