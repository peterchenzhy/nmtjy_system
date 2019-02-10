package cn.czy.nmtjy.mapper;

import cn.czy.nmtjy.model.po.CoursePo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePo record);

    int insertSelective(CoursePo record);

    CoursePo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePo record);

    int updateByPrimaryKey(CoursePo record);
}