package cn.czy.nmtjy.service.employee;

import cn.czy.nmtjy.commons.NmtjyException;
import cn.czy.nmtjy.commons.enums.StatusEnum;
import cn.czy.nmtjy.mapper.EmployeeMapper;
import cn.czy.nmtjy.model.po.EmployeePo;
import cn.czy.nmtjy.model.req.EmployeeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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

    public List<EmployeePo> getAllEmployees() {
        return this.employeeMapper.getAllEmployees();
    }

    public Boolean putEmployee(int loginUserId, EmployeeReq req) {
        if(!req.getPassword().equals(req.getPasswordAgain())){
            throw new NmtjyException("两次密码不一样。");
        }

        EmployeePo emp = this.queryEmployeeByName(req.getName());
        if(emp!=null){
            throw new NmtjyException("该用户已经存在。");
        }
        EmployeePo po = new EmployeePo();

        po.setName(req.getName());
        po.setTel(req.getTel());
        po.setBirthday(req.getBirthday());
        po.setRemark(req.getRemark());
        po.setPassword(req.getPassword());
        po.setStatus(StatusEnum.有效.getCode());
        po.setCreator(loginUserId);
        po.setOperator(loginUserId);

        return this.employeeMapper.insertSelective(po)>0;
    }
}
