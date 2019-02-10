package cn.czy.nmtjy.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

}