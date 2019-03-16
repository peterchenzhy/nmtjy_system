package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.model.po.EmployeePo;
import cn.czy.nmtjy.model.req.EmployeeReq;
import cn.czy.nmtjy.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: PeterChen
 * @Date: 2019/2/24 10:33
 * @Version 1.0
 */
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee/list",method = RequestMethod.GET)
    public List<EmployeePo> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    public Boolean putEmpoyee(@RequestHeader(Consts.LONGIN_USER)Integer loginUserId, @RequestBody @Valid EmployeeReq req){
        return this.employeeService.putEmployee( loginUserId,req);
    }
}
