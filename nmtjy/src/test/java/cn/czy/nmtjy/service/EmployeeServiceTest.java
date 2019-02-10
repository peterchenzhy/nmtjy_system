package cn.czy.nmtjy.service;

import cn.czy.nmtjy.BaseTest;
import cn.czy.nmtjy.model.po.EmployeePo;
import cn.czy.nmtjy.service.employee.EmployeeService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: PeterChen
 * @Date: 2019/2/9 19:11
 * @Version 1.0
 */
@Slf4j
public class EmployeeServiceTest extends BaseTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void test(){
        log.info("test start ..");
        EmployeePo po = employeeService.queryEmployeeByName("peter");
        log.info( JSON.toJSONString(  po));
        log.info("test end  ...");
    }
}
