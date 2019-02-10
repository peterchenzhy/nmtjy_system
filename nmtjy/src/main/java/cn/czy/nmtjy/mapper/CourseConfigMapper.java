package cn.czy.nmtjy.mapper;

import cn.czy.nmtjy.model.po.CourseConfigPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseConfigPo record);

    int insertSelective(CourseConfigPo record);

    CourseConfigPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseConfigPo record);

    int updateByPrimaryKey(CourseConfigPo record);
}