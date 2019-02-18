package cn.czy.nmtjy.mapper;


import cn.czy.nmtjy.model.po.TeacherPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

    TeacherPo queryTeacherByName(@Param("name") String name);

    int insertSelective(TeacherPo po);
}