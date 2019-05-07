package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.model.req.CourseReq;
import cn.czy.nmtjy.model.vo.CourseVo;
import cn.czy.nmtjy.service.course.CourseManagerService;
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
public class CourseController {
    @Autowired
    private CourseManagerService courseManagerService;

    /**
    * 创建课程
    * @author PeterChen
    * @modifier PeterChen
    * @version v1
    * @since 2019/2/25 21:16
    * @param loginUserId
    * @param req
    * @summary
    * @return  boolean
    */
    @RequestMapping(value = "/courseManager/course",method = RequestMethod.PUT)
    public boolean createCourse(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId, @RequestBody @Valid CourseReq req){
        return courseManagerService.insertCourse(loginUserId,req);
    }
    /**
    * 获取所有课程
    * @author PeterChen
    * @modifier PeterChen
    * @version v1
    * @since 2019/2/25 21:16
    * @param loginUserId
    * @summary
    * @return  java.util.List<cn.czy.nmtjy.model.po.CoursePo>
    */
    @RequestMapping(value = "/courseManager/course",method = RequestMethod.GET)
    public PageInfo<CourseVo> getAllCourses(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId,
                                            @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
                                            @RequestParam(value = "pageNo",required = false,defaultValue = "1") int pageNo){
        return courseManagerService.getAllCourses( pageSize ,pageNo );
    }


}
