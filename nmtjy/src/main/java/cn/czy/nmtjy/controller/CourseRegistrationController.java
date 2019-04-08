package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.model.req.PaymentReq;
import cn.czy.nmtjy.model.req.StudentReq;
import cn.czy.nmtjy.service.course.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:13
 * @Version 1.0
 */
@RestController
public class CourseRegistrationController  {
    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @RequestMapping(value = "/course/register/{courseId}", method = RequestMethod.POST)
    public boolean createCourse(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId,
                                @PathVariable("courseId") Long courseId,
                                @RequestParam("studentId") Long studentId,
                                @RequestParam("times") Integer times){
        return this.courseRegistrationService.courseRegister(loginUserId,courseId,studentId,times);
    }
}
