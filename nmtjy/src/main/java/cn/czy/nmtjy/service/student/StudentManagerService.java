package cn.czy.nmtjy.service.student;

import cn.czy.nmtjy.commons.NmtjyException;
import cn.czy.nmtjy.mapper.StudentMapper;
import cn.czy.nmtjy.model.po.StudentPo;
import cn.czy.nmtjy.model.req.StudentReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 学生管理 curd
 * @Author: PeterChen
 * @Date: 2019/2/17 14:06
 * @Version 1.0
 */
@Service
@Slf4j
public class StudentManagerService {
    @Autowired
    private StudentMapper  studentMapper;

    public StudentPo queryStudentByName(String name){
        return studentMapper.queryStudentByName( name);
    }
    private boolean insertStudent(StudentPo student){
        return studentMapper.insertSelective(student)>0;
    }

    public boolean insertStudent(Integer loginUserId ,StudentReq req) {
        if(Objects.isNull(req.getName())){
            log.error("插入学生时， 学生姓名不能为空");
            throw new NmtjyException("插入学生时， 学生姓名不能为空");
        }
        StudentPo po = new StudentPo();
        po.setName(req.getName());
        po.setBirthday(req.getBirthday());
        po.setElementarySchool(req.getElementarySchool());
        po.setMiddleSchool(req.getMiddleSchool());
        po.setHighSchool(req.getHighSchool());
        po.setGrade(req.getGrade());
        po.setRemark(req.getRemark());
        po.setCreator(loginUserId);
        po.setOperator(loginUserId);
        return this.insertStudent(po);
    }

    public List<StudentPo> studentList() {
        return studentMapper.queryStudent();
    }
}
