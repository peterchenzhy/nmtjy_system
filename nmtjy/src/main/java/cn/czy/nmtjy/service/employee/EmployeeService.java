package cn.czy.nmtjy.service.employee;

import cn.czy.nmtjy.mapper.EmployeeMapper;
import cn.czy.nmtjy.model.po.EmployeePo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 员工侧服务
 * @Author: PeterChen
 * @Date: 2019/2/9 18:58
 * @Version 1.0
 */
@Service
public class EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    public EmployeePo queryEmployeeByName(String name){
        return this.employeeMapper.queryEmployeeByName( name);
    }

}
