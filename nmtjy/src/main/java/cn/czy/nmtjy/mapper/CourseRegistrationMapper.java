package cn.czy.nmtjy.mapper;

import cn.czy.nmtjy.model.po.CourseRegistrationPo;
import cn.czy.nmtjy.model.po.CourseRegistrationVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseRegistrationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseRegistrationPo record);

    int insertSelective(CourseRegistrationPo record);

    CourseRegistrationPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseRegistrationPo record);

    int updateByPrimaryKey(CourseRegistrationPo record);

    List<CourseRegistrationVo> query(String studentName, String courseName);
}