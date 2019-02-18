package cn.czy.nmtjy.service.course;

import cn.czy.nmtjy.commons.enums.PayStatusEnum;
import cn.czy.nmtjy.commons.NmtjyException;
import cn.czy.nmtjy.mapper.CourseRegistrationMapper;
import cn.czy.nmtjy.model.po.CourseRegistrationPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 课程报名
 *
 * @Author: PeterChen
 * @Date: 2019/2/17 17:06
 * @Version 1.0
 */
@Service
@Slf4j
public class CourseRegistrationService {
    @Autowired
    private CourseRegistrationMapper courseRegistrationMapper;

    public boolean courseRegister(Integer loginUserId,Long courseId, Long studentId, Integer times, Integer payStatus) {
        if (Objects.isNull(courseId)) {
            log.error("课程id courseID 不能为空");
            throw new NmtjyException("课程id courseID 不能为空");
        }
        if (Objects.isNull(studentId)) {
            log.error("学生id studentId 不能为空");
            throw new NmtjyException("学生id studentId 不能为空");
        }
        if (Objects.isNull(times) || times == 0) {
            log.error("报名次数 times 必须大于0");
            throw new NmtjyException("报名次数 times 必须大于0");
        }
        if(Objects.isNull(payStatus)){
            payStatus = PayStatusEnum.未支付.getCode();
        }

        CourseRegistrationPo po = new CourseRegistrationPo();
        po.setCourseId(courseId);
        po.setStudentId(studentId);
        po.setTimes(times);
        po.setPayStatus(payStatus);
        po.setCreator(loginUserId);
        po.setOperator(loginUserId);

        return  this.courseRegistrationMapper.insertSelective(po)>0;
    }

}
