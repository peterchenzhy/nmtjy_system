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

    /**
    * 根据名字查询员工信息
    * @author PeterChen
    * @modifier PeterChen
    * @version v1
    * @since 2019/2/17 14:06
    * @param name
    * @summary
    * @return  cn.czy.nmtjy.model.po.EmployeePo
    */
    public EmployeePo queryEmployeeByName(String name){
        return this.employeeMapper.queryEmployeeByName( name);
    }

}
