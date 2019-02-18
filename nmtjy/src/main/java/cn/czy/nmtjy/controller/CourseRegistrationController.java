package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.service.course.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:13
 * @Version 1.0
 */
@RestController
public class CourseRegistrationController extends BaseController {
    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @RequestMapping(value = "/course/register/{courseId}", method = RequestMethod.POST)
    public boolean createCourse(@RequestHeader("login-userId") Integer loginUserId,
                                @PathVariable("courseId") Long courseId,
                                @RequestParam("studentId") Long studentId,
                                @RequestParam("times") Integer times,
                                @RequestParam(value = "payStatus", required = false, defaultValue = "0") Integer payStatus) {
        return this.courseRegistrationService.courseRegister(loginUserId,courseId,studentId,times,payStatus);
    }
}
