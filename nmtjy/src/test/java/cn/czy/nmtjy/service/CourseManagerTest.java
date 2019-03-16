package cn.czy.nmtjy.service;

import cn.czy.nmtjy.BaseTest;
import cn.czy.nmtjy.model.po.CoursePo;
import cn.czy.nmtjy.model.po.EmployeePo;
import cn.czy.nmtjy.model.vo.CourseVo;
import cn.czy.nmtjy.service.course.CourseManagerService;
import cn.czy.nmtjy.service.employee.EmployeeService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: PeterChen
 * @Date: 2019/2/9 19:11
 * @Version 1.0
 */
@Slf4j
public class CourseManagerTest extends BaseTest {
    @Autowired
    private CourseManagerService courseManagerService;

    @Test
    public void test(){
        log.info("test start ..");
      List<CourseVo> vo = courseManagerService.getAllCourses();
        log.info( JSON.toJSONString(  vo));
        log.info("test end  ...");
    }
}
