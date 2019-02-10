package cn.czy.nmtjy.mapper;

import cn.czy.nmtjy.model.po.CourseRegistrationPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseRegistrationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseRegistrationPo record);

    int insertSelective(CourseRegistrationPo record);

    CourseRegistrationPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseRegistrationPo record);

    int updateByPrimaryKey(CourseRegistrationPo record);
}