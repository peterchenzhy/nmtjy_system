package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.model.req.CourseRegisterReq;
import cn.czy.nmtjy.model.req.PaymentReq;
import cn.czy.nmtjy.model.req.StudentReq;
import cn.czy.nmtjy.service.course.CourseRegistrationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:13
 * @Version 1.0
 */
@RestController
public class CourseRegistrationController {
    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @RequestMapping(value = "/course/register/{courseId}", method = RequestMethod.POST)
    public boolean createCourse(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId, @RequestBody CourseRegisterReq courseRegisterReq) {
        return this.courseRegistrationService.courseRegister(loginUserId, courseRegisterReq);
    }

    @RequestMapping(value = "/course/registerInfo", method = RequestMethod.GET)
    public PageInfo createCourse(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId,
                                 @RequestParam(value = "studentName", required = false) String studentName,
                                 @RequestParam(value = "courseName", required = false) String courseName,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                 @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {
        return this.courseRegistrationService.registerInfo(studentName, courseName, pageSize, pageNo);
    }
}
