package cn.czy.nmtjy.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

}