package cn.czy.nmtjy.service.teacher;

import cn.czy.nmtjy.mapper.TeacherMapper;
import cn.czy.nmtjy.model.po.TeacherPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教师管理
 * @Author: PeterChen
 * @Date: 2019/2/17 16:42
 * @Version 1.0
 */
@Service
public class TeacherManagerService {
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherPo queryTeacherByName (String name){
       return  this.teacherMapper.queryTeacherByName(name);
    }
    public boolean insertTeacher(TeacherPo po){
        return this.teacherMapper.insertSelective(po)>0;
    }
}
