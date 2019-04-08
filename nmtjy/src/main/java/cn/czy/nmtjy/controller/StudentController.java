package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.model.po.StudentPo;
import cn.czy.nmtjy.model.req.StudentReq;
import cn.czy.nmtjy.service.student.StudentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 学生
 * @Author: PeterChen
 * @Date: 2019/2/17 21:13
 * @Version 1.0
 */
@RestController
public class StudentController  {
    @Autowired
    private StudentManagerService studentManagerService;

    @RequestMapping(value = "/student/create",method = RequestMethod.POST)
    public boolean createStudent(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId,
                                @RequestBody @Valid StudentReq req) {
        return studentManagerService.insertStudent(loginUserId,req);
    }

    @RequestMapping(value = "/student/list",method = RequestMethod.GET)
    public List<StudentPo> studentList(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId)
    {
        return studentManagerService.studentList();
    }
}
