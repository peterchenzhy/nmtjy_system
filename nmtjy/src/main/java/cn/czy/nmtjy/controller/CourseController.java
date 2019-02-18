package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.model.req.CourseReq;
import cn.czy.nmtjy.service.course.CourseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:13
 * @Version 1.0
 */
@RestController
public class CourseController extends BaseController {
    @Autowired
    private CourseManagerService courseManagerService;

    @RequestMapping(value = "/courseMnager/create",method = RequestMethod.POST)
    public boolean createCourse(@RequestHeader("login-userId") Integer loginUserId, @RequestBody @Valid CourseReq req){
        return courseManagerService.insertCourse(loginUserId,req);
    }
}
