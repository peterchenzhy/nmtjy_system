package cn.czy.nmtjy.mapper;

import cn.czy.nmtjy.model.po.EmployeePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeePo queryEmployeeByName(@Param("name") String name);

    List<EmployeePo> getAllEmployees();

    int insertSelective(EmployeePo po);
}