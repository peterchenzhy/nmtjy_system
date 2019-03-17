package cn.czy.nmtjy.service.course;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.commons.NmtjyException;
import cn.czy.nmtjy.mapper.CourseMapper;
import cn.czy.nmtjy.model.po.CoursePo;
import cn.czy.nmtjy.model.req.CourseReq;
import cn.czy.nmtjy.model.req.CourseSearchReq;
import cn.czy.nmtjy.model.vo.CourseVo;
import cn.czy.nmtjy.service.cache.EhcacheUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 课程管理
 *
 * @Author: PeterChen
 * @Date: 2019/2/17 16:58
 * @Version 1.0
 */
@Service
@Slf4j
public class CourseManagerService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CacheManager cacheManager;

    public CoursePo queryCourseById(long id) {
        return this.courseMapper.selectByPrimaryKey(id);
    }


    public boolean insertCourse(CoursePo po) {
        checkParam(po);
        return this.courseMapper.insertSelective(po) > 0;
    }

    public boolean insertCourse(Integer loginUserId, CourseReq req) {
        CoursePo po = new CoursePo();
        BeanUtils.copyProperties(req, po);
        po.setCreator(loginUserId);
        po.setOperator(loginUserId);
        return this.insertCourse(po);
    }

    private void checkParam(CoursePo po) {
        if (Strings.isBlank(po.getName())) {
            log.error("插入课程信息，课程名称不能为空");
            throw new NmtjyException("插入课程信息，课程名称不能为空");
        }
        if (Objects.isNull(po.getType())) {
            log.error("插入课程信息，课程类型不能为空");
            throw new NmtjyException("插入课程信息，课程类型不能为空");
        }
        if (Objects.isNull(po.getProgress())) {
            log.error("插入课程信息，课程进度类型不能为空");
            throw new NmtjyException("插入课程信息，课程进度类型不能为空");
        }
        if (Objects.isNull(po.getSeason())) {
            log.error("插入课程信息，课程季度类型不能为空");
            throw new NmtjyException("插入课程信息，课程季度类型不能为空");
        }
        if (Objects.isNull(po.getPerTime())) {
            log.error("插入课程信息，课程每次时间不能为空");
            throw new NmtjyException("插入课程信息，课程每次时间不能为空");
        }
        if (Objects.isNull(po.getTimes())) {
            log.error("插入课程信息，课程次数不能为空");
            throw new NmtjyException("插入课程信息，课程次数不能为空");
        }
        if (Objects.isNull(po.getPerPrice())) {
            log.error("插入课程信息，单次课程价格不能为空");
            throw new NmtjyException("插入课程信息，单次课程价格不能为空");
        }
        if (Objects.isNull(po.getStartDate())) {
            log.error("插入课程信息，课程开始日期不能为空");
            throw new NmtjyException("插入课程信息，课程开始日期不能为空");
        }
        if (Objects.isNull(po.getEndDate())) {
            log.error("插入课程信息，课程结束日期不能为空");
            throw new NmtjyException("插入课程信息，课程结束日期不能为空");
        }
    }

    public List<CoursePo> getCourse(CourseSearchReq req) {
        return this.courseMapper.queryCourses(req);
    }

    public List<CourseVo> getAllCourses() {
        List<CoursePo> poList = this.getCourse(null);
        List<CourseVo> voList = new ArrayList<>(poList.size());
        poList.forEach(e -> {
            voList.add(po2vo(e));
        });
        return voList;
    }

    private CourseVo po2vo(CoursePo po) {
        CourseVo vo = new CourseVo();
        BeanUtils.copyProperties(po, vo);
        try {
            vo.setAreaDescription(getCacheByType(Consts.COURSE_AREA).get(po.getArea().toString()));
            vo.setProgressDescription(getCacheByType(Consts.COURSE_PROGRESS).get(po.getProgress().toString()));
            vo.setSchoolTypeDescription(getCacheByType(Consts.SCHOOL_TYPE).get(po.getSchoolType().toString()));
            vo.setSeasonDescription(getCacheByType(Consts.COURSE_SEASON).get(po.getSeason().toString()));
            vo.setTypeDescription(getCacheByType(Consts.COURSE_TYPE).get(po.getType().toString()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        vo.setStartDateDescription(new DateTime(po.getStartDate()).toString(Consts.DATE_FORMAT));
        vo.setEndDateDescription(new DateTime(po.getEndDate()).toString(Consts.DATE_FORMAT));
        return vo;
    }

    /**
     * 获取指定类型cache
     *
     * @param type
     * @return java.util.Map<java.lang.String               ,               java.lang.String>
     * @author PeterChen
     * @modifier PeterChen
     * @version v1
     * @summary
     * @since 2019/3/17 19:59
     */
    private Map<String, String> getCacheByType(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        Map<String, String> map = (Map<String, String>) EhcacheUtils.getCache(cacheManager, type);
        if (Objects.isNull(map)) {
            log.error("该类型配置没找到 type: {}", type);
        }
        return map;
    }

}
