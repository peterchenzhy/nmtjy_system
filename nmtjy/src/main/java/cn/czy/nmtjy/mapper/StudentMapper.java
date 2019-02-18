package cn.czy.nmtjy.mapper;


import cn.czy.nmtjy.model.po.StudentPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    StudentPo queryStudentByName(@Param("name") String name);

    int insertSelective(StudentPo student);
}