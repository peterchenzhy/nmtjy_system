package cn.czy.nmtjy.service;

import cn.czy.nmtjy.commons.NmtjyException;
import cn.czy.nmtjy.mapper.CourseConfigMapper;
import cn.czy.nmtjy.model.po.CourseConfigPo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: PeterChen
 * @Date: 2019/2/24 17:07
 * @Version 1.0
 */
@Service
public class CourseConfigService {
    @Autowired
    private CourseConfigMapper courseConfigMapper;


    public List<CourseConfigPo> getConfigByConfigType(String configType){
        if(Strings.isBlank(configType)){
            throw new NmtjyException("没有此配置！"+configType);
        }
        return courseConfigMapper.getConfigByConfigType(configType);
    }
}
