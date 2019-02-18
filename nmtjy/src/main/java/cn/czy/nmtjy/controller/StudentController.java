package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.model.req.StudentReq;
import cn.czy.nmtjy.service.student.StudentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 学生
 * @Author: PeterChen
 * @Date: 2019/2/17 21:13
 * @Version 1.0
 */
@RestController
public class StudentController extends BaseController {
    @Autowired
    private StudentManagerService studentManagerService;

    @RequestMapping(value = "/student/create",method = RequestMethod.POST)
    public boolean createCourse(@RequestHeader("login-userId") Integer loginUserId,

                                @RequestBody @Valid StudentReq req) {

        return studentManagerService.insertStudent(loginUserId,req);
    }
}
